package ude.masteraise.concurrency.part2.extrasheet2;

import ude.masteraise.concurrency.part2.BaseThread;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.stream.Stream;

/**
 * Created by nils on 24.06.16.
 */
public class ExtraExercise2_3 {

    public static void main(String[] args) {

        ProcessSystem monitor = new ProcessSystem("M");
        Process p1 = new Process("p1", 1, monitor);
        Process p2 = new Process("p2", 2, monitor);
        Process p4 = new Process("p4", 4, monitor);

        p1.other = p4;
        p2.other = p1;
        p4.other = p2;

        monitor.setStartTime(System.nanoTime());
        Stream.of(p1, p2, p4).forEach(Thread::start);
    }

    static class ProcessSystem {
        String name;
        long startTime;

        public ProcessSystem(String name) {
            this.name = name;
        }

        synchronized void in(Process p) {
            ThreadUtils.sout(p, "in", "n", p.x);
            while (p.x == 2) {
                ThreadUtils.sout(p, "in", "n", p.x, (System.nanoTime() - startTime) / 1000000);
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }

        synchronized void out(Process p) {
            ThreadUtils.sout(p, "out", "n", p.x);
            notifyAll();
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
    }

    static class Process extends BaseThread {
        int x;
        Process other;
        ProcessSystem monitor;

        public Process(int x) {
            super("p" + x);
            this.x = x;
        }

        public Process(String name, int x) {
            this(x);
            setName(name);
        }

        public Process(String name, int x, ProcessSystem monitor) {
            this(name, x);
            this.monitor = monitor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                ThreadUtils.sleepSilent(100 * x);
                monitor.in(this);

                try {
                    sleep(200);
                    other.x++;
                    x++;
                } catch (InterruptedException e) {
                    other.x++;
                }
                monitor.out(this);
            }
        }
    }
}
