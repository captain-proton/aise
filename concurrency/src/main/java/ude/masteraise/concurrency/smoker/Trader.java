package ude.masteraise.concurrency.smoker;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by nils on 03.08.16.
 */
public class Trader extends Thread
{
    private static final long TRADING_TIME = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);

    private final Table table;
    private final Lock lock;
    private final Condition put;
    private final Condition took;

    public Trader(Table table, Lock lock, Condition put, Condition took)
    {
        super("Trader");
        this.table = table;
        this.lock = lock;
        this.put = put;
        this.took = took;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            Set<Ingredient> ingredients = Ingredient.random(2);

            // put resources
            try
            {
                lock.lock();

                ThreadUtils.log(this, "put");
                ingredients.stream().forEach(table::addIngredient);
                put.signalAll();

                // wait until table is empty
                ThreadUtils.log(this, "wait");
                took.await();
                ThreadUtils.log(this, "wokeUp");
            } catch (InterruptedException e)
            {
                ThreadUtils.handleInterrupt(this);
            } finally
            {
                lock.unlock();
            }
        }
    }
}
