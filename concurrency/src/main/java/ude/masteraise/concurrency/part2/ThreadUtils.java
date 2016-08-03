package ude.masteraise.concurrency.part2;

import org.apache.log4j.Logger;
import ude.masteraise.concurrency.smoker.Smoker;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Stream;

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
     * Calls {@linkplain Thread#sleep(long)} and ignores the thrown {@linkplain InterruptedException}
     */
    public static void sleepSilent(long amount, TimeUnit unit)
    {
        try
        {
            Thread.sleep(TimeUnit.MILLISECONDS.convert(amount, unit));
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
     * Calls {@linkplain Object#wait(long)} on given object and ignores the thrown {@linkplain InterruptedException}
     */
    public static void waitSilent(Object o, long timeout)
    {
        try
        {
            o.wait(timeout);
        } catch (InterruptedException e)
        {
        }
    }

    /**
     * Calls {@linkplain Condition#await()} on given condition and ignores the thrown {@linkplain InterruptedException}
     */
    public static void awaitSilent(Condition condition)
    {
        try
        {
            condition.await();
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

    public static boolean tryLockSilent(Lock lock, long time, TimeUnit timeUnit)
    {
        try
        {
            return lock.tryLock(time, timeUnit);
        } catch (InterruptedException e)
        {
            return false;
        }
    }

    public static void startAndJoin(Thread...threads)
    {
        Stream.of(threads).forEach(Thread::start);
        Stream.of(threads).forEach(ThreadUtils::joinSilent);
    }

    public static void handleInterrupt(Thread thread)
    {
        thread.interrupt();
        log(thread, "interrupted");
    }
}
