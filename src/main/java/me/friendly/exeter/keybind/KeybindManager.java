package me.friendly.exeter.keybind;

import java.util.ArrayList;
import me.friendly.api.event.Listener;
import me.friendly.api.registry.ListRegistry;
import me.friendly.exeter.core.Exeter;
import me.friendly.exeter.events.InputEvent;

public final class KeybindManager
extends ListRegistry<Keybind> {

    public KeybindManager() {
        this.registry = new ArrayList();
        Exeter.getInstance().getEventManager().register(new Listener<InputEvent>("keybinds_input_listener"){

            @Override
            public void call(InputEvent event) {
                if (event.getType() == InputEvent.Type.KEYBOARD_KEY_PRESS) {
                    KeybindManager.this.registry.forEach(keybind -> {
                        if (keybind.getKey() != 0 && keybind.getKey() == event.getKey()) {
                            keybind.onPressed();
                        }
                    });
                }
            }
        });
    }

    /**
     * Keybind registry is searched for matches
     * to the label param
     *
     * @param label the label
     * @return the keybind of the label
     */
    public Keybind getKeybindByLabel(String label) {
        for (Keybind keybind : registry) {
            if (!label.equalsIgnoreCase(keybind.getLabel())) continue;
            return keybind;
        }
        return null;
    }
}

