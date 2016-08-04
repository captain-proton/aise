package ude.masteraise.concurrency.smoker;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * The cigarette smoker application contains the solution to the smokers problem.
 */
public class CigaretteSmokerApplication
{
    public static void main(String[] args)
    {
        Table table = new Table();

        /*
        The solution is based upon a lock and two conditions. The lock is used by the agent and the smokers to obtain
        the lock. The conditions are used to wait and signal the other instances.
         */
        Lock lock = new ReentrantLock();
        Condition put = lock.newCondition();
        Condition took = lock.newCondition();

        Agent agent = new Agent(table, lock, put, took);

        Smoker tobaccoSmoker = new Smoker("TobaccoSmoker", table, lock, put, took, SmokingResource.Match, SmokingResource.Paper);
        Smoker matchSmoker = new Smoker("MatchSmoker", table, lock, put, took, SmokingResource.Paper, SmokingResource.Tobacco);
        Smoker paperSmoker = new Smoker("PaperSmoker", table, lock, put, took, SmokingResource.Match, SmokingResource.Tobacco);

        // launch the instances
        Stream.of(agent, tobaccoSmoker, matchSmoker, paperSmoker).forEach(Thread::start);

        // wait for a certain amount of time the application should keep running
        ThreadUtils.sleepSilent(20, TimeUnit.SECONDS);

        // call interrupt on the agent and all three workers
        ThreadUtils.log(Thread.currentThread(), "stopping");
        agent.interrupt();
        Stream.of(tobaccoSmoker, matchSmoker, paperSmoker).forEach(CigaretteSmokerApplication::stop);
    }

    private static void stop(Smoker smoker)
    {
        smoker.interrupt();
        ThreadUtils.log(smoker, "done", "smoked", smoker.getSmokingCount());
    }
}
