package ude.masteraise.concurrency.part2.sheet3;

/**
 * Created by nils on 22.06.16.
 */
public class Exercise3_3 {

    public static void main(String[] args) {

//        run_part_a();
//        run_part_b();
        run_part_c();
    }

    static void joinSilent(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(thread.getClass().getSimpleName() + " was interrupted");
            e.printStackTrace();
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

    static class BaseThread extends Thread {

        void sout(String method, String resource, int value) {

            String out = String.format("id: %3d   %20s.%-10s %4s: %3d",
                    this.getId(),
                    this.getClass().getSimpleName(),
                    method,
                    resource,
                    value);
            System.out.println(out);
        }
    }

    static class Process1 extends BaseThread {
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
            sout("methodA", "x", x);
        }

        void methodB() {
            c = x * x;
            sout("methodB", "c", c);
        }

        void methodC() {
            d = x + 1;
            sout("methodC", "d", d);
        }

        void methodD() {
            e = c + d;
            sout("methodD", "e", e);
        }
    }

    static class Process2 extends BaseThread {
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
            sout("methodE", "x", x);
        }

        private void methodF() {
            r = 2 * x;
            sout("methodF", "r", r);
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
