package ude.masteraise.concurrency.philosophers;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;


/**
 * Created by nils on 15.07.16.
 */
public class Philosopher extends Thread
{
    private final int numEatings;
    private final Fork leftFork;
    private final Fork rightFork;
    private int eatings;
    private int waitings;

    public Philosopher(int number, Fork leftFork, Fork rightFork, int numEatings)
    {
        setName("Philo " + number);
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.numEatings = numEatings;
    }

    @Override
    public void run()
    {
        int i = 1;
        while (eatings < numEatings)
        {
            eat(i);
            i++;
        }
    }

    private void eat(int iteration)
    {
        if (takeForks())
        {
            try
            {
                ThreadUtils.log(this, "eat", "num", iteration);
                eatings++;

            } finally
            {
                leftFork.getLock().unlock();
                rightFork.getLock().unlock();
            }
        } else
        {
            ThreadUtils.log(this, "wait");
            waitings++;
        }
    }

    private boolean takeForks()
    {
        boolean isLeftForkLocked = false;
        boolean isRightForkLocked = false;

        try
        {
            isLeftForkLocked = leftFork.getLock().tryLock();
            if (isLeftForkLocked)
                ThreadUtils.log(this, "took", "fork", leftFork.getNumber());

            isRightForkLocked = rightFork.getLock().tryLock();
            if (isRightForkLocked)
                ThreadUtils.log(this, "took", "fork", rightFork.getNumber());
        } finally
        {
            if (!(isLeftForkLocked && isRightForkLocked))
            {
                if (isLeftForkLocked)
                {
                    leftFork.getLock().unlock();
                    ThreadUtils.log(this, "put", "fork", leftFork.getNumber());
                }
                if (isRightForkLocked)
                {
                    rightFork.getLock().unlock();
                    ThreadUtils.log(this, "put", "fork", rightFork.getNumber());
                }
            }
        }
        return isLeftForkLocked && isRightForkLocked;
    }

    public int getEatings()
    {
        return eatings;
    }

    public int getWaitings()
    {
        return waitings;
    }
}
