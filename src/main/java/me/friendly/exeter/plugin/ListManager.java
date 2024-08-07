package me.friendly.exeter.plugin;

import java.util.*;
import java.util.List;

import net.minecraft.client.Minecraft;

public abstract class ListManager<T>
extends Manager<T> {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    private List<T> list;

    public ListManager(List<T> list) {
        this.list = list;
    }

    public ListManager() {
        this(new ArrayList());
    }

    public List<T> getList() {
        return this.list;
    }

    public Optional<T> get(String name) {
        return Optional.empty();
    }

    public void register(T ... types) {
        if (types.length > 0) {
            for (T type : types) {
                if (type == null || this.getList().contains(type)) continue;
                this.getList().add(type);
            }
        }
    }

    public void unregister(T ... types) {
        if (types.length > 0) {
            for (T type : types) {
                if (type == null || !this.getList().contains(type)) continue;
                this.getList().remove(type);
            }
        }
    }

    public boolean has(T type) {
        return this.getList().contains(type);
    }
}

