package me.friendly.api.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CopyOnWriteArrayList;

import me.friendly.api.event.filter.Filter;

/**
 * A listener, used to invoke events.
 *
 * @param <E> the {@link Event} this listener receives.
 */
public abstract class Listener<E extends Event> {
    private final String identifier;
    private Class<E> event;
    private final java.util.List<Filter> filters = new CopyOnWriteArrayList<Filter>();

    public Listener(String identifier) {
        this.identifier = identifier;
        Type generic = this.getClass().getGenericSuperclass();
        if (generic instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType)generic).getActualTypeArguments()) {
                if (!(type instanceof Class) || !Event.class.isAssignableFrom((Class)type)) continue;
                this.event = (Class)type;
                break;
            }
        }
    }

    public Class<E> getEvent() {
        return this.event;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final java.util.List<Filter> getFilters() {
        return this.filters;
    }

    public void addFilters(Filter ... filters) {
        for (Filter filter : filters) {
            this.filters.add(filter);
        }
    }

    /**
     * Called when an event is posted on the bus.
     *
     * @param event the event.
     */
    public abstract void call(E event);
}

