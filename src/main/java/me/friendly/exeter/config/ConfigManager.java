package me.friendly.exeter.config;

import java.util.ArrayList;
import me.friendly.api.registry.ListRegistry;

public final class ConfigManager
extends ListRegistry<Config> {

    /**
     * Instantiate registry
     */
    public ConfigManager() {
        this.registry = new ArrayList();
    }
}

