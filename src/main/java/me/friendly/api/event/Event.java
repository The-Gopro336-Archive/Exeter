package me.friendly.api.event;

public class Event {
    /** If this event is cancelled or not. */
    private boolean canceled = false;

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}

