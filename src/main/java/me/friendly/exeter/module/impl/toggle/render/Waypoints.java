package me.friendly.exeter.module.impl.toggle.render;///*
// * Decompiled with CFR 0.150.
// *
// * Could not load the following classes:
// *  org.lwjgl.opengl.GL11
// */
//package me.me.friendly.exeter.module.impl.toggle.render;
//
//import java.util.ArrayList;
//import java.util.List;
//import me.me.friendly.api.event.Listener;
//import me.me.friendly.api.interfaces.Labeled;
//import me.me.friendly.api.minecraft.render.RenderMethods;
//import me.me.friendly.exeter.command.Argument;
//import me.me.friendly.exeter.command.Command;
//import me.me.friendly.exeter.core.Exeter;
//import me.me.friendly.exeter.events.unused.RenderEvent;
//import me.me.friendly.exeter.module.ModuleType;
//import me.me.friendly.exeter.module.ToggleableModule;
//import me.me.friendly.exeter.properties.NumberProperty;
//import net.minecraft.client.renderer.GlStateManager;
//import org.lwjgl.opengl.GL11;
//
//public final class Waypoints
//extends ToggleableModule {
//    private final NumberProperty<Float> width = new NumberProperty<Float>(Float.valueOf(1.8f), Float.valueOf(1.0f), Float.valueOf(5.0f), "Width", "w");
//    private final NumberProperty<Float> scaling = new NumberProperty<Float>(Float.valueOf(0.003f), Float.valueOf(0.001f), Float.valueOf(0.01f), "Scaling", "scale", "s");
//    private final List<Point> points = new ArrayList<Point>();
//
//    public Waypoints() {
//        super("Waypoints", new String[]{"waypoints", "waypoint", "points", "point", "wp"}, ModuleType.RENDER);
//        this.offerProperties(this.width, this.scaling);
//        Exeter.getInstance().getCommandManager().register(new Command(new String[]{"waypointsadd", "waypointadd", "pointadd", "pointsadd", "wpadd", "wadd", "padd"}, new Argument[]{new Argument("label"), new Argument("x"), new Argument("y"), new Argument("z")}){
//
//            @Override
//            public String dispatch() {
//                int z;
//                int y;
//                int x;
//                String name = this.getArgument("label").getValue();
//                Point point = new Point(name, x = Integer.parseInt(this.getArgument("x").getValue()), y = Integer.parseInt(this.getArgument("y").getValue()), z = Integer.parseInt(this.getArgument("z").getValue()));
//                if (!Waypoints.this.isValidPoint(point)) {
//                    Waypoints.this.points.add(point);
//                }
//                return String.format("Added waypoint &e%s&7.", point.getLabel());
//            }
//        });
//        Exeter.getInstance().getCommandManager().register(new Command(new String[]{"waypointsremove", "waypointremove", "pointremove", "pointsremove", "wpremove", "wremove", "premove"}, new Argument[]{new Argument("label")}){
//
//            @Override
//            public String dispatch() {
//                String name = this.getArgument("label").getValue();
//                Point point = Waypoints.this.getPoint(name);
//                if (point == null) {
//                    return "Invalid waypoint entered.";
//                }
//                if (Waypoints.this.isValidPoint(point)) {
//                    Waypoints.this.points.remove(point);
//                }
//                return String.format("Removed waypoint &e%s&7.", point.getLabel());
//            }
//        });
//        this.listeners.add(new Listener<RenderEvent>("waypoints_render_listener"){
//
//            @Override
//            public void call(RenderEvent event) {
//                double z;
//                double y;
//                double x;
//                GlStateManager.pushMatrix();
//                RenderMethods.enableGL3D();
//                for (Point point : Waypoints.this.points) {
//                    x = (double)point.getX() - ((Waypoints)Waypoints.this).minecraft.getRenderManager().renderPosX;
//                    y = (double)point.getY() - ((Waypoints)Waypoints.this).minecraft.getRenderManager().renderPosY;
//                    z = (double)point.getZ() - ((Waypoints)Waypoints.this).minecraft.getRenderManager().renderPosZ;
//                    GlStateManager.color(0.7f, 0.1f, 0.2f, 0.7f);
//                    boolean bobbing = ((Waypoints)Waypoints.this).minecraft.gameSettings.viewBobbing;
//                    GL11.glLineWidth((float)((Float)Waypoints.this.width.getValue()).floatValue());
//                    GL11.glLoadIdentity();
//                    ((Waypoints)Waypoints.this).minecraft.gameSettings.viewBobbing = false;
//                    ((Waypoints)Waypoints.this).minecraft.entityRenderer.orientCamera(event.getPartialTicks());
//                    GL11.glBegin((int)1);
//                    GL11.glVertex3d((double)0.0, (double)((Waypoints)Waypoints.this).minecraft.player.getEyeHeight(), (double)0.0);
//                    GL11.glVertex3d((double)x, (double)y, (double)z);
//                    GL11.glVertex3d((double)x, (double)y, (double)z);
//                    GL11.glVertex3d((double)x, (double)(y + 2.0), (double)z);
//                    GL11.glEnd();
//                    ((Waypoints)Waypoints.this).minecraft.gameSettings.viewBobbing = bobbing;
//                }
//                for (Point point : Waypoints.this.points) {
//                    x = (double)point.getX() - ((Waypoints)Waypoints.this).minecraft.getRenderManager().renderPosX;
//                    y = (double)point.getY() - ((Waypoints)Waypoints.this).minecraft.getRenderManager().renderPosY;
//                    z = (double)point.getZ() - ((Waypoints)Waypoints.this).minecraft.getRenderManager().renderPosZ;
//                    GlStateManager.pushMatrix();
//                    Waypoints.this.renderPointNameTag(point, x, y, z);
//                    GlStateManager.popMatrix();
//                }
//                RenderMethods.disableGL3D();
//                GlStateManager.popMatrix();
//            }
//        });
//    }
//
//    private boolean isValidPoint(Point point) {
//        for (Point p : points) {
//            if (p.getX() != point.getX() || p.getY() != point.getY() || p.getZ() != point.getZ()) continue;
//            return true;
//        }
//        return false;
//    }
//
//    private Point getPoint(String name) {
//        for (Point point : points) {
//            if (!point.getLabel().equalsIgnoreCase(name)) continue;
//            return point;
//        }
//        return null;
//    }
//
//    private void renderPointNameTag(Point point, double x, double y, double z) {
//        double tempY = y;
//        tempY += 0.7;
//        double distance = this.minecraft.getRenderViewEntity().getDistance(x + this.minecraft.getRenderManager().viewerPosX, y + this.minecraft.getRenderManager().viewerPosY, z + this.minecraft.getRenderManager().viewerPosZ);
//        int width = this.minecraft.fontRenderer.getStringWidth(point.getLabel()) / 2 + 1;
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
//        RenderMethods.drawBorderedRectReliant(-width - 2, -(this.minecraft.fontRenderer.FONT_HEIGHT + 1), width, 1.5f, 1.6f, 0x77000000, -1435496416);
//        GlStateManager.enableAlpha();
//        this.minecraft.fontRenderer.drawStringWithShadow(point.getLabel(), -width, -(this.minecraft.fontRenderer.FONT_HEIGHT - 1), -5592406);
//        GlStateManager.enableDepth();
//        GlStateManager.enableLighting();
//        GlStateManager.disableBlend();
//        GlStateManager.enableLighting();
//        GlStateManager.disablePolygonOffset();
//        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
//        GlStateManager.popMatrix();
//    }
//
//    public class Point
//    implements Labeled {
//        private final String label;
//        private final int x;
//        private final int y;
//        private final int z;
//
//        public Point(String label, int x, int y, int z) {
//            this.label = label;
//            this.x = x;
//            this.y = y;
//            this.z = z;
//        }
//
//        @Override
//        public String getLabel() {
//            return this.label;
//        }
//
//        public int getZ() {
//            return this.z;
//        }
//
//        public int getX() {
//            return this.x;
//        }
//
//        public int getY() {
//            return this.y;
//        }
//    }
//}
//
