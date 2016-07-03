package ude.masteraise.concurrency.part2.extrasheet2;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 24.06.16.
 */
public class ExtraExercise2_2
{
    private static final int MAX_WARE_COUNT = 50;

    public static void main(String[] args)
    {

        Warehouse warehouse = new Warehouse(MAX_WARE_COUNT);
        Producer p = new Producer("P", warehouse);
        p.start();

        List<Consumer> consumers = IntStream
                .range(0, 3)
                .mapToObj(i -> new Consumer("C" + i, warehouse))
                .collect(Collectors.toList());
        consumers.forEach(Thread::start);
    }

    static class Producer extends Thread
    {

        private Warehouse warehouse;

        public Producer(String name, Warehouse warehouse)
        {
            super(name);
            this.warehouse = warehouse;
        }

        @Override
        public void run()
        {
            long startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 2000000000L)
            {

                long sleepTime = RandomUtils.nextLong(0, 51);
                ThreadUtils.sleepSilent(sleepTime);

                // - 1 -
                /*
                after sleep try to produce an item. if one was produced, print current count.
                has to be synchronized, otherwise goods count may differ from state after remove.
                 */
                synchronized (warehouse)
                {
                    if (warehouse.produce() >= 0)
                        ThreadUtils.sout(this, "produced", "goods", warehouse.goodsCount);
                }

            }
        }
    }

    static class Consumer extends Thread
    {
        private final Warehouse warehouse;

        public Consumer(String name, Warehouse warehouse)
        {
            super(name);
            this.warehouse = warehouse;
        }

        @Override
        public void run()
        {
            long startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 2000000000L)
            {

                // sleep random time
                long sleepTime = RandomUtils.nextLong(0, 151);
                ThreadUtils.sleepSilent(sleepTime);

                // - 2 -
                /*
                after sleep try to remove items of the warehouse. if one was consumed, print current count.
                has to be synchronized, otherwise goods count may differ from state after remove.
                 */
                synchronized (warehouse)
                {
                    if (warehouse.remove() > 0)
                        ThreadUtils.sout(this, "consumed", "goods", warehouse.goodsCount);
                }
            }
        }
    }

    static class Warehouse
    {

        int goodsCount = 0;
        int maxGoodsCount;

        public Warehouse(int maxGoodsCount)
        {
            this.maxGoodsCount = maxGoodsCount;
        }

        synchronized int produce()
        {
            // - 3 -
            if (goodsCount < maxGoodsCount)
            {
                goodsCount++;
                return 1;
            }
            return 0;
        }

        synchronized int remove()
        {
            // - 4 -
            if (goodsCount > 0)
            {
                goodsCount--;
                return 1;
            }
            return 0;
        }
    }
}

