package me.friendly.exeter.events;

import me.friendly.api.event.Event;
import org.lwjgl.input.Keyboard;

public class InputEvent
extends Event {
    private final Type type;
    private int key;

    public InputEvent(Type type) {
        this.type = type;
        this.key = Keyboard.getEventKey();
    }

    public Type getType() {
        return this.type;
    }

    public int getKey() {
        return this.key;
    }

    public static enum Type {
        KEYBOARD_KEY_PRESS,
        MOUSE_LEFT_CLICK,
        MOUSE_MIDDLE_CLICK,
        MOUSE_RIGHT_CLICK;

    }
}

