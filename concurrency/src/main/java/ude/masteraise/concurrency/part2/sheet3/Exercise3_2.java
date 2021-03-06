package ude.masteraise.concurrency.part2.sheet3;

import org.apache.log4j.Logger;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;

/**
 * Created by nils on 22.06.16.
 */
public class Exercise3_2
{

    private static final Logger LOG = Logger.getLogger(Exercise3_2.class);

    static int number;

    public static void main(String[] unbenutzt)
    {

        run_part_a();
//        run_part_b();
//        run_part_c();
//        run_part_d();
    }

    private static void run_part_a()
    {
        run(Plus.class, Minus.class);
    }

    private static void run_part_b()
    {
        run(PlusYield.class, MinusYield.class);
    }

    private static void run_part_c()
    {
        run(PlusSleep.class, MinusSleep.class);
    }

    private static void run_part_d()
    {
        run(PlusPrio.class, MinusPrio.class);
    }

    private static void run(Class<? extends Thread>... threadClasses)
    {

        number = 0;
        Arrays.stream(threadClasses)
                .map(ThreadUtils::newInstance)
                .filter(t -> t != null)
                .forEach(Thread::start);
    }

    abstract static class Worker extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                work();
                ThreadUtils.log(this, "run", "number", number);
                finish();
            }
        }

        abstract void work();

        void finish()
        {
        }
    }

    public static class Plus extends Worker
    {
        @Override
        void work()
        {
            Exercise3_2.number++;
        }
    }

    public static class Minus extends Worker
    {

        @Override
        void work()
        {
            Exercise3_2.number--;
        }
    }


    public static class PlusYield extends Plus
    {

        @Override
        void finish()
        {
            Thread.yield();
        }
    }

    public static class MinusYield extends Minus
    {

        @Override
        void finish()
        {
            Thread.yield();
        }
    }

    public static class PlusSleep extends Plus
    {
        final static int SLEEP_TIME = 5;

        @Override
        void finish()
        {
            ThreadUtils.sleepSilent(SLEEP_TIME);
        }
    }

    public static class MinusSleep extends Minus
    {
        final static int SLEEP_TIME = 2;

        @Override
        void finish()
        {
            ThreadUtils.sleepSilent(SLEEP_TIME);
        }
    }

    public static class PlusPrio extends Plus
    {
        public PlusPrio()
        {
            setPriority(1);
        }
    }

    public static class MinusPrio extends Minus
    {
        public MinusPrio()
        {
            setPriority(10);
        }
    }
}
