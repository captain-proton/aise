package ude.masteraise.concurrency.part2.extrasheet2;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.BaseThread;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 24.06.16.
 */
public class ExtraExercise2_2 {
    private static final int MAX_WARE_COUNT = 50;

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse(MAX_WARE_COUNT);
        Producer p = new Producer("P", warehouse);
        p.start();

        List<Consumer> consumers = IntStream
                .range(0, 3)
                .mapToObj(i -> new Consumer("C" + i, warehouse))
                .collect(Collectors.toList());
        consumers.forEach(Thread::start);
    }

    static class Producer extends BaseThread {

        private Warehouse warehouse;

        public Producer(String name, Warehouse warehouse) {
            super(name);
            this.warehouse = warehouse;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 2000000000L) {

                long sleepTime = RandomUtils.nextLong(0, 51);
                ThreadUtils.sleepSilent(sleepTime);

                // synchronize access to the warehouse so that producer and consumer do not wait for the others
                synchronized (warehouse) {
                    // produce is ware is not full
                    if (!warehouse.isFull()) {

                        warehouse.produce();
                        ThreadUtils.sout(this, "produced", "wares", warehouse.goodsCount);
                    }
                }
            }
        }
    }

    static class Consumer extends BaseThread {
        private final Warehouse warehouse;

        public Consumer(String name, Warehouse warehouse) {
            super(name);
            this.warehouse = warehouse;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 2000000000L) {

                long sleepTime = RandomUtils.nextLong(0, 151);
                ThreadUtils.sleepSilent(sleepTime);

                // synchronize access to the warehouse so that producer and consumer do not wait for the others
                synchronized (warehouse) {
                    // take ware from warehouse if it is present
                    if (warehouse.hasGoods()) {

                        warehouse.remove();
                        ThreadUtils.sout(this, "consumed", "wares", warehouse.goodsCount);
                    }
                }
            }
        }
    }

    static class Warehouse {

        int goodsCount = 0;
        int maxWaresCount;

        public Warehouse(int maxWaresCount) {
            this.maxWaresCount = maxWaresCount;
        }

        void produce() {
            // 1
            goodsCount++;
        }

        void remove() {
            // 2
            goodsCount--;
        }

        boolean hasGoods() {
            return goodsCount > 0;
        }

        boolean isFull() {
            return goodsCount >= maxWaresCount;
        }
    }
}

