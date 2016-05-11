package ude.masteraise.concurrency.exploration.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nils Verheyen
 * @since 29.04.16 16:25
 */
public class Automaton {

    private String name;
    private State start;
    private List<State> states;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getStart() {
        return start;
    }

    public void setStart(State start) {
        this.start = start;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public void addState(State state) {
        if (states == null)
            states = new ArrayList<>();

        states.add(state);
    }

    public boolean removeState(State state) {

        return states != null
               ? states.remove(state)
               : false;
    }

    public boolean hasStates() {
        return states != null && !states.isEmpty();
    }

    @Override
    public String toString() {
        return "Automaton{" +
                "name='" + name + '\'' +
                '}';
    }
}
