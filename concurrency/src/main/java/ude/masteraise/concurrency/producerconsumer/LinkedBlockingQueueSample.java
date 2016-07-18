package ude.masteraise.concurrency.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by nils on 18.07.16.
 */
public class LinkedBlockingQueueSample
{
    private static final int WAREHOUSE_CAPACITY = 10;
    private static final AtomicBoolean isOpen = new AtomicBoolean(true);

    public static void main(String[] args)
    {
        BlockingQueue<Integer> warehouse = new LinkedBlockingQueue<>(WAREHOUSE_CAPACITY);
        Producer p = new Producer(warehouse);
        Consumer c = new Consumer(warehouse);

        p.start();
        c.start();

        try
        {
            Thread.sleep(TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS));
        } catch (InterruptedException e)
        {
        } finally
        {
            isOpen.set(false);
        }
    }

    static class Producer extends Thread
    {
        private final BlockingQueue<Integer> warehouse;
        private final Random random;

        public Producer(BlockingQueue<Integer> warehouse)
        {
            super("Producer");
            this.warehouse = warehouse;
            this.random = new Random();
        }

        @Override
        public void run()
        {
            while (isOpen.get())
            {
                Integer item = random.nextInt(100);
                try
                {
                    System.out.println(getName() + " putBefore " + item);
                    warehouse.put(item);
                    System.out.println(getName() + " putAfter  " + item + " items " + warehouse.size());
                } catch (InterruptedException e)
                {
                    System.out.println(getName() + " interrupted");
                }
            }
        }
    }

    static class Consumer extends Thread
    {
        private final BlockingQueue<Integer> warehouse;

        public Consumer(BlockingQueue<Integer> warehouse)
        {
            super("Consumer");
            this.warehouse = warehouse;
        }

        @Override
        public void run()
        {
            while (isOpen.get())
            {
                try
                {
                    System.out.println(getName() + " take");
                    Integer item = warehouse.take();
                    System.out.println(getName() + " took " + item + " items " + warehouse.size());
                } catch (InterruptedException e)
                {
                    System.out.println(getName() + " interrupted");
                }
            }
        }
    }
}
