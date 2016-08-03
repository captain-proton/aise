package ude.masteraise.concurrency.smoker;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by nils on 03.08.16.
 */
public class Smoker extends Thread
{
    private static final long TRADING_TIME = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);
    private static final long SMOKING_TIME = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
    private final Lock lock;
    private final Table table;
    private final Condition put;
    private final Condition took;
    private final Ingredient[] needs;

    private int smokingCount;

    public Smoker(String name, Table table, Lock lock, Condition put, Condition took, Ingredient... needs)
    {
        super(name);
        this.table = table;
        this.lock = lock;
        this.put = put;
        this.took = took;
        this.needs = needs;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            boolean tookIngredients = false;
            try
            {
                lock.lock();

                while (!table.hasIngredients())
                {
                    ThreadUtils.log(this, "waiting");
                    put.await();
                    ThreadUtils.log(this, "wokeUp");
                }

                Set<Ingredient> ingredients = table.getIngredients();
                if (check(ingredients))
                {
                    tookIngredients = true;
                    Arrays.stream(needs).forEach(table::removeIngredient);
                    ThreadUtils.log(this, "took");
                    took.signalAll();
                    ThreadUtils.log(this, "signaled");
                }
            } catch (InterruptedException e)
            {
                ThreadUtils.handleInterrupt(this);
            } finally
            {
                lock.unlock();
            }
            if (tookIngredients)
            {
                ThreadUtils.log(this, "smoking");
                try
                {
                    sleep(SMOKING_TIME);
                } catch (InterruptedException e)
                {
                    ThreadUtils.handleInterrupt(this);
                }
                smokingCount++;
                ThreadUtils.log(this, "finished");
            }
        }
    }

    private boolean check(Set<Ingredient> ingredients)
    {
        return Arrays.stream(needs).allMatch(n -> ingredients.contains(n));
    }

    public int getSmokingCount()
    {
        return smokingCount;
    }
}
