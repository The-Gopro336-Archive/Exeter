package me.friendly.exeter.events.unused;

import me.friendly.api.event.Event;

public class ShowMessageEvent
extends Event {
    private String message;

    public ShowMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

