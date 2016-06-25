package ude.masteraise.concurrency.part2.extrasheet2;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by nils on 24.06.16.
 */
public class ExtraExercise2_1 {

    private static final int DELTA_TIMES = 100000000;

    private int x;
    private AtomicInteger atomicX;

    public ExtraExercise2_1(int x, AtomicInteger atomicX) {
        this.x = x;
        this.atomicX = atomicX;
    }

    public static void main(String[] args) {

        run_original_exercise();
//        run_original_exercise_synchronized();
//        run_exercise_on_atomic();
    }

    private static void run_original_exercise() {
        ExtraExercise2_1 main = new ExtraExercise2_1(100, null);

        Process p1 = new Process(2, main, "p1");
        Process p2 = new Process(-2, main, "p2");

        main.run(p1, p2);
    }

    private int getResourceValue() {
        return atomicX != null
               ? atomicX.intValue()
               : x;
    }

    private static void run_original_exercise_synchronized() {
        ExtraExercise2_1 main = new ExtraExercise2_1(100, null);

        Process p1 = new SynchronizedProcess(2, main, "p1");
        Process p2 = new SynchronizedProcess(-2, main, "p2");

        main.run(p1, p2);
    }

    private static void run_exercise_on_atomic() {
        ExtraExercise2_1 main = new ExtraExercise2_1(Integer.MIN_VALUE, new AtomicInteger(100));

        Process p1 = new ProcessOnAtomicInt(2, main, "p1");
        Process p2 = new ProcessOnAtomicInt(-2, main, "p2");

        main.run(p1, p2);
    }

    private void run(Process... processes) {
        ThreadUtils.sout(Thread.currentThread(), "main", "        x", this.getResourceValue());

        Arrays.stream(processes).forEach(Thread::start);

        while (Arrays.stream(processes).anyMatch(Thread::isAlive)) {
            ThreadUtils.sout(Thread.currentThread(), "main", "current x", this.getResourceValue());
        }
        ThreadUtils.sout(Thread.currentThread(), "main", "  final x", this.getResourceValue());
    }

    static class Process extends Thread {
        int delta;
        ExtraExercise2_1 main;
        String name;

        public Process(int delta, ExtraExercise2_1 main, String name) {
            this.delta = delta;
            this.main = main;
            this.name = name;
        }

        @Override
        public void run() {
            IntStream.range(0, DELTA_TIMES).forEach(i -> main.x += delta);
        }
    }

    static class SynchronizedProcess extends Process {

        public SynchronizedProcess(int delta, ExtraExercise2_1 main, String name) {
            super(delta, main, name);
        }

        @Override
        public void run() {
            IntStream.range(0, DELTA_TIMES).forEach(i -> {
                synchronized (main) {
                    main.x += delta;
                }
            });
        }
    }

    static class ProcessOnAtomicInt extends Process {

        public ProcessOnAtomicInt(int delta, ExtraExercise2_1 main, String name) {
            super(delta, main, name);
        }

        @Override
        public void run() {
            IntStream.range(0, DELTA_TIMES).forEach(i -> main.atomicX.addAndGet(delta));
        }
    }
}
