package ude.masteraise.concurrency.part2.sheet3;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import ude.masteraise.concurrency.part2.ThreadUtils;

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
            ThreadUtils.sout(this, "a1");
        }

        private void a2() {
            ThreadUtils.sout(this, "a2");
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
            ThreadUtils.sout(this, "b1");
        }

        private void b2() {
            ThreadUtils.sout(this, "b2");
        }

        private void b3() {
            ThreadUtils.sout(this, "b3");
        }

        private void b4() {
            ThreadUtils.sout(this, "b4");
        }
    }
}
