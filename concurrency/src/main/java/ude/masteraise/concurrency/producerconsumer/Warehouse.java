package ude.masteraise.concurrency.producerconsumer;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Nils Verheyen
 * @since 17.07.16 17:34
 */
public class Warehouse
{
    private static final int MAX_ITEM_COUNT = 50;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private final Integer[] items = new Integer[MAX_ITEM_COUNT];
    private int currentItemCount = 0;
    private final AtomicBoolean isOpen = new AtomicBoolean(true);

    public void add(Integer item)
    {
        // critical section
        if (currentItemCount < items.length)
        {
            items[currentItemCount] = item;
            currentItemCount++;
            ThreadUtils.log(Thread.currentThread(), "added", "items", currentItemCount);
        } else
        {
            ThreadUtils.log(Thread.currentThread(), "full");
        }
    }

    public synchronized void addSynchronized(Integer item)
    {
        add(item);
    }

    public Integer consume()
    {

        // critical section
        if (currentItemCount > 0)
        {
            currentItemCount--;
            ThreadUtils.log(Thread.currentThread(), "consumed", "items", currentItemCount);
            return items[currentItemCount];
        } else
        {
            ThreadUtils.log(Thread.currentThread(), "empty");
            return null;
        }
    }

    public synchronized Integer consumeSynchronized()
    {
        return consume();
    }

    public ReentrantReadWriteLock.WriteLock getWriteLock()
    {
        return writeLock;
    }

    public boolean isFull()
    {
        return currentItemCount >= items.length;
    }

    public boolean isEmpty()
    {
        return currentItemCount <= 0;
    }

    public synchronized void open()
    {
        isOpen.set(true);
    }

    public synchronized void close()
    {
        isOpen.set(false);
    }

    public boolean isOpen()
    {
        return isOpen.get();
    }
}
