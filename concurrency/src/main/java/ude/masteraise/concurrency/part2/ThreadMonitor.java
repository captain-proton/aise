package ude.masteraise.concurrency.part2;

import java.util.Arrays;

/**
 * @author Nils Verheyen
 * @since 29.06.16 15:18
 */
public class ThreadMonitor
{

    private static int x = 0;


    public static void main(String[] args)
    {

        Worker[] workers = {new Worker(2), new Worker(-2)};

        Arrays.stream(workers).forEach(w -> new Thread(w).start());
    }

    static class Worker implements Runnable
    {

        private final int delta;
        private final String name;

        public Worker(int delta)
        {
            this.delta = delta;
            this.name = "Worker (" + delta + ")";
        }

        @Override
        public void run()
        {
//             synchronized (Worker.class) {
            synchronized (this)
            {
                for (int i = 0; i < 10; i++)
                {

                    x += delta;
                    System.out.println(name + " " + x);
                }
            }
        }
    }
}
