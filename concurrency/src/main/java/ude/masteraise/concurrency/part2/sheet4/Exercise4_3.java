package ude.masteraise.concurrency.part2.sheet4;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.stream.Stream;

/**
 * Created by nils on 06.07.16.
 */
public class Exercise4_3
{
    public static void main(String[] args)
    {
        Monitor p = new Monitor("P");
        Monitor q = new Monitor("Q");

        Stream.of(new Process('A', p, q, 200, 600),
                new Process('B', p, q, 400, 100),
                new Process('C', p, q, 600, 100),
                new Process('D', p, q, 800, 300)
        ).forEach(Thread::start);
    }

    static class Monitor
    {
        String name;
        int count;

        public Monitor(String name)
        {
            this.name = name;
        }

        synchronized void in(char proc)
        {
            count += 1;
            ThreadUtils.log(Thread.currentThread(), proc + " in  " + name, "count", count);
            while (count < 2)
                ThreadUtils.waitSilent(this);

            count = count >= 2 && count < 7
                    ? count + 7
                    : count - 9;
            ThreadUtils.log(Thread.currentThread(), proc + " out " + name, "count", count);
            notifyAll();
        }
    }

    static class Process extends Thread {
        char proc;

        Monitor p;
        Monitor q;

        int x;
        int y;

        public Process(char proc, Monitor p, Monitor q, int x, int y)
        {
            this.proc = proc;
            this.p = p;
            this.q = q;
            this.x = x;
            this.y = y;
        }

        @Override
        public void run()
        {
            ThreadUtils.sleepSilent(x);
            p.in(proc);
            ThreadUtils.sleepSilent(y);
            q.in(proc);
        }
    }
}
