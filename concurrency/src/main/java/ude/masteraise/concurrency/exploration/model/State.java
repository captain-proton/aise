package ude.masteraise.concurrency.exploration.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nils Verheyen
 * @since 29.04.16 16:25
 */
public class State {

    private String name;

    private List<Edge> outgoingEdges;

    public State(String name) {
        this.name = name;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void setOutgoingEdges(List<Edge> outgoingEdges) {
        this.outgoingEdges = outgoingEdges;
    }

    public void addOutgoingEdge(Edge edge) {
        if (outgoingEdges == null)
            outgoingEdges = new ArrayList<>();

        this.outgoingEdges.add(edge);
    }

    public boolean removeOutgoingEdge(Edge edge) {
        return outgoingEdges != null
               ? outgoingEdges.remove(edge)
               : false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        return name.equals(state.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
