package me.friendly.api.minecraft.render.font;

import me.friendly.exeter.module.impl.active.render.Hud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class FontUtil {
    public static CustomFontRenderer customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 19), true, false);

    public static void drawString(String text, float x, float y, int color){
        if (Hud.customFont.getValue()){
            GlStateManager.enableBlend();
            customFontRenderer.drawStringWithShadow(text, x, y - 1, color);
            GlStateManager.disableBlend();
        } else {
            Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, color, true);
        }
    }

    public static int getStringWidth(String text){
        if (Hud.customFont.getValue()){
            return customFontRenderer.getStringWidth(text);
        } else {
            return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
        }
    }
}
