package ude.masteraise.concurrency.part2;

import org.apache.log4j.Logger;

/**
 * Created by nils on 22.06.16.
 */
public class ThreadUtils {

    private static final Logger LOG = Logger.getLogger(ThreadUtils.class);

    public static Thread newInstance(Class<? extends Thread> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            LOG.error("could not instantiate thread " + clazz.getSimpleName(), e);
        } catch (IllegalAccessException e) {
            LOG.error("could not call default constructor on thread " + clazz.getSimpleName(), e);
        }
        return null;
    }

    public static void joinSilent(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOG.error(thread.getClass().getSimpleName() + " was interrupted", e);
        }
    }

    public static void sleepSilent(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOG.error(Thread.currentThread().getName() + " was interrupted", e);
        }
    }

    public static void sout(Thread t, String method) {

        String out = String.format("%20s.%-10s",
                t.getClass().getSimpleName(),
                method);
        LOG.info(out);
    }

    public static void sout(Thread t, String method, String resource, int value) {

        String out = String.format("%20s.%-10s %10s: %3d",
                t.getClass().getSimpleName(),
                method,
                resource,
                value);
        LOG.info(out);
    }

    public static void sout(Thread t, String method, String resource, int value, long millis) {

        String out = String.format("%20s.%-10s %10s: %3d  %10d",
                t.getClass().getSimpleName(),
                method,
                resource,
                value,
                millis);
        LOG.info(out);
    }
}
