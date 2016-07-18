package ude.masteraise.concurrency.producerconsumer;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 18:19
 */
public class ConsumerWaitNotify extends Thread
{
    private final Warehouse warehouse;
    private final int iterations;
    private List<ProducerWaitNotify> producers = new ArrayList<>();

    public ConsumerWaitNotify(int id, int iterations, Warehouse warehouse)
    {
        super("Consumer " + id);
        this.warehouse = warehouse;
        this.iterations = iterations;
    }

    @Override
    public void run()
    {
        IntStream.range(0, iterations).forEach(this::consume);
    }

    private void consume(int iteration)
    {
        synchronized (warehouse)
        {
            while (warehouse.isEmpty() && warehouse.isOpen())
            {
                ThreadUtils.log(this, "interrupting");
                producers.forEach(Thread::interrupt);
                try
                {
                    ThreadUtils.log(this, "wait", "empty");
                    if (warehouse.isOpen())
                        warehouse.wait(1000);
                    ThreadUtils.log(this, "notified");
                } catch (InterruptedException e)
                {
                    ThreadUtils.log(this, "interrupted");
                }
            }
        }
        synchronized (warehouse)
        {
            if (warehouse.isOpen())
            {
                warehouse.consume();
                // this sleep is only to compensate the time needed to produce a random item
                ThreadUtils.sleepSilent(1);

                ThreadUtils.log(this, "notifying");
                warehouse.notifyAll();
            }
        }
        yield();
    }

    public void setProducers(List<ProducerWaitNotify> producers)
    {
        this.producers = producers;
    }
}
