package me.friendly.exeter.module.impl.toggle.render;///*
// * Decompiled with CFR 0.150.
// *
// * Could not load the following classes:
// *  org.lwjgl.opengl.GL11
// *  org.lwjgl.util.glu.Cylinder
// *  org.lwjgl.util.glu.Sphere
// */
//package me.me.friendly.exeter.module.impl.toggle.render;
//
//import me.me.friendly.api.event.Listener;
//import me.me.friendly.api.minecraft.render.RenderMethods;
//import me.me.friendly.exeter.core.Exeter;
//import me.me.friendly.exeter.events.unused.RenderEvent;
//import me.me.friendly.exeter.module.ModuleType;
//import me.me.friendly.exeter.module.ToggleableModule;
//import me.me.friendly.exeter.properties.EnumProperty;
//import me.me.friendly.exeter.properties.NumberProperty;
//import me.me.friendly.exeter.properties.Property;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.item.EntityEnderPearl;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.monster.IMob;
//import net.minecraft.entity.passive.IAnimals;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.math.AxisAlignedBB;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.util.glu.Cylinder;
//import org.lwjgl.util.glu.Sphere;
//
//public final class Tracers
//extends ToggleableModule {
//    private final Property<Boolean> box = new Property<Boolean>(true, "Box", "b");
//    private final Property<Boolean> spine = new Property<Boolean>(false, "Spine");
//    private final Property<Boolean> items = new Property<Boolean>(false, "Items", "item", "i");
//    private final Property<Boolean> lines = new Property<Boolean>(true, "Lines", "line", "l");
//    private final Property<Boolean> players = new Property<Boolean>(true, "Players", "player", "p");
//    private final Property<Boolean> enderpearls = new Property<Boolean>(true, "Enderpearls", "epearls", "enderpearl", "pearl");
//    private final Property<Boolean> invisibles = new Property<Boolean>(true, "Invisibles", "invis", "inv", "invisible");
//    private final Property<Boolean> monsters = new Property<Boolean>(false, "Monsters", "monster", "mon", "m");
//    private final Property<Boolean> animals = new Property<Boolean>(false, "Animals", "animal", "ani", "a");
//    public static Property<Boolean> esp = new Property<Boolean>(false, "ESP", "e");
//    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.OUTLINE, "Mode", "m");
//    private final NumberProperty<Float> width = new NumberProperty<Float>(Float.valueOf(1.8f), Float.valueOf(1.0f), Float.valueOf(5.0f), "Width", "w");
//
//    public Tracers() {
//        super("Tracers", new String[]{"tracers", "entityesp", "eesp", "esp"}, ModuleType.RENDER);
//        this.offerProperties(this.box, this.players, this.spine, this.enderpearls, this.width, this.items, this.monsters, this.animals, this.lines, this.invisibles, this.mode, esp);
//        this.listeners.add(new Listener<RenderEvent>("tracers_render_listener"){
//
//            @Override
//            public void call(RenderEvent event) {
//                GlStateManager.pushMatrix();
//                RenderMethods.enableGL3D();
//                for (Entity entity : ((Tracers)Tracers.this).minecraft.world.loadedEntityList) {
//                    float distance;
//                    if (!entity.isEntityAlive() || !Tracers.this.isValidEntity(entity)) continue;
//                    double x = Tracers.this.interpolate(entity.lastTickPosX, entity.posX, event.getPartialTicks(), ((Tracers)Tracers.this).minecraft.getRenderManager().renderPosX);
//                    double y = Tracers.this.interpolate(entity.lastTickPosY, entity.posY, event.getPartialTicks(), ((Tracers)Tracers.this).minecraft.getRenderManager().renderPosY);
//                    double z = Tracers.this.interpolate(entity.lastTickPosZ, entity.posZ, event.getPartialTicks(), ((Tracers)Tracers.this).minecraft.getRenderManager().renderPosZ);
//                    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(x - 0.4, y, z - 0.4, x + 0.4, y + 2.0, z + 0.4);
//                    if (!(entity instanceof EntityPlayer)) {
//                        axisAlignedBB = new AxisAlignedBB(x - 0.4, y, z - 0.4, x + 0.4, y + (double)entity.getEyeHeight() + 0.35, z + 0.4);
//                    }
//                    if (entity instanceof EntityItem) {
//                        axisAlignedBB = new AxisAlignedBB(x - 0.16, y + 0.13, z - 0.16, x + 0.16, y + (double)entity.getEyeHeight() + 0.25, z + 0.16);
//                    } else if (entity instanceof EntityEnderPearl) {
//                        axisAlignedBB = new AxisAlignedBB(x - 0.16, y - 0.2, z - 0.16, x + 0.16, y + (double)entity.getEyeHeight() - 0.1, z + 0.16);
//                    }
//                    if (Exeter.getInstance().getFriendManager().isFriend(entity.getName())) {
//                        GlStateManager.color(0.27f, 0.7f, 0.92f, 0.45f);
//                    } else {
//                        float distance2 = ((Tracers)Tracers.this).minecraft.player.getDistanceToEntity(entity);
//                        if (distance2 <= 32.0f) {
//                            GlStateManager.color(1.0f, distance2 / 32.0f, 0.0f, 0.45f);
//                        } else {
//                            GlStateManager.color(0.0f, 0.9f, 0.0f, 0.45f);
//                        }
//                    }
//                    boolean bobbing = ((Tracers)Tracers.this).minecraft.gameSettings.viewBobbing;
//                    if (((Boolean)Tracers.this.lines.getValue()).booleanValue()) {
//                        GlStateManager.pushMatrix();
//                        GL11.glLineWidth((float)((Float)Tracers.this.width.getValue()).floatValue());
//                        GL11.glLoadIdentity();
//                        ((Tracers)Tracers.this).minecraft.gameSettings.viewBobbing = false;
//                        ((Tracers)Tracers.this).minecraft.entityRenderer.orientCamera(event.getPartialTicks());
//                        GL11.glBegin((int)1);
//                        GL11.glVertex3d((double)0.0, (double)((Tracers)Tracers.this).minecraft.player.getEyeHeight(), (double)0.0);
//                        if (Tracers.this.mode.getValue() == Mode.OUTLINE && esp.getValue().booleanValue()) {
//                            GL11.glVertex3d((double)x, (double)(y + 1.0), (double)z);
//                        } else {
//                            GL11.glVertex3d((double)x, (double)y, (double)z);
//                        }
//                        if (((Boolean)Tracers.this.spine.getValue()).booleanValue()) {
//                            if (Tracers.this.mode.getValue() == Mode.OUTLINE && esp.getValue().booleanValue()) {
//                                GL11.glVertex3d((double)x, (double)(y + 1.0), (double)z);
//                            } else {
//                                GL11.glVertex3d((double)x, (double)y, (double)z);
//                            }
//                            if (entity instanceof EntityPlayer) {
//                                GL11.glVertex3d((double)x, (double)(y + 1.35), (double)z);
//                            } else {
//                                GL11.glVertex3d((double)x, (double)(y + (double)entity.getEyeHeight()), (double)z);
//                            }
//                        }
//                        GL11.glEnd();
//                        GlStateManager.popMatrix();
//                    }
//                    if (((Boolean)Tracers.this.box.getValue()).booleanValue()) {
//                        GlStateManager.pushMatrix();
//                        GlStateManager.translate(x, y, z);
//                        GlStateManager.rotate(-entity.rotationYaw, 0.0f, entity.height, 0.0f);
//                        GlStateManager.translate(-x, -y, -z);
//                        if (entity instanceof EntityItem || entity instanceof EntityEnderPearl) {
//                            distance = ((Tracers)Tracers.this).minecraft.player.getDistanceToEntity(entity);
//                            if (distance <= 32.0f) {
//                                GlStateManager.color(1.0f, distance / 32.0f, 0.0f, 0.25f);
//                            } else {
//                                GlStateManager.color(0.0f, 0.9f, 0.0f, 0.25f);
//                            }
//                            RenderMethods.drawBox(axisAlignedBB);
//                        }
//                        RenderMethods.drawOutlinedBox(axisAlignedBB);
//                        GlStateManager.popMatrix();
//                    }
//                    if (esp.getValue().booleanValue()) {
//                        if (Tracers.this.mode.getValue() == Mode.FILL) {
//                            GlStateManager.pushMatrix();
//                            GlStateManager.translate(x, y, z);
//                            GlStateManager.rotate(-entity.rotationYaw, 0.0f, entity.height, 0.0f);
//                            GlStateManager.translate(-x, -y, -z);
//                            if (Exeter.getInstance().getFriendManager().isFriend(entity.getName())) {
//                                GlStateManager.color(0.27f, 0.7f, 0.92f, 0.15f);
//                            } else {
//                                distance = ((Tracers)Tracers.this).minecraft.player.getDistanceToEntity(entity);
//                                if (distance <= 32.0f) {
//                                    GlStateManager.color(1.0f, distance / 32.0f, 0.0f, 0.15f);
//                                } else {
//                                    GlStateManager.color(0.0f, 0.9f, 0.0f, 0.15f);
//                                }
//                            }
//                            RenderMethods.drawBox(axisAlignedBB);
//                            RenderMethods.drawOutlinedBox(axisAlignedBB);
//                            GlStateManager.popMatrix();
//                        } else if (Tracers.this.mode.getValue() == Mode.CROSS) {
//                            GlStateManager.pushMatrix();
//                            GlStateManager.translate(x, y, z);
//                            GlStateManager.rotate(-entity.rotationYaw, 0.0f, entity.height, 0.0f);
//                            GlStateManager.translate(-x, -y, -z);
//                            RenderMethods.drawOutlinedBox(axisAlignedBB);
//                            RenderMethods.renderCrosses(axisAlignedBB);
//                            GlStateManager.popMatrix();
//                        } else if (Tracers.this.mode.getValue() == Mode.PENIS) {
//                            GlStateManager.pushMatrix();
//                            GlStateManager.translate(x, y, z);
//                            GlStateManager.rotate(-entity.rotationYaw, 0.0f, entity.height, 0.0f);
//                            GlStateManager.translate(-x, -y, -z);
//                            GlStateManager.translate(x, y + (double)(entity.height / 2.0f) - (double)0.225f, z);
//                            GlStateManager.rotate(entity.rotationPitch, 1.0f, 0.0f, 0.0f);
//                            GlStateManager.color(1.0f, 1.0f, 0.0f, 1.0f);
//                            int lines = 20;
//                            GlStateManager.translate(0.0, 0.0, 0.075);
//                            Cylinder shaft = new Cylinder();
//                            shaft.setDrawStyle(100013);
//                            shaft.draw(0.1f, 0.09f, 1.0f, 25, lines * 2);
//                            GlStateManager.translate(0.0, 0.0, -0.075);
//                            GlStateManager.translate(-0.05, 0.0, 0.0);
//                            Sphere right = new Sphere();
//                            right.setDrawStyle(100013);
//                            right.draw(0.1f, 25, lines);
//                            GlStateManager.translate(0.1, 0.0, 0.0);
//                            Sphere left = new Sphere();
//                            left.setDrawStyle(100013);
//                            left.draw(0.1f, 25, lines);
//                            GlStateManager.color(1.0f, 0.2f, 1.0f, 1.0f);
//                            GlStateManager.translate(-0.05, 0.0, 1.1);
//                            Sphere tip = new Sphere();
//                            tip.setDrawStyle(100013);
//                            tip.draw(0.09f, 25, lines);
//                            GlStateManager.popMatrix();
//                        }
//                    }
//                    ((Tracers)Tracers.this).minecraft.gameSettings.viewBobbing = bobbing;
//                }
//                RenderMethods.disableGL3D();
//                GlStateManager.popMatrix();
//            }
//        });
//    }
//
//    public boolean isValidEntity(Entity entity) {
//        if (entity == null) {
//            return false;
//        }
//        if (entity instanceof IMob) {
//            return this.monsters.getValue();
//        }
//        if (entity instanceof IAnimals) {
//            return this.animals.getValue();
//        }
//        if (entity instanceof IMob) {
//            return this.monsters.getValue();
//        }
//        if (entity instanceof EntityItem) {
//            return this.items.getValue();
//        }
//        if (entity instanceof EntityEnderPearl) {
//            return this.enderpearls.getValue();
//        }
//        if (entity instanceof EntityPlayer) {
//            EntityPlayer entityPlayer = (EntityPlayer)entity;
//            if (entityPlayer.equals(this.minecraft.player) || entityPlayer.isInvisible() && !this.invisibles.getValue().booleanValue()) {
//                return false;
//            }
//            return this.players.getValue();
//        }
//        return false;
//    }
//
//    private double interpolate(double lastI, double i, float ticks, double ownI) {
//        return lastI + (i - lastI) * (double)ticks - ownI;
//    }
//
//    public void renderOne() {
//        GL11.glPushAttrib((int)1048575);
//        GL11.glDisable((int)3008);
//        GL11.glDisable((int)3553);
//        GL11.glDisable((int)2896);
//        GL11.glEnable((int)3042);
//        GL11.glBlendFunc((int)770, (int)771);
//        GL11.glLineWidth((float)(((Float)this.width.getValue()).floatValue() * 2.0f));
//        GL11.glEnable((int)2848);
//        GL11.glEnable((int)2960);
//        GL11.glClear((int)1024);
//        GL11.glClearStencil((int)15);
//        GL11.glStencilFunc((int)512, (int)1, (int)15);
//        GL11.glStencilOp((int)7681, (int)7681, (int)7681);
//        GL11.glPolygonMode((int)1028, (int)6913);
//    }
//
//    public void renderTwo() {
//        GL11.glStencilFunc((int)512, (int)0, (int)15);
//        GL11.glStencilOp((int)7681, (int)7681, (int)7681);
//        GL11.glPolygonMode((int)1028, (int)6914);
//    }
//
//    public void renderThree() {
//        GL11.glStencilFunc((int)514, (int)1, (int)15);
//        GL11.glStencilOp((int)7680, (int)7680, (int)7680);
//        GL11.glPolygonMode((int)1028, (int)6913);
//    }
//
//    public void renderFour(Entity renderEntity) {
//        float[] color;
//        if (renderEntity instanceof EntityLivingBase) {
//            float distance;
//            EntityLivingBase entity = (EntityLivingBase)renderEntity;
//            color = Exeter.getInstance().getFriendManager().isFriend(entity.getName()) ? new float[]{0.27f, 0.7f, 0.92f} : ((distance = this.minecraft.player.getDistanceToEntity(entity)) <= 32.0f ? new float[]{1.0f, distance / 32.0f, 0.0f} : new float[]{0.0f, 0.9f, 0.0f});
//        } else {
//            float distance = this.minecraft.player.getDistanceToEntity(renderEntity);
//            color = distance <= 32.0f ? new float[]{1.0f, distance / 32.0f, 0.0f} : new float[]{0.0f, 0.9f, 0.0f};
//        }
//        GlStateManager.color(color[0], color[1], color[2], 0.85f);
//        this.renderFour();
//    }
//
//    private void renderFour() {
//        GL11.glDepthMask((boolean)false);
//        GL11.glDisable((int)2929);
//        GL11.glEnable((int)10754);
//        GL11.glPolygonOffset((float)1.0f, (float)-2000000.0f);
//        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
//    }
//
//    public void renderFive() {
//        GL11.glPolygonOffset((float)1.0f, (float)2000000.0f);
//        GL11.glDisable((int)10754);
//        GL11.glEnable((int)2929);
//        GL11.glDepthMask((boolean)true);
//        GL11.glDisable((int)2960);
//        GL11.glDisable((int)2848);
//        GL11.glHint((int)3154, (int)4352);
//        GL11.glEnable((int)3042);
//        GL11.glEnable((int)2896);
//        GL11.glEnable((int)3553);
//        GL11.glEnable((int)3008);
//        GL11.glPopAttrib();
//    }
//
//    public static enum Mode {
//        FILL,
//        CROSS,
//        OUTLINE,
//        PENIS;
//
//    }
//}
//
