package me.friendly.api.registry;

/**
 * Register
 *
 * @param <T> the type of Object registered.
 */
public class ListRegistry<T> {

    protected java.util.List<T> registry;

    public void register(T element) {
        this.registry.add(element);
    }

    public void unregister(T element) {
        this.registry.remove(element);
    }

    public void clear() {
        this.registry.clear();
    }

    public java.util.List<T> getRegistry() {
        return this.registry;
    }
}

