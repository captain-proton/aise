package ude.masteraise.concurrency.philosophers;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 15.07.16.
 */
public class PhilosophersApp
{
    public static void main(String[] args)
    {
        int numEatings = RandomUtils.nextInt(10, 20);
        List<Fork> forks = IntStream.range(0, 5)
                .mapToObj(i -> new Fork(i + 1))
                .collect(Collectors.toList());

        List<Philosopher> philos = forks.stream()
                .sorted()
                .map(leftFork -> new Philosopher(leftFork.getNumber(), leftFork, getRightFork(leftFork, forks), numEatings))
                .collect(Collectors.toList());

        philos.forEach(Philosopher::start);

        philos.forEach(ThreadUtils::joinSilent);
        philos.forEach(p ->
        {
            ThreadUtils.log(p, "done", "eatings", p.getEatings());
            ThreadUtils.log(p, "done", "waitings", p.getWaitings());
        });
    }

    private static Fork getRightFork(Fork leftFork, List<Fork> forks)
    {
        int forkIndex = forks.indexOf(leftFork);
        return forkIndex == forks.size() - 1
               ? forks.get(0)
               : forks.get(forkIndex + 1);
    }
}
