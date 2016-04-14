package concurrency.exercise1;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nils Verheyen
 * @since 13.04.16 20:29
 */
public class Main {

    private static final SecureRandom WAIT_TIMER = new SecureRandom();

    public static void main(String[] args) {
        AtomicBoolean req1 = new AtomicBoolean(false);
        AtomicBoolean req2 = new AtomicBoolean(false);
        AtomicInteger id = new AtomicInteger(1);

        Automaton a1 = new Automaton(id, req1, req2);
        id.set(2);
        Automaton a2 = new Automaton(id, req2, req1);

        a1.start();
        a2.start();
    }

    private static class Automaton extends Thread {

        private static final int MAX_CRITICAL_STUFF_COUNT = 1000;
        private static final int MAX_TIMEOUT = 5;
        private int criticalStuffCount;

        private final int id;
        private final AtomicInteger turn;
        private final AtomicBoolean req_self;
        private final AtomicBoolean req_other;

        public Automaton(AtomicInteger turn, AtomicBoolean req_self, AtomicBoolean req_other) {
            this.id = turn.get();
            this.turn = turn;
            this.req_self = req_self;
            this.req_other = req_other;
        }

        @Override
        public void run() {

            System.out.println("id " + id + " req_self:=true");
            req_self.set(true);
            int newTurn = id == 1 ? 2 : 1;
            System.out.println("id " + id + " turn:=" + newTurn);
            turn.set(newTurn);
            while (turn.get() != id && !req_other.get() && !isCriticalStuffDone()) {
                doCriticalStuff();
            }
            System.out.println("id " + id + " req_self:=false");
            req_self.set(false);

        }

        private boolean isCriticalStuffDone() {
            return criticalStuffCount >= MAX_CRITICAL_STUFF_COUNT;
        }

        private void doCriticalStuff() {
            criticalStuffCount++;
            long timeout = WAIT_TIMER.nextInt(MAX_TIMEOUT);
            System.out.println("id:" + id
                    + " turn: " + turn
                    + " req_self: " + req_self.hashCode()
                    + " req_other: " + req_other.hashCode()
                    + " critical stuff count: " + criticalStuffCount
                    + " waiting for " + timeout);
            long startWaitTime = System.currentTimeMillis();
            long endWaitTime = startWaitTime + timeout;
            while (System.currentTimeMillis() < endWaitTime);
        }
    }
}
