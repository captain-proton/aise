package ude.masteraise.concurrency.part2.sheet4;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * In this example a train station is simulated where a number of trains want to enter it. Every train is able to stop
 * at every track, but may be has to go over several other tracks. While one train blocks other tracks, trains are not
 * able to pass these tracks.
 */
public class Exercise4_4
{
    /**
     * Number of tracks that are used.
     */
    private static final int NUM_TRACKS = 3;

    /**
     * Maximum number of how many times one train accesses the train station.
     */
    private static final int MAX_NUM_CONNECTIONS = 5;

    /**
     * Minimum time to stay on a track to release and enter passengers.
     */
    private static final int MIN_STAY_TIME = 100;

    /**
     * Maximum time to stay on a track to release and enter passengers.
     */
    private static final int MAX_STAY_TIME = 1000;

    public static void main(String[] args)
    {
        /*
        In this example a semaphore is used to synchronize the access to different tracks. It is not relevant
        which train goes to which track! It will only block n tracks to access the track.
         */
        Semaphore trackSync = new Semaphore(NUM_TRACKS);

        /*
        Trains that are in use by the system. Try out more instances of train to see if the station works even if
        more trains than tracks want to access the station.
         */
        Stream.of("A", "B")
                .map(name -> new Train("Train " + name, trackSync, MAX_NUM_CONNECTIONS))
                .forEach(Thread::start);
    }

    static class Train extends Thread
    {
        private final Semaphore trackSync;
        private final int connections;

        public Train(String name, Semaphore sync, int connections)
        {
            super(name);
            this.trackSync = sync;
            this.connections = connections;
        }

        @Override
        public void run()
        {
            // Occupy tracks for the number of defined connections
            IntStream.range(1, connections + 1).forEach(this::occupy);
        }

        private void occupy(int connectionNumber)
        {
            // start by printing that this train wants to occupy a track
            ThreadUtils.sout(this, "occupy", "connection", connectionNumber);
            ThreadUtils.sout(this, "available", "permits", trackSync.availablePermits());

            /*
            the track is chosen by random. It is not important which track a train wants to occupy, but the number of
            tracks it needs.
             */
            int trackCount = RandomUtils.nextInt(1, NUM_TRACKS + 1);
            ThreadUtils.sout(this, "tryIn", "needs", trackCount);

            try
            {

                // acquire the needed amount of tracks. if they are not available the thread waits at this point.
                ThreadUtils.acquireSilent(trackSync, trackCount);

                /*
                Start of the critical section. The train has acquired the needed amount of permits and is able to access
                the train station. If for example 3 tracks are defined and the train needs all 3 to enter, then no other
                train is able to access the station until the train releases the tracks (Semaphore.release(n)). If the train
                has stopped at one track and another train wants to access a different track, the tracks have to be
                defined as separate objects and the access to them has to be synchronized.
                 */
                ThreadUtils.sout(this, "driveIn", "acquired", trackCount);

                // wait for passengers to leave and enter for a random time
                int waitTime = RandomUtils.nextInt(MIN_STAY_TIME, MAX_STAY_TIME + 1);
                ThreadUtils.sout(this, "wait", "time", waitTime);
                ThreadUtils.sleepSilent(waitTime);

            } finally
            {
                /*
                Finally release all tracks that were acquired. This part marks the end of the critical section.
                Other trains may access the station if they were previously stopped.
                 */
                ThreadUtils.sout(this, "driveOut");
                trackSync.release(trackCount);
                ThreadUtils.sout(this, "out", "released", trackCount);
            }

        }
    }
}
