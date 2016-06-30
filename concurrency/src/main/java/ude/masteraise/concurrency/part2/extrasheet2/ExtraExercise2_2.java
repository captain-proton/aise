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
                after sleep, synchronize access to ware and check for present goods.
                if warehouse is not full produce item
                 */
                synchronized (warehouse)
                {
                    // produce is ware is not full
                    if (!warehouse.isFull())
                    {

                        warehouse.produce();
                        ThreadUtils.sout(this, "produced", "goods", warehouse.goodsCount);
                    }
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
                after sleep, synchronize access to ware and check for present goods.
                if one is present remove good
                 */
                synchronized (warehouse)
                {
                    // take ware from warehouse if it is present
                    if (warehouse.hasGoods())
                    {

                        warehouse.remove();
                        ThreadUtils.sout(this, "consumed", "goods", warehouse.goodsCount);
                    }
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

        void produce()
        {
            // - 3 -
            goodsCount++;
        }

        void remove()
        {
            // - 4 -
            goodsCount--;
        }

        /**
         * - 5 -
         * Check if goods are present.
         *
         * @return <code>true</code> if goods are present, <code>false</code> otherwise
         */
        boolean hasGoods()
        {
            return goodsCount > 0;
        }

        /**
         * - 6 -
         * Check if warehouse is full.
         *
         * @return <code>true</code> if warehouse if full, <code>false</code> otherwise
         */
        boolean isFull()
        {
            return goodsCount >= maxGoodsCount;
        }
    }
}

