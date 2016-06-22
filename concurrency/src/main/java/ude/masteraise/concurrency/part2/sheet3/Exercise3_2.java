package ude.masteraise.concurrency.part2.sheet3;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nils on 22.06.16.
 */
public class Exercise3_2 {
    static AtomicInteger number;

    public static void main(String[] unbenutzt) {
        number = new AtomicInteger();

//        run_part_a();
        run_part_b();
//        run_part_c();
//        run_part_d();
    }

    private static void run_part_a() {
        run_part(Plus.class, Minus.class);
    }

    private static void run_part_b() {
        run_part(PlusYield.class, MinusYield.class);
    }

    private static void run_part_c() {
        run_part(PlusSleep.class, MinusSleep.class);
    }

    private static void run_part_d() {
        run_part(PlusPrio.class, MinusPrio.class);
    }

    private static void run_part(Class<? extends Thread>... threadClasses) {

        number.set(0);
        Arrays.stream(threadClasses).forEach(t -> {
            try {
                t.newInstance().start();
            } catch (InstantiationException e) {
                System.out.println("could not instantiate thread " + t.getSimpleName());
            } catch (IllegalAccessException e) {
                System.out.println("could not call default constructor on thread " + t.getSimpleName());
            }
        });
    }

    private static void sout(Thread instance) {
        String out = String.format("id: %3d   %10s: %3d   prio: %d",
                instance.getId(),
                instance.getClass().getSimpleName(),
                number.get(),
                instance.getPriority());
        System.out.println(out);
    }

    abstract static class Worker extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                work();
                sout(this);
                finish();
            }
        }

        abstract void work();
        void finish() {}
    }

    static class Plus extends Worker {
        @Override
        void work() {
            Exercise3_2.number.incrementAndGet();
        }
    }

    static class Minus extends Worker {

        @Override
        void work() {
            Exercise3_2.number.decrementAndGet();
        }
    }


    static class PlusYield extends Plus {

        @Override
        void finish() {
            Thread.yield();
        }
    }

    static class MinusYield extends Minus {

        @Override
        void finish() {
            Thread.yield();
        }
    }

    static class PlusSleep extends Plus {
        final static int SLEEP_TIME = 5;

        @Override
        void finish() {
            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.out.println("could not sleep for " + SLEEP_TIME + " millis");
            }
        }
    }

    static class MinusSleep extends Minus {
        final static int SLEEP_TIME = 2;

        @Override
        void finish() {
            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.out.println("could not sleep for " + SLEEP_TIME + " millis");
            }
        }
    }

    static class PlusPrio extends Plus {
        public PlusPrio() {
            setPriority(1);
        }
    }

    static class MinusPrio extends Minus {
        public MinusPrio() {
            setPriority(10);
        }
    }
}
