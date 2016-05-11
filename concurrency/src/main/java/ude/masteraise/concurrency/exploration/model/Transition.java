package ude.masteraise.concurrency.exploration.model;

/**
 * @author Nils Verheyen
 * @since 29.04.16 16:29
 */
public class Transition {

    public static final Transition EMPTY = new Transition("e", true);

    private String name;
    private boolean output;

    public Transition(String name, boolean output) {
        this.name = name;
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transition)) return false;

        Transition that = (Transition) o;

        if (output != that.output) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (output
                                ? 1
                                : 0);
        return result;
    }
}
