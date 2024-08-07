package me.friendly.exeter.module.impl.toggle.render;///*
// * Decompiled with CFR 0.150.
// */
//package me.me.friendly.exeter.module.impl.toggle.render;
//
//import me.me.friendly.api.event.Listener;
//import me.me.friendly.api.minecraft.render.RenderMethods;
//import me.me.friendly.exeter.events.unused.RenderEvent;
//import me.me.friendly.exeter.module.ModuleType;
//import me.me.friendly.exeter.module.ToggleableModule;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.item.EntityFallingBlock;
//import net.minecraft.util.math.AxisAlignedBB;
//
//public final class Seeker
//extends ToggleableModule {
//    public Seeker() {
//        super("Seeker", new String[]{"seeker", "seek", "hide"}, -7285564, ModuleType.RENDER);
//        this.listeners.add(new Listener<RenderEvent>("tracers_render_listener"){
//
//            @Override
//            public void call(RenderEvent event) {
//                GlStateManager.pushMatrix();
//                RenderMethods.enableGL3D();
//                for (Entity entity : ((Seeker)Seeker.this).minecraft.world.loadedEntityList) {
//                    if (!entity.isEntityAlive() || !(entity instanceof EntityFallingBlock)) continue;
//                    double x = Seeker.this.interpolate(entity.lastTickPosX, entity.posX, event.getPartialTicks(), ((Seeker)Seeker.this).minecraft.getRenderManager().renderPosX);
//                    double y = Seeker.this.interpolate(entity.lastTickPosY, entity.posY, event.getPartialTicks(), ((Seeker)Seeker.this).minecraft.getRenderManager().renderPosY);
//                    double z = Seeker.this.interpolate(entity.lastTickPosZ, entity.posZ, event.getPartialTicks(), ((Seeker)Seeker.this).minecraft.getRenderManager().renderPosZ);
//                    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(x - 0.5, y, z - 0.5, x + 0.5, y + 1.0, z + 0.5);
//                    float distance = entity.getDistance(minecraft.player);
////                    float distance = ((Seeker)Seeker.this).minecraft.player.getDistanceToEntity(entity);
//                    if (distance <= 32.0f) {
//                        GlStateManager.color(1.0f, distance / 32.0f, 0.0f, 0.35f);
//                    } else {
//                        GlStateManager.color(0.0f, 0.9f, 0.0f, 0.35f);
//                    }
//                    RenderMethods.drawBox(axisAlignedBB);
//                }
//                RenderMethods.disableGL3D();
//                GlStateManager.popMatrix();
//            }
//        });
//    }
//
//    private double interpolate(double lastI, double i, float ticks, double ownI) {
//        return lastI + (i - lastI) * (double)ticks - ownI;
//    }
//}
//
