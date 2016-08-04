package ude.masteraise.concurrency.smoker;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * A <code>Smoker</code> contains one resource it needs to smoke in an infinite number. The other two resources
 * it has to obtain from the {@linkplain Agent}. If the resources that were put on the {@linkplain Table} are exactly
 * the resources that it needs, the smoker takes them and smokes.
 */
public class Smoker extends Thread
{
    private static final long SMOKING_TIME = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
    private final Lock lock;
    private final Table table;
    private final Condition put;
    private final Condition took;
    private final SmokingResource[] needs;

    private int smokingCount;

    public Smoker(String name, Table table, Lock lock, Condition put, Condition took, SmokingResource... needs)
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
        /*
        Trade with the agent until an interrupt was called.
         */
        while (!isInterrupted())
        {
            // notice if the resources that were put on the table
            boolean tookResources = false;
            try
            {
                /*
                Obtain lock. If the lock could not be obtained the smoker waits until the agent that owns the lock
                has unlocked it.
                 */
                lock.lock();

                /*
                The table must be filled to take resources of it. Therefore the smoker has to wait. On wait the lock is
                released and the other smokers and agent are able to acquire it. After the lock was released by this
                smoker this agent is able to reacquire it if no other locked it.
                 */
                while (table.isEmpty())
                {
                    ThreadUtils.log(this, "waiting");
                    put.await();
                    ThreadUtils.log(this, "wokeUp");
                }

                Set<SmokingResource> smokingResources = table.getAvailableResources();
                /*
                The resources must be checked, if they are all the resources that are needed by this smoker. If this
                smoker took the resources signal the instances that wait on "took" (the agent).
                 */
                if (check(smokingResources))
                {
                    tookResources = true;
                    Arrays.stream(needs).forEach(table::takeSmokingResource);
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
            /*
            If this smoker took the resources it needs to smoke, smoke for some time.
             */
            if (tookResources)
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

    /**
     * Check if all ingredients this smoker {@linkplain #needs} match the given resources.
     *
     * @return <code>true</code> if target resources are the resources this smoker needs, <code>false</code> otherwise
     */
    private boolean check(Set<SmokingResource> smokingResources)
    {
        return Arrays.stream(needs).allMatch(n -> smokingResources.contains(n));
    }

    public int getSmokingCount()
    {
        return smokingCount;
    }
}
