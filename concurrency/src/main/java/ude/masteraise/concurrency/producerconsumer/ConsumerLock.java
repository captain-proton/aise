package ude.masteraise.concurrency.producerconsumer;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 17:45
 */
public class ConsumerLock extends Thread
{
    private final Warehouse warehouse;
    private final int iterations;
    private final ReentrantReadWriteLock.WriteLock lock;

    public ConsumerLock(int iterations, Warehouse warehouse)
    {
        this.warehouse = warehouse;
        this.iterations = iterations;
        this.lock = warehouse.getWriteLock();
    }

    public ConsumerLock(int id, int iterations, Warehouse warehouse)
    {
        super("Consumer " + id);
        this.warehouse = warehouse;
        this.iterations = iterations;
        this.lock = warehouse.getWriteLock();
    }

    @Override
    public void run()
    {
        IntStream.range(0, iterations)
                .forEach(this::consume);
    }

    private void consume(int iteration)
    {
        boolean locked = ThreadUtils.tryLockSilent(lock, 10, TimeUnit.MILLISECONDS);
        if (locked)
        {
            ThreadUtils.log(this, "locked");
            warehouse.consume();
            lock.unlock();
        } else
        {
            int time = RandomUtils.nextInt(1, 11);
            ThreadUtils.log(this, "notLocked", "sleep", time);
            ThreadUtils.sleepSilent(time);
        }
    }
}
