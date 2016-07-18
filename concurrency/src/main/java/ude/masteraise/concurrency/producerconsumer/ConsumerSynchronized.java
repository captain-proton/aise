package ude.masteraise.concurrency.producerconsumer;

import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 18:19
 */
public class ConsumerSynchronized extends Thread
{
    private final Warehouse warehouse;
    private final int iterations;

    public ConsumerSynchronized(int iterations, Warehouse warehouse)
    {
        this.warehouse = warehouse;
        this.iterations = iterations;
    }

    public ConsumerSynchronized(int id, int iterations, Warehouse warehouse)
    {
        super("Consumer " + id);
        this.warehouse = warehouse;
        this.iterations = iterations;
    }

    @Override
    public void run()
    {
        IntStream.range(0, iterations)
                .forEach(this::consume);
    }

    private void consume(int iteration)
    {
        warehouse.consumeSynchronized();
    }
}
