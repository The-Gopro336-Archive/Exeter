package me.friendly.exeter.module.impl.active.render;

import me.friendly.api.event.Listener;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.core.Exeter;
import me.friendly.exeter.events.RenderGameInfoEvent;
import me.friendly.exeter.events.RenderGameOverlayEvent;
import me.friendly.exeter.events.ViewmodelEvent;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.properties.NumberProperty;
import me.friendly.exeter.properties.Property;
import net.minecraft.client.gui.ScaledResolution;

public final class Render
extends Module {
    private final Property<Boolean> customBars = new Property<Boolean>(true, "CustomBars", "c", "custom", "cb");
    private final Property<Boolean> nofov = new Property<Boolean>(false, "NoFOV", "nf");
    private final Property<Boolean> pumpkin = new Property<Boolean>(true, "NoPumpkin", "p", "np");
    private final Property<Boolean> fire = new Property<Boolean>(true, "NoFire", "fire", "nf");
    private final Property<Boolean> hurtcam = new Property<Boolean>(false, "NoHurtcam", "hurtcam", "nh");
    private final Property<Boolean> items = new Property<Boolean>(false, "NoItems", "items", "item", "ni");
    public final Property<Float> blockHeight = new NumberProperty<Float>(Float.valueOf(-0.2f), Float.valueOf(-1.5f), Float.valueOf(1.5f), "BlockHeight");

    public Render() {
        super("Render", new String[]{"render", "norender", "nr"});
        this.offerProperties(pumpkin, customBars, fire, hurtcam, items, nofov, blockHeight);
        Exeter.getInstance().getEventManager().register(new Listener<RenderGameOverlayEvent>("norender_render_game_overlay_listener"){

            @Override
            public void call(RenderGameOverlayEvent event) {
                switch (event.getType()) {
                    case FIRE: {
                        event.setRenderFire(fire.getValue());
                        break;
                    }
                    case HURTCAM: {
                        event.setRenderHurtcam(hurtcam.getValue());
                        break;
                    }
                    case ITEM: {
                        event.setRenderItems(items.getValue());
                        if (!(items.getValue()).booleanValue()) break;
                        ((Render)Render.this).minecraft.world.removeEntity(event.getEntityItem());
                        break;
                    }
                    case PUMPKIN: {
                        event.setRenderPumpkin(pumpkin.getValue());
                    }
                }
            }
        });
        Exeter.getInstance().getEventManager().register(new Listener<RenderGameInfoEvent>("textgui_render_game_info_listener"){

            @Override
            public void call(RenderGameInfoEvent event) {
                ScaledResolution scaledResolution = event.getScaledResolution();
                if ((customBars.getValue()).booleanValue()) {
                    int minusHealth = (int)((Render)Render.this).minecraft.player.getHealth();
                    int minusFood = ((Render)Render.this).minecraft.player.getFoodStats().getFoodLevel();
                    if (minusFood > 20) {
                        minusFood = 20;
                    }
                    if (minusHealth > 20) {
                        minusHealth = 20;
                    }
                    RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 - 91, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 - 10, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -12303292, -14540254);
                    RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 - 91, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 - 90 + minusHealth * 4, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -577175552, -582746112);
                    ((Render)Render.this).minecraft.fontRenderer.drawStringWithShadow(String.format("%s", (int)((Render)Render.this).minecraft.player.getHealth()), scaledResolution.getScaledWidth() / 2 - 56, scaledResolution.getScaledHeight() - 38, -1);
                    RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 + 10, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 + 91, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -12303292, -14540254);
                    RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 + 90 - minusFood * 4, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 + 91, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -586565120, -586849536);
                    ((Render)Render.this).minecraft.fontRenderer.drawStringWithShadow(String.format("%s", ((Render)Render.this).minecraft.player.getFoodStats().getFoodLevel()), scaledResolution.getScaledWidth() / 2 + 45, scaledResolution.getScaledHeight() - 38, -1);
                }
            }
        });
        Exeter.getInstance().getEventManager().register(new Listener<ViewmodelEvent>("textgui_view_model_listener"){

            @Override
            public void call(ViewmodelEvent event) {
                if (nofov.getValue()) {
                    event.setCanceled(true);
                }
            }
        });
    }
}

