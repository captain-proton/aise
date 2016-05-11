package ude.masteraise.concurrency.exploration.model;

import java.util.Vector;

/**
 * @author Nils Verheyen
 * @since 29.04.16 17:08
 */
public class MessageQueue {

    private State state;

    private Vector<String> messages;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Vector<String> getMessages() {
        return messages;
    }

    public void setMessages(Vector<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String value) {
        if (messages == null)
            messages = new Vector<>();

        this.messages.add(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageQueue)) return false;

        MessageQueue that = (MessageQueue) o;

        if (!state.equals(that.state)) return false;
        return messages.equals(that.messages);

    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + messages.hashCode();
        return result;
    }
}
