package me.friendly.api.interfaces;

/**
 * An interface for Objects that can be toggled.
 */
public interface Toggleable {

    /** @return the running state for this Object. */
    boolean isRunning();

    /** Sets the running state for this Object. */
    void setRunning(boolean var1);

    void toggle();
}

