package ude.masteraise.concurrency.part2.sheet3;

import ude.masteraise.concurrency.part2.ThreadUtils;

/**
 * Created by nils on 22.06.16.
 */
public class Exercise3_3
{

    static int a, b, c, d, e;
    static int q, r, s, x;

    public static void main(String[] args)
    {

        run_part_a();
//        run_part_b();
//        run_part_c();
    }

    private static void run_part_a()
    {

        Process1 p1 = new Process1();
        Process2 p2 = new Process2();

        p1.start();
        p2.start();
    }

    private static void run_part_b()
    {

        Process1 p1 = new Process1();
        Process2 p2 = new Process2();

        p2.start();
        ThreadUtils.log(Thread.currentThread(), "waiting for p2");
        ThreadUtils.joinSilent(p2);
        ThreadUtils.log(Thread.currentThread(), "p2 finished");
        p1.start();
    }

    private static void run_part_c()
    {

        Process1 p1 = new Process1Concurrent();
        Process2 p2 = new Process2();

        p1.start();
        p2.start();
    }

    static class Process1 extends Thread
    {

        public Process1()
        {
            a = 2;
            b = 3;
        }

        @Override
        public void run()
        {

            methodA();
            methodB();
            methodC();
            methodD();
        }

        void methodA()
        {
            x = a + b;
            ThreadUtils.log(this, "methodA", "x", x);
        }

        void methodB()
        {
            c = x * x;
            ThreadUtils.log(this, "methodB", "c", c);
        }

        void methodC()
        {
            d = x + 1;
            ThreadUtils.log(this, "methodC", "d", d);
        }

        void methodD()
        {
            e = c + d;
            ThreadUtils.log(this, "methodD", "e", e);
        }
    }

    static class Process2 extends Thread
    {

        public Process2()
        {
            s = 20;
            q = 10;
        }

        @Override
        public void run()
        {
            methodE();
            methodF();
        }

        private void methodE()
        {
            x = s - q;
            ThreadUtils.log(this, "methodE", "x", x);
        }

        private void methodF()
        {
            r = 2 * x;
            ThreadUtils.log(this, "methodF", "r", r);
        }
    }

    static class Process1Concurrent extends Process1
    {

        @Override
        public void run()
        {
            methodA();

            Runnable b = () -> methodB();
            Runnable c = () -> methodC();

            Thread threadB = new Thread(b);
            Thread threadC = new Thread(c);

            threadB.start();
            threadC.start();

            ThreadUtils.joinSilent(threadB);
            ThreadUtils.joinSilent(threadC);

            methodD();
        }
    }
}
