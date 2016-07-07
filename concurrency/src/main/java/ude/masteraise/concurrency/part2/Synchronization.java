package ude.masteraise.concurrency.part2;

import java.util.stream.Stream;

/**
 * The <code>Synchronization</code> is meant to show that a synchronization block on class objects taken from
 * <code>Object.class</code> differs from the use of single objects (<code>new Object()</code>).
 */
public class Synchronization
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        /*
        Use sync on different objects. Inside the log you can see that different worker instances are able to
        call "started sync" before "ending sync". That means that each worker is inside the sync block.
        */
        Stream.of(
                new Worker(new Object(), startTime),
                new Worker(new Object(), startTime),
                new Worker(new Object(), startTime)
        ).forEach(Thread::start);

        /*
        Use sync on the class object of Object. Now every "started sync" is later followed by an "ending sync".
        Without an "ending sync" another thread is not able to access the sync block.

        Object.class does not create new objects! It only returns the Java Class object that defines the class.
        Class<Synchronization> executingClass = Synchronization.class;
        */
//        Stream.of(
//                new Worker(Object.class, startTime),
//                new Worker(Object.class, startTime),
//                new Worker(Object.class, startTime)
//        ).forEach(Thread::start);
    }

    /**
     * A <code>Worker</code> is a simple thread that synchronizes on a specific object and does some work inside
     * the sync block.
     */
    static class Worker extends Thread
    {
        private final Object lock;
        private final long startTime;

        public Worker(Object lock, long startTime)
        {
            this.lock = lock;
            this.startTime = startTime;
        }

        @Override
        public void run()
        {
            // log the start of the thread
            log("run");

            // multiple threads are blocked at this point if they use the same lock object.
            synchronized (lock)
            {
                // log the start of the sync block
                log("started sync");

                // simulate some work
                try { sleep(100); } catch (InterruptedException e) { }

                // log the end of the sync block
                log("ending sync");
            }
            log("finished");
        }

        void log(String msg)
        {
            /*
            sync System.out otherwise it may happen that output on the console indicates misleading calculated time.
            Example: two threads use the log method. the first one is started and calls log("run"). time is calculated
            and print out to console. meanwhile thread two also calls log("run") and time is calculated. the cpu stops
            thread two at this point, thread one gains control and calls log("started sync"). time is calculated and
            print out to console. the passed time is larger than on the first call on "run". then thread two regains
            control and prints out its message. the time is already calculated! the output contains a time that
            is before the time of thread one that has output "started sync".

            This does not happen if the calculation and output is synchronized with the System.out. Two threads
            are not able to calculate and output on the same time.
            */
            synchronized (System.out)
            {
                long time = System.currentTimeMillis();
                System.out.println(String.format("%5d ms - %10s - %s", time - startTime, getName(), msg));
            }
        }
    }
}
