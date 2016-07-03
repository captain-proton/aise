package ude.masteraise.concurrency.part2.sheet4;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by nils on 30.06.16.
 */
public class Exercise4_2
{
    private static final int STACK_CAPACITY = 30;
    private static final int WRITER_ITERATIONS = 10;
    private static final int READER_ITERATIONS = WRITER_ITERATIONS;

    public static void main(String[] args)
    {
        Stack<Integer> stack = new Stack();

        Thread[] threads = {
                new Writer("Writer", stack, WRITER_ITERATIONS),
                new Reader("Reader", stack, READER_ITERATIONS)
        };

        // start each customer
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static class Writer extends Thread
    {
        private static final int MIN_GENERATED_VALUE = 1;
        private static final int MAX_GENERATED_VALUE = 200;

        private final Stack<Integer> stack;
        private final int iterations;

        public Writer(String name, Stack<Integer> stack, int iterations)
        {
            super(name);
            this.stack = stack;
            this.iterations = iterations;
        }

        @Override
        public void run()
        {
            IntStream.range(0, iterations).forEach(this::write);
        }

        private void write(int iteration)
        {
            synchronized (stack)
            {
                /*
                see http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#wait-long-
                why this has to be a loop.
                wait until items can be put into the stack
                 */
                while (stack.size() >= STACK_CAPACITY)
                {
                    ThreadUtils.waitSilent(stack);
                }

                // generate random to put into the stack
                int random = RandomUtils.nextInt(MIN_GENERATED_VALUE, MAX_GENERATED_VALUE + 1);

                // push value and print it
                stack.push(random);
                ThreadUtils.sout(this, "write", "value", random);

                // call notify on stack so that consumers may be put out of their wait
                stack.notify();
            }
        }
    }

    private static class Reader extends Thread
    {

        private final Stack<Integer> stack;
        private final int iterations;

        public Reader(String name, Stack stack, int iterations)
        {
            super(name);
            this.stack = stack;
            this.iterations = iterations;
        }

        @Override
        public void run()
        {
            IntStream.range(0, iterations).forEach(this::read);
        }

        private void read(int iteration)
        {
            synchronized (stack)
            {
                // wait until items can be consumed of the stack
                while (stack.size() <= 0)
                {
                    ThreadUtils.waitSilent(stack);
                }

                // consume and print
                Integer value = stack.pop();
                ThreadUtils.sout(this, "read", "value", value);

                // call notify on stack so that producers may be put out of their wait
                stack.notify();
            }
        }
    }
}
