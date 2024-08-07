package me.friendly.exeter.events.unused;

import me.friendly.api.event.Event;

public class RenderEvent
extends Event {
    private float partialTicks;

    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}

