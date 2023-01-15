package cs445.rec05;

import java.lang.StringBuilder;

/**
 * A wrapper class used to store an action and data item
 * for the Bag ADT
 * @author Brian Nixon
 * @author William C. Garrison III
 */
public class Action<T> {
    // 'i' for insertion, 'r' for removal
    private char action;
    private T data;

    public Action(char action, T data) {
        this.action = action;
        this.data = data;
    }

    public char getAction() {
        return this.action;
    }

    public T getData() {
        return this.data;
    }

    public void setAction(char newAction) {
        this.action = newAction;
    }

    public void setData(T newData) {
        this.data = newData;
    }

    @Override
    public String toString() {
        return "Action <" + action + ", " + data + ">";
    }
}