package me.friendly.exeter.module.impl.toggle.render;///*
// * Decompiled with CFR 0.150.
// *
// * Could not load the following classes:
// *  org.lwjgl.opengl.GL11
// */
//package me.me.friendly.exeter.module.impl.toggle.render;
//
//import me.me.friendly.api.event.Listener;
//import me.me.friendly.api.minecraft.render.RenderMethods;
//import me.me.friendly.exeter.core.Exeter;
//import me.me.friendly.exeter.events.unused.RenderEvent;
//import me.me.friendly.exeter.module.ModuleType;
//import me.me.friendly.exeter.module.ToggleableModule;
//import me.me.friendly.exeter.presets.Preset;
//import me.me.friendly.exeter.properties.NumberProperty;
//import me.me.friendly.exeter.properties.Property;
//import net.minecraft.block.Block;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.init.Blocks;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityChest;
//import net.minecraft.tileentity.TileEntityEnderChest;
//import net.minecraft.util.AxisAlignedBB;
//import net.minecraft.util.math.AxisAlignedBB;
//import org.lwjgl.opengl.GL11;
//
//public final class StorageESP
//extends ToggleableModule {
//    private final Property<Boolean> tags = new Property<Boolean>(true, "NameTags", "plates", "tags", "labels", "np", "nameplates", "nametag", "nt");
//    private final Property<Boolean> outline = new Property<Boolean>(true, "Outline", "outline", "o");
//    private final Property<Boolean> lines = new Property<Boolean>(true, "Lines", "line", "l");
//    private final Property<Boolean> chest = new Property<Boolean>(true, "Chests", "chest", "c");
//    private final Property<Boolean> enderchest = new Property<Boolean>(true, "EnderChests", "enderchest", "echest");
//    private final NumberProperty<Float> scaling = new NumberProperty<Float>(Float.valueOf(0.003f), Float.valueOf(0.001f), Float.valueOf(0.01f), "Scaling", "scale", "s");
//    private final NumberProperty<Float> width = new NumberProperty<Float>(Float.valueOf(1.8f), Float.valueOf(1.0f), Float.valueOf(5.0f), "Width", "w");
//
//    public StorageESP() {
//        super("StorageESP", new String[]{"storageesp", "cesp", "sesp", "chestesp"}, ModuleType.RENDER);
//        this.offerProperties(this.tags, this.chest, this.enderchest, this.lines, this.width, this.outline);
//        this.offsetPresets(new Preset("Cluster"){
//
//            @Override
//            public void onSet() {
//                StorageESP.this.tags.setValue(true);
//                StorageESP.this.lines.setValue(true);
//            }
//        }, new Preset("Minimal"){
//
//            @Override
//            public void onSet() {
//                StorageESP.this.tags.setValue(false);
//                StorageESP.this.lines.setValue(false);
//            }
//        });
//        this.listeners.add(new Listener<RenderEvent>("chest_esp_render_listener"){
//
//            @Override
//            public void call(RenderEvent event) {
//                double z;
//                double y;
//                double x;
//                TileEntity tileEntity;
//                GlStateManager.pushMatrix();
//                RenderMethods.enableGL3D();
//                for (Object object : ((StorageESP)StorageESP.this).minecraft.world.loadedTileEntityList) {
//                    tileEntity = (TileEntity)object;
//                    if (!StorageESP.this.shouldDraw(tileEntity)) continue;
//                    x = (double)tileEntity.getPos().getX() - ((StorageESP)StorageESP.this).minecraft.getRenderManager().renderPosX;
//                    y = (double)tileEntity.getPos().getY() - ((StorageESP)StorageESP.this).minecraft.getRenderManager().renderPosY;
//                    z = (double)tileEntity.getPos().getZ() - ((StorageESP)StorageESP.this).minecraft.getRenderManager().renderPosZ;
//                    float[] color = StorageESP.this.getColor(tileEntity);
//                    AxisAlignedBB box = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
//                    if (tileEntity instanceof TileEntityChest) {
//                        TileEntityChest chest = (TileEntityChest)TileEntityChest.class.cast(tileEntity);
//                        if (chest.adjacentChestZPos != null) {
//                            box = new AxisAlignedBB(x + 0.0625, y, z + 0.0625, x + 0.9375, y + 0.875, z + 1.9375);
//                        } else if (chest.adjacentChestXPos != null) {
//                            box = new AxisAlignedBB(x + 0.0625, y, z + 0.0625, x + 1.9375, y + 0.875, z + 0.9375);
//                        } else {
//                            if (chest.adjacentChestZPos != null || chest.adjacentChestXPos != null || chest.adjacentChestZNeg != null || chest.adjacentChestXNeg != null) continue;
//                            box = new AxisAlignedBB(x + 0.0625, y, z + 0.0625, x + 0.9375, y + 0.875, z + 0.9375);
//                        }
//                    } else if (tileEntity instanceof TileEntityEnderChest) {
//                        box = new AxisAlignedBB(x + 0.0625, y, z + 0.0625, x + 0.9375, y + 0.875, z + 0.9375);
//                    }
//                    GlStateManager.color(color[0], color[1], color[2], 0.45f);
//                    boolean bobbing = ((StorageESP)StorageESP.this).minecraft.gameSettings.viewBobbing;
//                    if (((Boolean)StorageESP.this.lines.getValue()).booleanValue()) {
//                        GlStateManager.pushMatrix();
//                        GL11.glLineWidth((float)((Float)StorageESP.this.width.getValue()).floatValue());
//                        GL11.glLoadIdentity();
//                        ((StorageESP)StorageESP.this).minecraft.gameSettings.viewBobbing = false;
//                        ((StorageESP)StorageESP.this).minecraft.entityRenderer.orientCamera(event.getPartialTicks());
//                        GL11.glBegin((int)1);
//                        GL11.glVertex3d((double)0.0, (double)((StorageESP)StorageESP.this).minecraft.player.getEyeHeight(), (double)0.0);
//                        GL11.glVertex3d((double)(x + 0.5), (double)y, (double)(z + 0.5));
//                        GL11.glEnd();
//                        GlStateManager.popMatrix();
//                    }
//                    if (((Boolean)StorageESP.this.outline.getValue()).booleanValue()) {
//                        StorageESP.this.renderOne();
//                        GlStateManager.color(color[0], color[1], color[2], 0.45f);
//                        RenderMethods.drawBox(box);
//                        StorageESP.this.renderTwo();
//                        GlStateManager.color(color[0], color[1], color[2], 0.45f);
//                        RenderMethods.drawBox(box);
//                        StorageESP.this.renderThree();
//                        GlStateManager.color(color[0], color[1], color[2], 0.45f);
//                        RenderMethods.drawBox(box);
//                        StorageESP.this.renderFour();
//                        GlStateManager.color(color[0], color[1], color[2], 0.45f);
//                        RenderMethods.drawBox(box);
//                        StorageESP.this.renderFive();
//                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
//                    } else {
//                        RenderMethods.drawBox(box);
//                        RenderMethods.renderCrosses(box);
//                        RenderMethods.drawOutlinedBox(box);
//                    }
//                    ((StorageESP)StorageESP.this).minecraft.gameSettings.viewBobbing = bobbing;
//                }
//                for (Object object : ((StorageESP)StorageESP.this).minecraft.world.loadedTileEntityList) {
//                    tileEntity = (TileEntity)object;
//                    if (!StorageESP.this.shouldDraw(tileEntity)) continue;
//                    x = (double)tileEntity.getPos().getX() + 0.5 - ((StorageESP)StorageESP.this).minecraft.getRenderManager().renderPosX;
//                    y = (double)tileEntity.getPos().getY() - 1.0 - ((StorageESP)StorageESP.this).minecraft.getRenderManager().renderPosY;
//                    z = (double)tileEntity.getPos().getZ() + 0.5 - ((StorageESP)StorageESP.this).minecraft.getRenderManager().renderPosZ;
//                    if (!((Boolean)StorageESP.this.tags.getValue()).booleanValue()) continue;
//                    GlStateManager.pushMatrix();
//                    StorageESP.this.renderTileEntityNameTag(tileEntity, x, y, z);
//                    GlStateManager.popMatrix();
//                }
//                RenderMethods.disableGL3D();
//                GlStateManager.popMatrix();
//            }
//        });
//    }
//
//    private boolean shouldDraw(TileEntity tileEntity) {
//        return tileEntity instanceof TileEntityChest && this.chest.getValue() != false || tileEntity instanceof TileEntityEnderChest && this.enderchest.getValue() != false;
//    }
//
//    private float[] getColor(TileEntity tileEntity) {
//        if (tileEntity instanceof TileEntityChest) {
//            Block block = tileEntity.getBlockType();
//            if (block == Blocks.chest) {
//                return new float[]{0.8f, 0.7f, 0.22f};
//            }
//            if (block == Blocks.trapped_chest) {
//                return new float[]{0.8f, 0.22f, 0.22f};
//            }
//        }
//        if (tileEntity instanceof TileEntityEnderChest) {
//            return new float[]{1.0f, 0.0f, 1.0f};
//        }
//        return new float[]{1.0f, 1.0f, 1.0f};
//    }
//
//    private void renderTileEntityNameTag(TileEntity tileEntity, double x, double y, double z) {
//        double tempY = y;
//        tempY += 0.7;
//        double distance = this.minecraft.getRenderViewEntity().getDistance(x + this.minecraft.getRenderManager().viewerPosX, y + this.minecraft.getRenderManager().viewerPosY, z + this.minecraft.getRenderManager().viewerPosZ);
//        int width = this.minecraft.fontRenderer.getStringWidth(this.getDisplayName(tileEntity)) / 2 + 1;
//        double scale = 0.0018 + (double)((Float)this.scaling.getValue()).floatValue() * distance;
//        if (distance <= 8.0) {
//            scale = 0.0245;
//        }
//        GlStateManager.pushMatrix();
//        GlStateManager.enablePolygonOffset();
//        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
//        GlStateManager.disableLighting();
//        GlStateManager.translate((float)x, (float)tempY + 1.4f, (float)z);
//        GlStateManager.rotate(-this.minecraft.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
//        GlStateManager.rotate(this.minecraft.getRenderManager().playerViewX, this.minecraft.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f, 0.0f, 0.0f);
//        GlStateManager.scale(-scale, -scale, scale);
//        GlStateManager.disableDepth();
//        GlStateManager.enableBlend();
//        GlStateManager.disableAlpha();
//        RenderMethods.drawBorderedRectReliant(-width - 2, -(this.minecraft.fontRenderer.FONT_HEIGHT + 1), width, 1.5f, 1.6f, 0x77000000, 0x55000000);
//        GlStateManager.enableAlpha();
//        this.minecraft.fontRenderer.drawStringWithShadow(this.getDisplayName(tileEntity), -width, -(this.minecraft.fontRenderer.FONT_HEIGHT - 1), -1);
//        GlStateManager.enableDepth();
//        GlStateManager.enableLighting();
//        GlStateManager.disableBlend();
//        GlStateManager.enableLighting();
//        GlStateManager.disablePolygonOffset();
//        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
//        GlStateManager.popMatrix();
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
//    private String getDisplayName(TileEntity tileEntity) {
//        if (tileEntity instanceof TileEntityChest) {
//            Block block = tileEntity.getBlockType();
//            if (block == Blocks.CHEST) {
//                return "Chest";
//            }
//            if (block == Blocks.TRAPPED_CHEST) {
//                return "Trapped Chest";
//            }
//        }
//        if (tileEntity instanceof TileEntityEnderChest) {
//            return "EnderChest";
//        }
//        return "Unknown";
//    }
//}
//
