package me.friendly.api.event.basic;

import java.util.concurrent.CopyOnWriteArrayList;
import me.friendly.api.event.Event;
import me.friendly.api.event.EventManager;
import me.friendly.api.event.Listener;
import me.friendly.api.event.filter.Filter;
import net.minecraft.client.Minecraft;

/**
 * An EventManager implementation.
 */
public final class BasicEventManager
implements EventManager {

    /** A list of listeners currently on the event bus */
    private final java.util.List<Listener> listeners = new CopyOnWriteArrayList<Listener>();

    @Override
    public void register(Listener listener) {
        if (!this.has(listener) && listener != null) {
            this.listeners.add(listener);
        }
    }

    @Override
    public void unregister(Listener listener) {
        if (this.has(listener) && listener != null) {
            this.listeners.remove(listener);
        }
    }

    @Override
    public void clear() {
        if (!this.listeners.isEmpty()) {
            this.listeners.clear();
        }
    }

    @Override
    public void dispatch(Event event) {
        this.listeners.forEach(listener -> {
            if (this.filter(listener, event) && listener.getEvent() == event.getClass() && Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
                listener.call(event);
            }
        });
    }

    @Override
    public java.util.List<Listener> getListeners() {
        return this.listeners;
    }

    public Listener getListener(String identifier) {
        if (!this.listeners.isEmpty()) {
            for (Listener listener : listeners) {
                if (!listener.getIdentifier().equalsIgnoreCase(identifier)) continue;
                return listener;
            }
        }
        return null;
    }

    private boolean filter(Listener listener, Event event) {
        java.util.List<Filter> filters = listener.getFilters();
        if (!filters.isEmpty()) {
            for (Filter filter : filters) {
                if (filter.filter(listener, event)) continue;
                return false;
            }
        }
        return true;
    }

    private boolean has(Listener listener) {
        return this.listeners.contains(listener);
    }
}

