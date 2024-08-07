package me.friendly.exeter.module.impl.toggle.render;///*
// * Decompiled with CFR 0.150.
// */
//package me.me.friendly.exeter.module.impl.toggle.render;
//
//import me.me.friendly.api.event.Listener;
//import me.me.friendly.api.minecraft.render.RenderMethods;
//import me.me.friendly.exeter.events.InputEvent;
//import me.me.friendly.exeter.events.unused.RenderEvent;
//import me.me.friendly.exeter.module.ModuleType;
//import me.me.friendly.exeter.module.ToggleableModule;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.item.ItemAxe;
//import net.minecraft.util.math.AxisAlignedBB;
//
//public final class WorldeditESP
//extends ToggleableModule {
//    private Position position1;
//    private Position position2;
//
//    public WorldeditESP() {
//        super("WorldeditESP", new String[]{"worldeditesp", "wee", "weesp"}, -2835568, ModuleType.RENDER);
//        this.listeners.add(new Listener<RenderEvent>("worldedit_esp_render_listener"){
//
//            @Override
//            public void call(RenderEvent event) {
//                GlStateManager.pushMatrix();
//                RenderMethods.enableGL3D();
//                if (WorldeditESP.this.position1 != null && WorldeditESP.this.position2 != null) {
//                    double x = (double)WorldeditESP.this.position1.getX() - ((WorldeditESP)WorldeditESP.this).minecraft.getRenderManager().renderPosX;
//                    double y = (double)WorldeditESP.this.position1.getY() - ((WorldeditESP)WorldeditESP.this).minecraft.getRenderManager().renderPosY;
//                    double z = (double)WorldeditESP.this.position1.getZ() - ((WorldeditESP)WorldeditESP.this).minecraft.getRenderManager().renderPosZ;
//                    double x1 = (double)WorldeditESP.this.position2.getX() - ((WorldeditESP)WorldeditESP.this).minecraft.getRenderManager().renderPosX;
//                    double y1 = (double)WorldeditESP.this.position2.getY() - ((WorldeditESP)WorldeditESP.this).minecraft.getRenderManager().renderPosY;
//                    double z1 = (double)WorldeditESP.this.position2.getZ() - ((WorldeditESP)WorldeditESP.this).minecraft.getRenderManager().renderPosZ;
//                    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(x, y, z, x1, y1, z1);
//                    GlStateManager.color(0.1f, 0.1f, 0.3f, 1.0f);
//                    RenderMethods.renderCrosses(axisAlignedBB);
//                    RenderMethods.drawOutlinedBox(axisAlignedBB);
//                }
//                RenderMethods.disableGL3D();
//                GlStateManager.popMatrix();
//            }
//        });
//        this.listeners.add(new Listener<InputEvent>("worldedit_esp_input_listener"){
//
//            @Override
//            public void call(InputEvent event) {
//                switch (event.getType()) {
//                    case MOUSE_LEFT_CLICK: {
//                        if (((WorldeditESP)WorldeditESP.this).minecraft.player.inventory.getCurrentItem() == null || !(((WorldeditESP)WorldeditESP.this).minecraft.player.inventory.getCurrentItem().getItem() instanceof ItemAxe) || ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver == null || ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) break;
//                        WorldeditESP.this.position1 = new Position(((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.func_178782_a().getX(), ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.func_178782_a().getY(), ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.func_178782_a().getZ());
//                        break;
//                    }
//                    case MOUSE_RIGHT_CLICK: {
//                        if (((WorldeditESP)WorldeditESP.this).minecraft.player.inventory.getCurrentItem() == null || !(((WorldeditESP)WorldeditESP.this).minecraft.player.inventory.getCurrentItem().getItem() instanceof ItemAxe) || ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver == null || ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) break;
//                        WorldeditESP.this.position2 = new Position(((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.func_178782_a().getX(), ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.func_178782_a().getY() + 1, ((WorldeditESP)WorldeditESP.this).minecraft.objectMouseOver.func_178782_a().getZ());
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onEnable() {
//        super.onEnable();
//        this.position2 = null;
//        this.position1 = null;
//    }
//
//    @Override
//    protected void onDisable() {
//        super.onDisable();
//        this.position2 = null;
//        this.position1 = null;
//    }
//
//    public class Position {
//        private int x;
//        private int y;
//        private int z;
//
//        public Position(int x, int y, int z) {
//            this.x = x;
//            this.y = y;
//            this.z = z;
//        }
//
//        public int getX() {
//            return this.x;
//        }
//
//        public int getY() {
//            return this.y;
//        }
//
//        public int getZ() {
//            return this.z;
//        }
//    }
//}
//
