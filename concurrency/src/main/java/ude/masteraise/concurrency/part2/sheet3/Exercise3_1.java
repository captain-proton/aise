package ude.masteraise.concurrency.part2.sheet3;

import org.apache.commons.lang3.RandomUtils;

/**
 * Created by nils on 22.06.16.
 */
public class Exercise3_1 {

    public static void main(String[] args) {

        ProcessA a = new ProcessA();
        ProcessB b = new ProcessB();

        a.start();
        b.start();
    }

    private static class ProcessA extends Thread {
        @Override
        public void run() {
            a1();
            a2();
        }

        private void a1() {
            System.out.println("a1()");
        }

        private void a2() {
            System.out.println("a2()");
        }
    }

    private static class ProcessB extends Thread {

        private boolean x;

        public ProcessB() {
            // 0 or 1 -> false or true
            x = RandomUtils.nextInt(0, 2) == 1;
        }

        @Override
        public synchronized void start() {
            b1();
            b2();
            if (x)
                b3();
            else
                b4();
        }

        private void b1() {
            System.out.println("b1()");
        }

        private void b2() {
            System.out.println("b2()");
        }

        private void b3() {
            System.out.println("b3()");
        }

        private void b4() {
            System.out.println("b4()");
        }
    }
}
