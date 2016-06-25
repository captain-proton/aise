package ude.masteraise.concurrency.part2;

/**
 * Created by nils on 24.06.16.
 */
public abstract class BaseThread extends Thread {
    public BaseThread() {
    }

    public BaseThread(Runnable target) {
        super(target);
    }

    public BaseThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public BaseThread(String name) {
        super(name);
    }

    public BaseThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public BaseThread(Runnable target, String name) {
        super(target, name);
    }

    public BaseThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public BaseThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

    protected void sleepSilent(long millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public void waitSilent() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }
}
