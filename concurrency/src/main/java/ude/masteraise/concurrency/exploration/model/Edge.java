package ude.masteraise.concurrency.exploration.model;

/**
 * @author Nils Verheyen
 * @since 29.04.16 16:26
 */
public class Edge {

    private State source;
    private State destination;
    private Transition transition;

    public Edge(State source, State destination, Transition transition) {
        this.source = source;
        this.destination = destination;
        this.transition = transition;
    }

    public State getSource() {
        return source;
    }

    public void setSource(State source) {
        this.source = source;
    }

    public State getDestination() {
        return destination;
    }

    public void setDestination(State destination) {
        this.destination = destination;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (!source.equals(edge.source)) return false;
        if (!destination.equals(edge.destination)) return false;
        return transition.equals(edge.transition);

    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + transition.hashCode();
        return result;
    }
}
