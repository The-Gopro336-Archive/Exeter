package me.friendly.exeter.module.impl.toggle.render.tabgui;

import java.util.ArrayList;
import java.util.Collections;

import me.friendly.api.interfaces.Toggleable;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.api.minecraft.render.font.FontUtil;
import me.friendly.exeter.core.Exeter;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.module.impl.active.render.Hud;
import me.friendly.exeter.module.impl.toggle.render.tabgui.item.GuiItem;
import me.friendly.exeter.module.impl.toggle.render.tabgui.item.GuiTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public final class GuiTabHandler {
    private Minecraft mc = Minecraft.getMinecraft();
    private float width = 0.7f;
    private int guiHeight = 0;
    public boolean mainMenu = true;
    public int selectedItem = 0;
    public int selectedTab = 0;
    private int tabHeight = 12;
    public final ArrayList<GuiTab> tabs = new ArrayList();
    public int transition = 0;
    public boolean visible = true;

    public GuiTabHandler() {
        java.util.List<Module> modules = Exeter.getInstance().getModuleManager().getRegistry();
        modules.sort((mod1, mod2) -> mod1.getLabel().compareTo(mod2.getLabel()));
        for (ModuleType moduleType : ModuleType.values()) {
            GuiTab guiTab = new GuiTab(this, moduleType.getLabel());
            modules.stream().forEach(module -> {
                ToggleableModule toggle;
                if (module instanceof Toggleable && (toggle = (ToggleableModule)module).getModuleType() == moduleType && !toggle.getLabel().equalsIgnoreCase("Click Gui") && !toggle.getLabel().equalsIgnoreCase("Tab Gui")) {
                    guiTab.getMods().add(new GuiItem(toggle));
                }
            });
            this.tabs.add(guiTab);
        }
        Collections.sort(this.tabs, (category1, category2) -> category1.getLabel().compareTo(category2.getLabel()));
        this.guiHeight = this.tabs.size() * this.tabHeight;
    }

    public void drawGui(int x, int y) {
        if (!this.visible) {
            return;
        }
        int guiWidth = 73;
        RenderMethods.drawBorderedRectReliant(x, (float)y - 0.4f, x + guiWidth - 2, (float)(y + this.guiHeight) + 0.4f, 1.5f, 0x66000000, -2013265920);
        for (int i = 0; i < this.tabs.size(); ++i) {
            final int transitionTop = this.mainMenu ? (this.transition + ((this.selectedTab == 0 && this.transition < 0) ? (-this.transition) : 0)) : 0;
            final int transitionBottom = this.mainMenu ? (this.transition + ((this.selectedTab == this.tabs.size() - 1 && this.transition > 0) ? (-this.transition) : 0)) : 0;
            if (this.selectedTab == i) {
                RenderMethods.drawGradientBorderedRectReliant((float)x, i * 12 + y + transitionTop - 0.3f, x + guiWidth - 2.2f, i + (y + 12 + i * 11) + transitionBottom + 0.3f, 1.5f, -2013265920, -1141161406, -1141161406);
            }
        }
        int yOff = y + 2;
        for (int index = 0; index < this.tabs.size(); ++index) {
            GuiTab tab = this.tabs.get(index);
            if (Hud.customFont.getValue()){
                GlStateManager.enableBlend();
                FontUtil.customFontRenderer.drawStringWithShadow(tab.getLabel(), x + 2, yOff, -197380);
                GlStateManager.disableBlend();
            } else {
                Minecraft.getMinecraft().fontRenderer.drawString(tab.getLabel(), x + 2, yOff, -197380, true);
            }
//            FontUtil.drawString(tab.getLabel(), x + 2, yOff, -197380);
            if (this.selectedTab == index && !this.mainMenu) {
                tab.drawTabMenu(this.mc, x + guiWidth, yOff - 2);
            }
            yOff += this.tabHeight;
        }
        if (this.transition > 0) {
            --this.transition;
        } else if (this.transition < 0) {
            ++this.transition;
        }
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public int getSelectedItem() {
        return this.selectedItem;
    }

    public int getTabHeight() {
        return this.tabHeight;
    }

    public int getTransition() {
        return this.transition;
    }
}

