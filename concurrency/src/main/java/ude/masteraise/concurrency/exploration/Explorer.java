package ude.masteraise.concurrency.exploration;

import ude.masteraise.concurrency.exploration.model.Automaton;
import ude.masteraise.concurrency.exploration.model.Edge;
import ude.masteraise.concurrency.exploration.model.ExpandedState;
import ude.masteraise.concurrency.exploration.model.State;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nils Verheyen
 * @since 29.04.16 16:34
 */
public class Explorer {

    private Automaton automata;
    private Set<ExpandedState> visited;

    public void expand(Automaton... automata) {

        for (Automaton a : automata) {

            State s0 = a.getStart();
            if (s0 == null)
                throw new IllegalStateException("automaton " + a + " has no start state");

            if (!a.hasStates())
                throw new IllegalStateException("automaton " + a + " does not contain any state");
        }

        this.visited = new HashSet<>();
        List<State> startStates = Arrays.stream(automata)
                .map(a -> a.getStart())
                .collect(Collectors.toList());
        ExpandedState start = buildExpandedState(startStates);
        startStates.forEach(start::addState);
        expand(start);
    }

    private void expand(ExpandedState state) {
    }

    private ExpandedState buildExpandedState(List<State> states) {
        ExpandedState result = new ExpandedState();
        for (State s : states) {
            result.addState(s);
        }
        return result;
    }

    private List<Edge> calculateTransitions(ExpandedState exploredState) {
        return null;
    }

    private boolean isTransitionPossible(ExpandedState exploredState) {
        return false;
    }
}
