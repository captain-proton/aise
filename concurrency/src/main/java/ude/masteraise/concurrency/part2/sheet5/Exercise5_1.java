package ude.masteraise.concurrency.part2.sheet5;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.stream.IntStream;

/**
 * Created by nils on 11.07.16.
 */
public class Exercise5_1
{
    public static void main(String[] args)
    {
        int numProcs = 3;

        // channel contains values which processes have done a()
        Channel channel = new Channel(numProcs);

        // create processes and start
        IntStream.range(0, numProcs)
                .mapToObj(i -> new Process(i, channel))
                .forEach(Thread::start);
    }

    static class Process extends Thread
    {
        private final int id;
        private final Channel channel;

        public Process(int id, Channel channel)
        {
            this.id = id;
            this.channel = channel;
            setName("proc " + id);
        }

        @Override
        public void run()
        {
            a();
            channel.setFinished(id);

            synchronized (channel)
            {
                // wait until two others have done a()
                while (channel.othersDone(id) < 2)
                {
                    ThreadUtils.log(this, "waiting");

                    // timeout is needed cause notifications (setFinished) may have gone into the deep
                    ThreadUtils.waitSilent(channel, 10);
                    ThreadUtils.log(this, "up");
                }
            }
            b();
        }

        private void a()
        {
            ThreadUtils.log(this, "a()");
            yield();
        }

        private void b()
        {
            ThreadUtils.log(this, "b()");
        }
    }

    static class Channel
    {
        boolean[] finished;

        public Channel(int numFinished)
        {
            this.finished = new boolean[numFinished];
        }

        synchronized void setFinished(int id)
        {
            finished[id] = true;
            notifyAll();
        }

        synchronized long othersDone(int id)
        {
            return IntStream.range(0, finished.length)
                    .filter(i -> i != id)
                    .filter(i -> finished[i])
                    .count();
        }
    }
}
