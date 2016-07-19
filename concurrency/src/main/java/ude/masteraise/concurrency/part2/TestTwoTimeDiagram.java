package ude.masteraise.concurrency.part2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 19.07.16 08:24
 */
public class TestTwoTimeDiagram
{

    public static void main(String[] args)
    {
        Monitor m1 = new Monitor("M1");
        Monitor m2 = new Monitor("M2");

        List<Process> processes = IntStream.range(1, 4)
                .mapToObj(i -> new Process(i, m1, m2))
                .collect(Collectors.toList());

        processes.forEach(Thread::start);
    }

    static class Monitor
    {
        int n;
        String name;

        public Monitor(String name)
        {
            this.name = name;
        }

        public synchronized void in(int index)
        {
            n += 2;
            // <time> <proc_index> IN <monitor> n = <n>
            ThreadUtils.log(Thread.currentThread(), "IN", name + " n = ", n);
            ThreadUtils.sleepSilent(100 * index);

            while (n < 3)
            {
                ThreadUtils.log(Thread.currentThread(), "WAIT", name + " n = ", n);
                ThreadUtils.waitSilent(this);
            }
            n += n >= 4 && n < 10
                 ? 1
                 : -1;

            ThreadUtils.log(Thread.currentThread(), "OUT", name + " n = ", n);
            notifyAll();
        }
    }

    static class Process extends Thread
    {
        int index;
        Monitor m1;
        Monitor m2;

        public Process(int index, Monitor m1, Monitor m2)
        {
            super("P" + index);
            this.index = index;
            this.m1 = m1;
            this.m2 = m2;
        }

        @Override
        public void run()
        {
            ThreadUtils.sleepSilent(100 * index);
            m1.in(index);
            ThreadUtils.sleepSilent(100 * index);
            m2.in(index);
        }
    }
}
