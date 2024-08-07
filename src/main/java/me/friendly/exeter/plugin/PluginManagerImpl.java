package me.friendly.exeter.plugin;

import java.io.IOException;

/**
 * Plugins can be used to add functionality, modules or commands
 * to Exeter. Plugins are located in the exeter/plugins folder.
 * A Plugin should be a jar file. Dependencies like Mixin don't
 * need to be included as they are already included in the Exeter
 * jar.
 */
public interface PluginManagerImpl<T> {
    java.io.File getFile();

    /**
     * Loads this Plugin.
     */
    void onLoad() throws IOException;

    boolean needsUpdate();

    default boolean needsUpdate(T type) {
        return this.needsUpdate();
    }

    default void onLoad(T type) throws IOException {
        this.onLoad();
    }
}

