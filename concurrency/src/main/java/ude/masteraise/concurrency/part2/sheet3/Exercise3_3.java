package ude.masteraise.concurrency.part2.sheet3;

import org.apache.log4j.Logger;
import ude.masteraise.concurrency.part2.ThreadUtils;

/**
 * Created by nils on 22.06.16.
 */
public class Exercise3_3 {

    private static final Logger LOG = Logger.getLogger(Exercise3_3.class);

    public static void main(String[] args) {

//        run_part_a();
//        run_part_b();
        run_part_c();
    }

    static void joinSilent(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOG.error(thread.getClass().getSimpleName() + " was interrupted", e);
        }
    }

    private static void run_part_a() {

        Process1 p1 = new Process1(2, 3);
        Process2 p2 = new Process2(20, 10);

        p1.start();
        p2.start();
    }

    private static void run_part_b() {

        Process1 p1 = new Process1(2, 3);
        Process2 p2 = new Process2(20, 10);

        p2.start();
        joinSilent(p2);
        p1.start();
    }

    private static void run_part_c() {

        Process1 p1 = new Process1Concurrent(2, 3);
        Process2 p2 = new Process2(20, 10);

        p1.start();
        p2.start();
    }

    static class Process1 extends Thread {
        int a, b, c, d, e, x;

        public Process1(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            methodA();
            methodB();
            methodC();
            methodD();
        }

        void methodA() {
            x = a + b;
            ThreadUtils.sout(this, "methodA", "x", x);
        }

        void methodB() {
            c = x * x;
            ThreadUtils.sout(this, "methodB", "c", c);
        }

        void methodC() {
            d = x + 1;
            ThreadUtils.sout(this, "methodC", "d", d);
        }

        void methodD() {
            e = c + d;
            ThreadUtils.sout(this, "methodD", "e", e);
        }
    }

    static class Process2 extends Thread {
        int q, r, s, x;

        public Process2(int s, int q) {
            this.s = s;
            this.q = q;
        }

        @Override
        public void run() {
            methodE();
            methodF();
        }

        private void methodE() {
            x = s - q;
            ThreadUtils.sout(this, "methodE", "x", x);
        }

        private void methodF() {
            r = 2 * x;
            ThreadUtils.sout(this, "methodF", "r", r);
        }
    }

    static class Process1Concurrent extends Process1 {

        public Process1Concurrent(int a, int b) {
            super(a, b);
        }

        @Override
        public void run() {
            methodA();

            Runnable b = () -> methodB();
            Runnable c = () -> methodC();

            Thread threadB = new Thread(b);
            Thread threadC = new Thread(c);

            threadB.start();
            threadC.start();

            joinSilent(threadB);
            joinSilent(threadC);

            methodD();
        }
    }
}
