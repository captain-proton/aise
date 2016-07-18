package ude.masteraise.concurrency.producerconsumer;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 17:34
 */
public class ProducerLock extends Thread
{
    private final Warehouse warehouse;
    private final int iterations;
    private final ReentrantReadWriteLock.WriteLock lock;

    public ProducerLock(int iterations, Warehouse warehouse)
    {
        setName("Producer");
        this.iterations = iterations;
        this.warehouse = warehouse;
        this.lock = warehouse.getWriteLock();
    }

    public ProducerLock(int id, int iterations, Warehouse warehouse)
    {
        super("Producer " + id);
        this.iterations = iterations;
        this.warehouse = warehouse;
        this.lock = warehouse.getWriteLock();
    }

    @Override
    public void run()
    {
        IntStream.range(0, iterations)
                .forEach(this::produce);
    }

    private void produce(int iteration)
    {
        boolean locked = ThreadUtils.tryLockSilent(lock, 10, TimeUnit.MILLISECONDS);
        if (locked)
        {
            Integer item = RandomUtils.nextInt(0, 201);
            warehouse.add(item);
            lock.unlock();
        } else
        {
            int time = RandomUtils.nextInt(1, 11);
            ThreadUtils.log(this, "full", "sleep", time);
            ThreadUtils.sleepSilent(time);
        }
    }
}
