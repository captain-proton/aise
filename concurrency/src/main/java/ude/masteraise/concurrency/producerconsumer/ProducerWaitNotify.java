package ude.masteraise.concurrency.producerconsumer;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 17:34
 */
public class ProducerWaitNotify extends Thread
{
    private final Warehouse warehouse;
    private final int iterations;
    private List<ConsumerWaitNotify> consumers;

    public ProducerWaitNotify(int id, int iterations, Warehouse warehouse)
    {
        super("Producer " + id);
        this.iterations = iterations;
        this.warehouse = warehouse;
        this.consumers = new ArrayList<>();
    }

    @Override
    public void run()
    {
        IntStream.range(0, iterations).forEach(this::produce);
    }

    private void produce(int iteration)
    {
        synchronized (warehouse)
        {
            while (warehouse.isFull() && warehouse.isOpen())
            {
                ThreadUtils.log(this, "interrupting");
                consumers.forEach(Thread::interrupt);
                try
                {
                    ThreadUtils.log(this, "wait", "full");
                    if (warehouse.isOpen())
                        warehouse.wait(1000);
                    ThreadUtils.log(this, "notified");
                } catch (InterruptedException e)
                {
                    ThreadUtils.log(this, "interrupted");
                }
            }
        }

        if (warehouse.isOpen())
        {
            Integer item = RandomUtils.nextInt(0, 201);

            synchronized (warehouse)
            {
                warehouse.add(item);
                warehouse.notifyAll();
            }
        }
        yield();

    }

    public void setConsumers(List<ConsumerWaitNotify> consumers)
    {
        this.consumers = consumers;
    }
}
