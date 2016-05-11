package ude.masteraise.concurrency.exploration.model;

/**
 * @author Nils Verheyen
 * @since 29.04.16 16:53
 */
public class ExpandedState {

    private Object[][] matrix;
    private boolean explored;

    public ExpandedState() {
    }

    public void addState(State state) {
        if (matrix == null) {
            matrix = new Object[1][1];
        } else {
            Object[][] newMatrix = new Object[matrix.length + 1][0];
            for (int i = 0; i < matrix.length; i++) {
                newMatrix[i] = new Object[matrix[i].length + 1];
            }
            newMatrix[newMatrix.length - 1] = new Object[newMatrix.length];
        }
    }

    public void addMessage(State source, State destination, String value) {

    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpandedState)) return false;

        ExpandedState that = (ExpandedState) o;

        if (matrix.length != that.matrix.length) return false;

        for (int row = 0; row < matrix.length; row++) {

            if (matrix[0].length != that.matrix[0].length) return false;
            for (int column  = 0; column < matrix[row].length; column++) {

                if (!matrix[row][column].equals(that.matrix[row][column]))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {

        int result = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column  = 0; column < matrix[row].length; column++) {

                result = 31 * result + matrix[row][column].hashCode();
            }
        }
        return result;
    }
}
