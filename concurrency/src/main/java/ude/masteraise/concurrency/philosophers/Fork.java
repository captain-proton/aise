package ude.masteraise.concurrency.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nils on 15.07.16.
 */
public class Fork implements Comparable<Fork>
{
    private final Lock lock = new ReentrantLock();
    private final int number;

    public Fork(int number)
    {
        this.number = number;
    }

    public Lock getLock()
    {
        return lock;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public int compareTo(Fork fork)
    {
        return number > fork.number
               ? 1
               : number == fork.number
                 ? 0
                 : -1;
    }
}
