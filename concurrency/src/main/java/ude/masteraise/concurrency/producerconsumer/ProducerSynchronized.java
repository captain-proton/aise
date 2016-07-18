package ude.masteraise.concurrency.producerconsumer;

import org.apache.commons.lang3.RandomUtils;

import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 17:34
 */
public class ProducerSynchronized extends Thread
{
    private final Warehouse warehouse;
    private final int iterations;

    public ProducerSynchronized(int iterations, Warehouse warehouse)
    {
        setName("Producer");
        this.iterations = iterations;
        this.warehouse = warehouse;
    }

    public ProducerSynchronized(int id, int iterations, Warehouse warehouse)
    {
        super("Producer " + id);
        this.iterations = iterations;
        this.warehouse = warehouse;
    }

    @Override
    public void run()
    {
        IntStream.range(0, iterations)
                .forEach(this::produce);
    }

    private void produce(int iteration)
    {
        synchronized (warehouse)
        {
            Integer item = RandomUtils.nextInt(0, 201);
            warehouse.addSynchronized(item);
        }
    }
}
