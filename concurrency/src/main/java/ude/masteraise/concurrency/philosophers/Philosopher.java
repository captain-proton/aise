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
    private int eatingCount;
    private int thinkingCount;
    private int waitingCount;

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
        while (eatingCount < numEatings)
        {
            think(i);
            eat(i);
            i++;
        }
    }

    private void think(int iteration)
    {
        int time = RandomUtils.nextInt(0, 10);
        ThreadUtils.log(this, "think", "time", time);
        ThreadUtils.sleepSilent(time);
        thinkingCount++;
    }

    private void eat(int iteration)
    {
        while (!takeForks())
        {
            ThreadUtils.log(this, "wait");
            waitingCount++;
        }
        try
        {
            ThreadUtils.log(this, "eat", "num", iteration);
            eatingCount++;
            ThreadUtils.sleepSilent(RandomUtils.nextInt(0, 10));

        } finally
        {
            leftFork.getLock().unlock();
            rightFork.getLock().unlock();
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

    public int getEatingCount()
    {
        return eatingCount;
    }

    public int getWaitingCount()
    {
        return waitingCount;
    }

    public int getThinkingCount()
    {
        return thinkingCount;
    }
}
