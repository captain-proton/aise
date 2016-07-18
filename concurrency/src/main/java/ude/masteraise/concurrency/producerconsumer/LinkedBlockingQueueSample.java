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
                System.out.println(getName() + " offering " + item);
                boolean offered = false;
                try
                {
                    warehouse.put(item);
                    offered = true;
                } catch (InterruptedException e)
                {
                    System.out.println(getName() + " interrupted");
                }
                String msg = offered
                             ? getName() + " offered  " + item
                             : getName() + " offering " + item + " failed";
                System.out.println(msg);
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
                System.out.println(getName() + " polling");
                try
                {
                    Integer item = warehouse.take();
                    String msg = item != null
                                 ? getName() + " polled   " + item
                                 : getName() + " polling  failed";
                    System.out.println(msg);
                } catch (InterruptedException e)
                {
                    System.out.println(getName() + " interrupted");
                }
            }
        }
    }
}
