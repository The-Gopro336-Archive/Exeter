package me.friendly.api.event;

/**
 * A manager, used to register events.
 */
public interface EventManager {

    /**
     * Registers a listener to the bus. It will now receive
     * Objects posted on the bus.
     *
     * @param listener the listener to be registered.
     */
    void register(Listener listener);

    /**
     * Unregisters a listener from the bus. Now it
     * wont receive Objects posted on the bus.
     *
     * @param listener the listener to be unregistered.
     */
    void unregister(Listener listener);

    /**
     * Clears the event bus
     */
    void clear();

    /**
     * Posts an Event on the bus. Invokes
     * {@link Listener#call(event)} for every
     * Listener that targets the objects class.
     *
     * @param object the object posted.
     */
    void dispatch(Event event);

    /**
     * @return a list of Listeners on the bus.
     */
    java.util.List<Listener> getListeners();
}

