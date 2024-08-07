package me.friendly.exeter.module.impl.toggle.render;

import me.friendly.api.event.Listener;
import me.friendly.api.stopwatch.Stopwatch;
import me.friendly.exeter.events.TickEvent;
import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.properties.EnumProperty;
import me.friendly.exeter.properties.NumberProperty;
import net.minecraft.util.EnumParticleTypes;

public final class Trails
extends ToggleableModule {
    private final EnumProperty<ParticleType> particleType = new EnumProperty<ParticleType>(ParticleType.BARRIER, "Particle");
    private final NumberProperty<Long> delay = new NumberProperty<Long>(Long.valueOf(250L), 1L, 10000L, "Delay", "d");
    private final NumberProperty<Double> xOffset = new NumberProperty<Double>(Double.valueOf(0.0), -10.0, 10.0, "X Offset", "xoffset", "xo");
    private final NumberProperty<Double> yOffset = new NumberProperty<Double>(Double.valueOf(2.7), -10.0, 10.0, "Y Offset", "yoffset", "yo");
    private final NumberProperty<Double> zOffset = new NumberProperty<Double>(Double.valueOf(0.0), -10.0, 10.0, "Z Offset", "zoffset", "zo");
    private final Stopwatch stopwatch = new Stopwatch();

    public Trails() {
        super("Trails", new String[]{"trails"}, ModuleType.RENDER);
        this.offerProperties(this.delay, this.particleType, this.xOffset, this.yOffset, this.zOffset);
        this.listeners.add(new Listener<TickEvent>("trails_tick_listener"){

            @Override
            public void call(TickEvent event) {
                if (Trails.this.stopwatch.hasCompleted((Long)Trails.this.delay.getValue())) {
                    ((Trails)Trails.this).minecraft.world.spawnParticle(((ParticleType)((Trails)Trails.this).particleType.getValue()).particleType, ((Trails)Trails.this).minecraft.player.posX + (Double)Trails.this.xOffset.getValue(), ((Trails)Trails.this).minecraft.player.posY + (Double)Trails.this.yOffset.getValue(), ((Trails)Trails.this).minecraft.player.posZ + (Double)Trails.this.zOffset.getValue(), 0.0, 0.0, 0.0, new int[0]);
                    Trails.this.stopwatch.reset();
                }
            }
        });
    }

    public static enum ParticleType {
        BARRIER(EnumParticleTypes.BARRIER),
        CLOUD(EnumParticleTypes.CLOUD),
        CRIT(EnumParticleTypes.CRIT),
        EXPLOSION_NORMAL(EnumParticleTypes.EXPLOSION_NORMAL),
        EXPLOSION_LARGE(EnumParticleTypes.EXPLOSION_LARGE),
        EXPLOSION_HUGE(EnumParticleTypes.EXPLOSION_HUGE),
        DRIP_LAVA(EnumParticleTypes.DRIP_LAVA),
        DRIP_WATER(EnumParticleTypes.DRIP_WATER);

        public EnumParticleTypes particleType;

        private ParticleType(EnumParticleTypes particleType) {
            this.particleType = particleType;
        }
    }
}

