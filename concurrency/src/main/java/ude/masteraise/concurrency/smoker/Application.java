package ude.masteraise.concurrency.smoker;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * Created by nils on 03.08.16.
 */
public class Application
{
    public static void main(String[] args)
    {
        Table table = new Table();
        Lock lock = new ReentrantLock();

        Condition put = lock.newCondition();
        Condition took = lock.newCondition();

        Trader trader = new Trader(table, lock, put, took);

        Smoker tobaccoSmoker = new Smoker("TobaccoSmoker", table, lock, put, took, Ingredient.Match, Ingredient.Paper);
        Smoker matchSmoker = new Smoker("MatchSmoker", table, lock, put, took, Ingredient.Paper, Ingredient.Tobacco);
        Smoker paperSmoker = new Smoker("PaperSmoker", table, lock, put, took, Ingredient.Match, Ingredient.Tobacco);

        Stream.of(trader, tobaccoSmoker, matchSmoker, paperSmoker).forEach(Thread::start);

        ThreadUtils.sleepSilent(20, TimeUnit.SECONDS);
        ThreadUtils.log(Thread.currentThread(), "stopping");

        trader.interrupt();
        Stream.of(tobaccoSmoker, matchSmoker, paperSmoker).forEach(Application::stop);
    }

    private static void stop(Smoker smoker)
    {
        smoker.interrupt();
        ThreadUtils.log(smoker, "done", "smoked", smoker.getSmokingCount());
    }
}
