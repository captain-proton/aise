package ude.masteraise.concurrency.part2.sheet4;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * In this example a train station is simulated where a number of trains want to enter it. Every train is able to stop
 * at every track, but may be has to go over several other requirements. While one train blocks other requirements, trains are not
 * able to pass these requirements.
 */
public class Exercise4_4
{
    /**
     * Number of requirements that are used.
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

    /**
     * Minimum time to stay on a track to release and enter passengers.
     */
    private static final int MIN_ACCESS_TIME = 40;

    /**
     * Maximum time to stay on a track to release and enter passengers.
     */
    private static final int MAX_ACCESS_TIME = 200;

    public static void main(String[] args)
    {
        /*
        In this example a semaphore is used to synchronize the track to different requirements. It is not relevant
        which train goes to which track! It will only block n requirements to track the track.
         */
        Semaphore trackSync = new Semaphore(NUM_TRACKS);

        Track trackOne = new Track(1);
        Track trackTwo = new Track(2);
        Track trackThree = new Track(3);

        List<InOutRequirement> trainARequirements = Stream.of(
                new InOutRequirement(trackOne, trackOne),
                new InOutRequirement(trackTwo, trackOne, trackTwo),
                new InOutRequirement(trackThree, trackOne, trackTwo, trackThree))
                .collect(Collectors.toList());

        List<InOutRequirement> trainBRequirements = Stream.of(
                new InOutRequirement(trackOne, trackOne, trackTwo, trackThree),
                new InOutRequirement(trackTwo, trackOne, trackTwo),
                new InOutRequirement(trackThree, trackOne))
                .collect(Collectors.toList());

        /*
        Trains that are in use by the system. Try out more instances of train to see if the station works even if
        more trains than requirements want to track the station.
         */
        Train trainA = new Train("Train A", trackSync, MAX_NUM_CONNECTIONS, trainARequirements);
        Train trainB = new Train("Train B", trackSync, MAX_NUM_CONNECTIONS, trainBRequirements);

        Stream.of(trainA, trainB).forEach(Thread::start);
    }

    static class Train extends Thread
    {
        private final Semaphore trackSync;
        private final int connections;
        private final List<InOutRequirement> requirements;

        public Train(String name, Semaphore sync, int connections, List<InOutRequirement> requirements)
        {
            super(name);
            this.trackSync = sync;
            this.connections = connections;
            this.requirements = requirements;
        }

        @Override
        public void run()
        {
            // Occupy requirements for the number of defined connections
            IntStream.range(1, connections + 1).forEach(this::occupy);
        }

        private void occupy(int connectionNumber)
        {
            // start by printing that this train wants to access a track
            ThreadUtils.sout(this, "start", "connection", connectionNumber);

            /*
            The track is chosen by randomAccess. It is important which track a train wants to access. If the track
            is not free the train is not able to enter and has to wait until it is free.
             */
            InOutRequirement access = randomAccess();
            Track track = access.getTrack();
            ThreadUtils.sout(this, "wants", track.toString());
            int trackCount = access.getRequirements().size();

            // synchronize access to the track the trains wants to access
            synchronized (track)
            {
                // wait until the track is free
                while (!track.isFree())
                {
                    ThreadUtils.waitSilent(track);
                }
                /*
                This is a critical section. Other trains should not be able to access the track. After this train
                has left the track it must be released.
                 */
                track.setOccupiedBy(this);
            }

            try
            {
                // acquire the needed amount of requirements. if they are not available the thread waits at this point.
                ThreadUtils.acquireSilent(trackSync, trackCount);

                /*
                Start of the critical section. The train has acquired the needed amount of permits and is able to enter
                the train station. If for example 3 requirements are defined and the train needs all 3 to enter,
                then no other train is able to track the station until the train releases the requirements
                (Semaphore.release(n)). There is a random time defined how much time the train needs to access the
                station to simulate the drive in process.
                 */
                int driveInTime = RandomUtils.nextInt(MIN_ACCESS_TIME, MAX_ACCESS_TIME + 1);
                ThreadUtils.sout(this, "driveIn", "blocked", trackCount);
                ThreadUtils.sleepSilent(driveInTime);

                /*
                At this point the train has entered the station and other trains may drive in to tracks that are free.
                 */
                trackSync.release(trackCount);
                ThreadUtils.sout(this, "in", "released", trackCount);

                // wait for passengers to leave and enter for a randomAccess time
                int waitTime = RandomUtils.nextInt(MIN_STAY_TIME, MAX_STAY_TIME + 1);
                ThreadUtils.sleepSilent(waitTime);

                /*
                Once the train wants to leave the station it must access the amount of tracks it needed to drive into
                the station. There is a random time defined how much time the train needs to leave the
                station to simulate the drive out process.
                 */
                ThreadUtils.acquireSilent(trackSync, trackCount);
                ThreadUtils.sout(this, "driveOut", "blocked", trackCount);

                int driveOutTime = RandomUtils.nextInt(MIN_ACCESS_TIME, MAX_ACCESS_TIME + 1);
                ThreadUtils.sleepSilent(driveOutTime);

            } finally
            {
                /*
                Finally release all requirements that were acquired. This part marks the end of the critical section.
                Other trains may track the station if they were previously stopped.
                 */
                trackSync.release(trackCount);
                ThreadUtils.sout(this, "out", track.toString());
            }

            /*
            Finally the train has left the station and the track itself must be released. One other train is able to
            access the station and has to be notified if it is waiting.
             */
            synchronized (track)
            {
                track.release();
                track.notify();
            }

        }

        private InOutRequirement randomAccess()
        {
            return requirements.get(RandomUtils.nextInt(0, requirements.size()));
        }
    }

    /**
     * A <code>Track</code> is a shared resources between the different {@linkplain Train}s.
     */
    static class Track
    {

        private final Integer number;
        Train occupiedBy;

        public Track(Integer number)
        {
            this.number = number;
        }

        public synchronized boolean isFree()
        {
            return occupiedBy == null;
        }

        public synchronized void release()
        {
            this.occupiedBy = null;
        }

        public synchronized void setOccupiedBy(Train occupiedBy)
        {
            this.occupiedBy = occupiedBy;
        }

        @Override
        public String toString()
        {
            return String.format("Track %d", number);
        }
    }

    /**
     * An <code>InOutRequirement</code> contains the required {@linkplain Track}s which have to be blocked when a
     * {@linkplain Train} wants to access the track.
     */
    static class InOutRequirement
    {
        /** Which track the a train wants to access. */
        private final Track track;

        /** Tracks that must be blocked, so that the train is able to access the track. */
        private final List<Track> requirements;

        public InOutRequirement(Track track, List<Track> requirements)
        {
            this.track = track;
            this.requirements = requirements;
        }

        public InOutRequirement(Track track, Track... requirements)
        {
            this(track, Stream.of(requirements).collect(Collectors.toList()));
        }

        public Track getTrack()
        {
            return track;
        }

        public List<Track> getRequirements()
        {
            return requirements;
        }
    }
}
