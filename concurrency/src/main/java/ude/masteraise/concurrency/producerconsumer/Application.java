package ude.masteraise.concurrency.producerconsumer;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nils Verheyen
 * @since 17.07.16 17:52
 */
public class Application
{
    public static void main(String[] args)
    {
        Optional<String> method = Arrays.stream(args).filter(a -> a.startsWith("method")).findAny();
        if (method.isPresent())
        {
            String chosenMethodArg = method.get();
            final String chosenMethod = chosenMethodArg.split("=")[1];
            switch (chosenMethod)
            {
                case "lock":
                    mainOnLock();
                    break;
                case "sync":
                    mainOnSync();
                    break;
                case "waitNotify":
                    mainOnWaitNotify();
                    break;
            }
        }
    }

    private static void mainOnWaitNotify()
    {
        Warehouse warehouse = new Warehouse();

        int iterations = 1000;
        int numProducers = 3;
        int numConsumers = 3;

        /*
        at the moment the implementation does only work on exactly one producer and one consumer. To get a working
        copy with multiple producers and consumers a solution may be that the warehouse calls consumers on available
        items and producers if it is empty. Another solution may be that the producers call one one or more consumers
        vice versa.
        */
        List<ProducerWaitNotify> producers = IntStream.range(1, numProducers + 1)
                .mapToObj(i -> new ProducerWaitNotify(i, iterations, warehouse))
                .collect(Collectors.toList());
        List<ConsumerWaitNotify> consumers = IntStream.range(1, numConsumers + 1)
                .mapToObj(i -> new ConsumerWaitNotify(i, iterations, warehouse))
                .collect(Collectors.toList());

        producers.forEach(p -> p.setConsumers(consumers));
        consumers.forEach(c -> c.setProducers(producers));

        producers.forEach(Thread::start);
        consumers.forEach(Thread::start);

        ThreadUtils.sleepSilent(10, TimeUnit.SECONDS);
        ThreadUtils.log(Thread.currentThread(), "closingWarehouse");
        warehouse.close();
    }

    private static void mainOnSync()
    {

        Warehouse warehouse = new Warehouse();

        int iterations = 100;
        IntStream.range(1, 3)
                .mapToObj(i -> new ProducerSynchronized(i, iterations, warehouse))
                .forEach(Thread::start);

        IntStream.range(1, 3)
                .mapToObj(i -> new ConsumerSynchronized(i, iterations, warehouse))
                .forEach(Thread::start);
    }

    private static void mainOnLock()
    {

        Warehouse warehouse = new Warehouse();

        int iterations = 100;
        IntStream.range(1, 5)
                .mapToObj(i -> new ProducerLock(i, iterations, warehouse))
                .forEach(Thread::start);

        IntStream.range(1, 4)
                .mapToObj(i -> new ConsumerLock(i, iterations, warehouse))
                .forEach(Thread::start);
    }
}
