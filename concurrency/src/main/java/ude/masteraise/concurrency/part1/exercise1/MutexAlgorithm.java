package ude.masteraise.concurrency.exercise1;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This sample contains a implementation of the uppaal tutorial mentioned in "small_tutorial.pdf" in the section
 * 3.2 Mutual Exclusion Algorithm.
 */
public class MutexAlgorithm {

    private static final Logger LOGGER = Logger.getLogger(MutexAlgorithm.class);

    public static void main(String[] args) {

        // shared resources between the automata. these are global variables in uppaal (declarations)
        MutableBoolean req1 = new MutableBoolean();
        MutableBoolean req2 = new MutableBoolean();

        // instantiate automata (system declarations)
        Automaton a1 = new Automaton(req1, req2, 10);
        Automaton a2 = new Automaton(req2, req1, 15);

        // in the sample an id is used to tag one automaton here an instance is used
        a1.other = a2;
        a2.other = a1;

        // run the automata
        a1.start();
        a2.start();
    }

    /**
     * An {@code Automaton} contains the implementation of a template modeled in uppaal. Parameters defined in the
     * uppaal template are mostly used as parameters inside the constructor.
     */
    private static class Automaton extends Thread {

        private static final int MAX_CRITICAL_STUFF_COUNT = 50;

        /**
         * Log format to show current state variables, takes {@code int int boolean boolean int int}
         */
        private static final String CS_LOG_FORMAT = "id: %1$3d"
                + " - turn: %2$3d"
                + " - req_self: %3$5b"
                + " - req_other: %4$5b"
                + " - critical stuff count: %5$" + (int) (Math.log10(MAX_CRITICAL_STUFF_COUNT) + 1) + "d"
                + " - waiting for: %6$4dms";
        private final int workingTime;

        private final MutableBoolean req_self;
        private final MutableBoolean req_other;

        /**
         * automaton that should be turned to. may be {@code this} or {@link #other}.
         */
        private Automaton turn;

        /**
         * work can not be done without the other automaton
         */
        private Automaton other;

        /**
         * Just a count how many times critical work should be done
         */
        private int criticalStuffCount;

        public Automaton(MutableBoolean req_self, MutableBoolean req_other, int workingTime) {
            this.req_self = req_self;
            this.req_other = req_other;
            this.workingTime = workingTime;
        }

        @Override
        public void run() {
            // validation
            if (other == null)
                throw new IllegalStateException("other automaton must be set");

            /*
            inside the sample the automata are able to perform critical stuff for an indefinite amount. therefore
            the critical stuff counter is used to end work and stop the program.
             */
            while (!isCriticalStuffDone()) {

                // see page 5 figure 6 of the tutorial

                // req_self:=1
                req_self.setTrue();
                LOGGER.debug("id: " + getId() + " -> req_self:=1");

                // turn:= ( me == 1 ? 2 : 1 )
                turn = turn == this
                       ? other
                       : this;
                LOGGER.debug("id: " + getId() + " -> turn:=" + turn.getId());

                /*
                wait until turn == me and req_other == 0 and others critical stuff is not done
                cs is checked at this point otherwise it may lead to a deadlock if the other automaton has done its
                stuff and this one is waiting for the appropriate variable set
                 */
                while (turn != this // turn != 1
                        && req_other.isTrue() // req_other != 0: is equivalent to req_other = true
                        && other.isCriticalStuffDone()) ;

                doCriticalStuff();

                // req_self:=0
                req_self.setFalse();
                LOGGER.debug("id: " + getId() + " -> req_self:=0");
            }

        }

        private boolean isCriticalStuffDone() {
            return criticalStuffCount >= MAX_CRITICAL_STUFF_COUNT;
        }

        private void doCriticalStuff() {

            // calculate working time
            long startWaitTime = System.currentTimeMillis();
            long endWaitTime = startWaitTime + workingTime;

            // work
            while (System.currentTimeMillis() < endWaitTime) ;

            // increase work counter
            criticalStuffCount++;

            // log the work
            String log = String.format(CS_LOG_FORMAT, getId(), turn.getId(), req_self.booleanValue(),
                    req_other.booleanValue(), criticalStuffCount, workingTime);
            LOGGER.info(log);
        }
    }
}
