package me.friendly.exeter.events;

import me.friendly.api.event.Event;

public class ViewmodelEvent
extends Event {
    private final Type type;
    private boolean noFov;
    private boolean noPitchLimit;

    public ViewmodelEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isNoFov() {
        return this.noFov;
    }

    public boolean isNoPitchLimit() {
        return this.noPitchLimit;
    }

    public void setNoFov(boolean noFov) {
        this.noFov = noFov;
    }

    public void setNoPitchLimit(boolean noPitchLimit) {
        this.noPitchLimit = noPitchLimit;
    }

    public static enum Type {
        FOV,
        PITCH_LIMIT;

    }
}

