package ude.masteraise.concurrency.smoker;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>
 * An <code>Agent</code> is the instance that is able to provide resources, that the three {@linkplain Smoker}s need
 * to smoke. The agent will choose two resources at random and put them on the table if it is empty.
 * </p>
 */
public class Agent extends Thread
{
    /** This is the table used by the agent and the three smokers. */
    private final Table table;

    /** */
    private final Lock lock;
    private final Condition put;
    private final Condition took;

    public Agent(Table table, Lock lock, Condition put, Condition took)
    {
        super("Agent");
        this.table = table;
        this.lock = lock;
        this.put = put;
        this.took = took;
    }

    @Override
    public void run()
    {
        /*
        Trade with the smokers until an interrupt was called.
         */
        while (!isInterrupted())
        {
            // get two random resources
            Set<SmokingResource> smokingResources = SmokingResource.random();

            try
            {
                /*
                Obtain lock. If the lock could not be obtained the agent waits until the smoker that has the lock
                has unlocked it.
                 */
                lock.lock();

                /*
                The table must be empty to put resources on it. Therefore the agent has to wait. On wait the lock is
                released and the smokers are able to acquire it. After the lock was released by the smoker this agent
                is able to reacquire it if no other smoker locked it.
                 */
                while (!table.isEmpty())
                {
                    // wait until table is empty
                    ThreadUtils.log(this, "wait");
                    took.await();
                    ThreadUtils.log(this, "wokeUp");
                }

                /*
                This is the critical section. The agent was finally able to acquire the lock and can put its
                resources onto the table. After put the smokers that wait on put are signaled.
                 */
                ThreadUtils.log(this, "put");
                smokingResources.stream().forEach(table::putSmokingResource);
                put.signalAll();
            } catch (InterruptedException e)
            {
                /*
                The agent is interrupted from the outside, therefore a silent await must not be used, otherwise the
                second interrupt, that must be called, is not called and the agent does not stop.
                 */
                ThreadUtils.handleInterrupt(this);
            } finally
            {
                /*
                Finally the lock must be unlocked! Otherwise the smokers are possibly waiting to acquire the lock.
                 */
                lock.unlock();
            }
        }
    }
}
