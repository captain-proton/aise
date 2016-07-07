package ude.masteraise.concurrency.part2;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by nils on 22.06.16.
 */
public class ThreadUtils
{

    private static final Logger LOG = Logger.getLogger(ThreadUtils.class);

    public static Thread newInstance(Class<? extends Thread> clazz)
    {
        try
        {
            return clazz.newInstance();
        } catch (InstantiationException e)
        {
            LOG.error("could not instantiate thread " + clazz.getSimpleName(), e);
        } catch (IllegalAccessException e)
        {
            LOG.error("could not call default constructor on thread " + clazz.getSimpleName(), e);
        }
        return null;
    }

    /**
     * Calls {@linkplain Thread#join()} and ignores the thrown {@linkplain InterruptedException}
     */
    public static void joinSilent(Thread thread)
    {
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
        }
    }

    /**
     * Calls {@linkplain Thread#sleep(long)} and ignores the thrown {@linkplain InterruptedException}
     */
    public static void sleepSilent(long millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException e)
        {
        }
    }

    /**
     * Calls {@linkplain Object#wait()} on given object and ignores the thrown {@linkplain InterruptedException}
     */
    public static void waitSilent(Object o)
    {
        try
        {
            o.wait();
        } catch (InterruptedException e)
        {
        }
    }

    /**
     * Calls {@linkplain Semaphore#acquire(int)} and ignores the thrown {@linkplain InterruptedException}
     */
    public static void acquireSilent(Semaphore semaphore, int permits)
    {
        try
        {
            semaphore.acquire(permits);
        } catch (InterruptedException e)
        {
        }
    }

    public static void log(Thread t, String method)
    {

        String out = String.format("%20s.%-10s",
                t.getName(),
                method);
        LOG.info(out);
    }

    public static void log(Thread t, String method, String resource)
    {

        String out = String.format("%20s.%-10s %15s",
                t.getName(),
                method,
                resource);
        LOG.info(out);
    }

    public static void log(Thread t, String method, String resource, int value)
    {

        String out = String.format("%20s.%-10s %15s: %4d",
                t.getName(),
                method,
                resource,
                value);
        LOG.info(out);
    }

    public static void log(Thread t, String method, String resource, int value, long millis)
    {

        String out = String.format("%20s.%-10s %15s: %3d  %10d ms",
                t.getName(),
                method,
                resource,
                value,
                millis);
        LOG.info(out);
    }
}
