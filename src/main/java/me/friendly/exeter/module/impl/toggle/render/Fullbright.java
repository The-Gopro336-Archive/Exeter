package me.friendly.exeter.module.impl.toggle.render;

import me.friendly.api.event.Listener;
import me.friendly.exeter.events.GammaSettingEvent;
import me.friendly.exeter.events.NightVisionEvent;
import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.properties.EnumProperty;

public final class Fullbright
extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.POTION, "Mode", "m");

    public Fullbright() {
        super("Fullbright", new String[]{"fullbright", "bright", "brightness", "fb"}, -2366720, ModuleType.RENDER);
        this.offerProperties(this.mode);
        this.listeners.add(new Listener<GammaSettingEvent>("brightness_gamma_setting_listener"){

            @Override
            public void call(GammaSettingEvent event) {
                if (Fullbright.this.mode.getValue() == Mode.GAMMA) {
                    event.setGammaSetting(1000.0f);
                }
            }
        });
        this.listeners.add(new Listener<NightVisionEvent>("brightness_night_vision_listener"){

            @Override
            public void call(NightVisionEvent event) {
                if (Fullbright.this.mode.getValue() == Mode.POTION) {
                    event.setCanceled(true);
                }
            }
        });
    }

    public enum Mode {
        GAMMA,
        POTION;

    }
}

