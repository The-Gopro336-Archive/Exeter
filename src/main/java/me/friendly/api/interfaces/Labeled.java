package me.friendly.api.interfaces;

/**
 * An Interface for all Objects that can have a label.
 * For most use-cases in this client the label should
 * never change.
 */
public interface Labeled {
    /** @return the label for this Object. */
    String getLabel();
}

