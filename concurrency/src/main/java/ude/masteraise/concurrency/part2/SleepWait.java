package ude.masteraise.concurrency.part2;

import java.util.stream.Stream;

/**
 * This sample should show the differences between sleep and wait called inside a monitor used by processes.
 */
public class SleepWait
{
    private static long startTime;

    public static void main(String[] args)
    {
        startTime = System.currentTimeMillis();
        runSleep();
        startTime = System.currentTimeMillis();
        runWait();
    }

    public static long getRuntime()
    {
        return System.currentTimeMillis() - startTime;
    }

    private static void runSleep()
    {
        ThreadUtils.log(Thread.currentThread(), "runSleep", "start");
        Monitor m = new SleepMonitor("SleepMonitor");
        Process p1 = new Process(1, 100, 400, m);
        Process p2 = new Process(2, 200, 100, m);

        ThreadUtils.startAndJoin(p1, p2);

        ThreadUtils.log(Thread.currentThread(), "runSleep", "finished");
    }

    private static void runWait()
    {
        ThreadUtils.log(Thread.currentThread(), "runWait", "start");
        Monitor m = new WaitMonitor("WaitMonitor");
        Process p1 = new Process(1, 100, 400, m);
        Process p2 = new Process(2, 200, 100, m);

        ThreadUtils.startAndJoin(p1, p2);
        ThreadUtils.log(Thread.currentThread(), "runWait", "finished");
    }

    static class SleepMonitor implements Monitor
    {
        String name;

        public SleepMonitor(String name)
        {
            this.name = name;
        }

        public synchronized void in(Process p)
        {
            ThreadUtils.log(Thread.currentThread(), "in", "start", p.id, getRuntime());
            ThreadUtils.sleepSilent(p.sleepTimeOnMonitor);
            ThreadUtils.log(Thread.currentThread(), "in", "end", p.id, getRuntime());
        }
    }

    static class WaitMonitor implements Monitor
    {
        String name;
        boolean isFirstWaiting;

        public WaitMonitor(String name)
        {
            this.name = name;
            this.isFirstWaiting = false;
        }

        public synchronized void in(Process p)
        {
            ThreadUtils.log(Thread.currentThread(), "in", "start", p.id, getRuntime());
            if (!isFirstWaiting)
            {
                isFirstWaiting = true;
                ThreadUtils.log(Thread.currentThread(), "in", "wait", p.id, getRuntime());
                ThreadUtils.waitSilent(this);
            }
            ThreadUtils.log(Thread.currentThread(), "in", "end", p.id, getRuntime());
            notifyAll();
        }
    }

    static class Process extends Thread
    {
        private final int id;
        private final Monitor monitor;
        private final long startSleepTime;
        private final long sleepTimeOnMonitor;

        public Process(int id, long startSleepTime, long sleepTimeOnMonitor, Monitor monitor)
        {
            super("P" + id);
            this.id = id;
            this.startSleepTime = startSleepTime;
            this.sleepTimeOnMonitor = sleepTimeOnMonitor;
            this.monitor = monitor;
        }

        @Override
        public void run()
        {
            ThreadUtils.log(this, "run", "start", id, getRuntime());
            ThreadUtils.sleepSilent(startSleepTime);
            ThreadUtils.log(this, "run", "slept", id, getRuntime());
            monitor.in(this);
            ThreadUtils.log(this, "run", "end", id, getRuntime());
        }
    }

    interface Monitor
    {
        void in(Process p);
    }
}
