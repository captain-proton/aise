package ude.masteraise.concurrency.part2;

import org.apache.commons.lang3.RandomUtils;

import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 04.07.16 14:40
 */
public class ConstructorTest
{
    /** number of threads that will be created */
    private static final int MAX_THREADS = 10;

    public static void main(String[] args)
    {
        ThreadUtils.log(Thread.currentThread(), "main()");

        /*
        mapToObj and forEach are not called in order! Some threads start immediately after the instance was created.
         */
        IntStream.range(0, MAX_THREADS) // some numbers
                .mapToObj(i -> ThreadUtils.newInstance(SomeThread.class)) // new threads by count
                .forEach(Thread::start); // start them
    }

    static class SomeThread extends Thread
    {
        public SomeThread()
        {
            // this is called inside the thread where the constructor was called
            ThreadUtils.log(this, "init");
        }

        @Override
        public void run()
        {
            // omg work to do
            ThreadUtils.log(this, "running");

            // simulate long work
            ThreadUtils.sleepSilent(RandomUtils.nextInt(0, 500));

            // finally  ...
            ThreadUtils.log(this, "finished");
        }
    }
}
