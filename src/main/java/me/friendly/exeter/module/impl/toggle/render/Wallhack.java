package me.friendly.exeter.module.impl.toggle.render;

import me.friendly.api.event.Listener;
import me.friendly.exeter.events.RenderChestEvent;
import me.friendly.exeter.events.RenderEntityEvent;
import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.properties.Property;
import org.lwjgl.opengl.GL11;

public final class Wallhack
extends ToggleableModule {
    private final Property<Boolean> entities = new Property<Boolean>(true, "Entities", "entity", "entitie", "e");
    private final Property<Boolean> chests = new Property<Boolean>(true, "Chests", "chest", "c");

    public Wallhack() {
        super("Wallhack", new String[]{"wallhack", "wh"}, ModuleType.RENDER);
        this.offerProperties(this.entities, this.chests);
        this.listeners.add(new Listener<RenderEntityEvent>("wallhack_render_entity_listener"){

            @Override
            public void call(RenderEntityEvent event) {
                if (((Boolean)Wallhack.this.entities.getValue()).booleanValue()) {
                    switch (event.getTime()) {
                        case BEFORE: {
                            GL11.glEnable((int)32823);
                            GL11.glPolygonOffset((float)1.0f, (float)-2000000.0f);
                            break;
                        }
                        case AFTER: {
                            GL11.glPolygonOffset((float)1.0f, (float)2000000.0f);
                            GL11.glDisable((int)32823);
                        }
                    }
                }
            }
        });
        this.listeners.add(new Listener<RenderChestEvent>("wallhack_render_chest_listener"){

            @Override
            public void call(RenderChestEvent event) {
                if (((Boolean)Wallhack.this.chests.getValue()).booleanValue()) {
                    switch (event.getTime()) {
                        case BEFORE: {
                            GL11.glEnable((int)32823);
                            GL11.glPolygonOffset((float)1.0f, (float)-2000000.0f);
                            break;
                        }
                        case AFTER: {
                            GL11.glPolygonOffset((float)1.0f, (float)2000000.0f);
                            GL11.glDisable((int)32823);
                        }
                    }
                }
            }
        });
    }
}

