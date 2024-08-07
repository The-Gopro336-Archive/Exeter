package me.friendly.exeter.module.impl.toggle.render;///*
// * Decompiled with CFR 0.150.
// */
//package me.me.friendly.exeter.module.impl.toggle.render;
//
//import me.me.friendly.api.event.Listener;
//import me.me.friendly.api.minecraft.render.RenderMethods;
//import me.me.friendly.exeter.core.Exeter;
//import me.me.friendly.exeter.events.unused.PassSpecialRenderEvent;
//import me.me.friendly.exeter.events.unused.RenderEvent;
//import me.me.friendly.exeter.module.ModuleType;
//import me.me.friendly.exeter.module.ToggleableModule;
//import me.me.friendly.exeter.properties.EnumProperty;
//import me.me.friendly.exeter.properties.NumberProperty;
//import me.me.friendly.exeter.properties.Property;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.RenderHelper;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemArmor;
//import net.minecraft.item.ItemAxe;
//import net.minecraft.item.ItemBow;
//import net.minecraft.item.ItemPickaxe;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.ItemSword;
//import net.minecraft.item.ItemTool;
//import net.minecraft.util.EnumChatFormatting;
//
//public final class NameTags
//extends ToggleableModule {
//    private final Property<Boolean> armor = new Property<Boolean>(true, "Armor", "a");
//    private final Property<Boolean> ping = new Property<Boolean>(true, "Ping");
//    private final Property<Boolean> heart = new Property<Boolean>(true, "Heart");
//    private final Property<Boolean> health = new Property<Boolean>(true, "Health", "h");
//    private final Property<Boolean> durability = new Property<Boolean>(true, "Durability", "d");
//    private final NumberProperty<Float> scaling = new NumberProperty<Float>(Float.valueOf(0.003f), Float.valueOf(0.001f), Float.valueOf(0.01f), "Scaling", "scale", "s");
//    private final NumberProperty<Float> width = new NumberProperty<Float>(Float.valueOf(1.6f), Float.valueOf(1.0f), Float.valueOf(5.0f), "Width", "w");
//    private final EnumProperty<Health> healthLook = new EnumProperty<Health>(Health.TWENTY, "HealthLook", "look");
//    private int pingInt;
//
//    public NameTags() {
//        super("NameTags", new String[]{"nametags", "np", "nt", "tags", "plates", "nameplates", "nametag"}, ModuleType.RENDER);
//        this.offerProperties(this.armor, this.health, this.scaling, this.width, this.healthLook, this.durability, this.heart, this.ping);
//        this.listeners.add(new Listener<RenderEvent>("name_tags_render_listener"){
//
//            @Override
//            public void call(RenderEvent event) {
//                for (Object o : ((NameTags)NameTags.this).minecraft.world.playerEntities) {
//                    Entity entity = (Entity)o;
//                    if (!(entity instanceof EntityPlayer) || entity == ((NameTags)NameTags.this).minecraft.player || !entity.isEntityAlive()) continue;
//                    double x = NameTags.this.interpolate(entity.lastTickPosX, entity.posX, event.getPartialTicks()) - ((NameTags)NameTags.this).minecraft.getRenderManager().renderPosX;
//                    double y = NameTags.this.interpolate(entity.lastTickPosY, entity.posY, event.getPartialTicks()) - ((NameTags)NameTags.this).minecraft.getRenderManager().renderPosY;
//                    double z = NameTags.this.interpolate(entity.lastTickPosZ, entity.posZ, event.getPartialTicks()) - ((NameTags)NameTags.this).minecraft.getRenderManager().renderPosZ;
//                    NameTags.this.renderNameTag((EntityPlayer)entity, x, y, z, event.getPartialTicks());
//                }
//            }
//        });
//        this.listeners.add(new Listener<PassSpecialRenderEvent>("name_tags_pass_special_render_listener"){
//
//            @Override
//            public void call(PassSpecialRenderEvent event) {
//                event.setCanceled(true);
//            }
//        });
//        this.setRunning(true);
//    }
//
//    private void renderNameTag(EntityPlayer player, double x, double y, double z, float delta) {
//        double tempY = y;
//        tempY += player.isSneaking() ? 0.5 : 0.7;
//        Entity camera = this.minecraft.getRenderViewEntity();
//        double originalPositionX = camera.posX;
//        double originalPositionY = camera.posY;
//        double originalPositionZ = camera.posZ;
//        camera.posX = this.interpolate(camera.prevPosX, camera.posX, delta);
//        camera.posY = this.interpolate(camera.prevPosY, camera.posY, delta);
//        camera.posZ = this.interpolate(camera.prevPosZ, camera.posZ, delta);
//        double distance = camera.getDistance(x + this.minecraft.getRenderManager().viewerPosX, y + this.minecraft.getRenderManager().viewerPosY, z + this.minecraft.getRenderManager().viewerPosZ);
//        int width = this.minecraft.fontRenderer.getStringWidth(this.getDisplayName(player)) / 2;
//        double scale = 0.0018 + (double)((Float)this.scaling.getValue()).floatValue() * distance;
//        if (distance <= 8.0) {
//            scale = 0.0245;
//        }
//        GlStateManager.pushMatrix();
//        RenderHelper.enableStandardItemLighting();
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
//        RenderMethods.drawBorderedRectReliant(-width - 2, -(this.minecraft.fontRenderer.FONT_HEIGHT + 1), (float)width + 2.0f, 1.5f, ((Float)this.width.getValue()).floatValue(), 0x77000000, 0x55000000);
//        GlStateManager.enableAlpha();
//        this.minecraft.fontRenderer.drawStringWithShadow(this.getDisplayName(player), -width, -(this.minecraft.fontRenderer.FONT_HEIGHT - 1), this.getDisplayColour(player));
//        if (this.armor.getValue().booleanValue()) {
//            ItemStack stack;
//            int index;
//            GlStateManager.pushMatrix();
//            int xOffset = 0;
//            for (index = 3; index >= 0; --index) {
//                stack = player.inventory.armorInventory[index];
//                if (stack == null) continue;
//                xOffset -= 8;
//            }
//            if (player.getCurrentEquippedItem() != null) {
//                xOffset -= 8;
//                ItemStack renderStack = player.getCurrentEquippedItem().copy();
//                if (renderStack.hasEffect() && (renderStack.getItem() instanceof ItemTool || renderStack.getItem() instanceof ItemArmor)) {
//                    renderStack.stackSize = 1;
//                }
//                this.renderItemStack(renderStack, xOffset, -26);
//                xOffset += 16;
//            }
//            for (index = 3; index >= 0; --index) {
//                stack = player.inventory.armorInventory[index];
//                if (stack == null) continue;
//                ItemStack armourStack = stack.copy();
//                if (armourStack.hasEffect() && (armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor)) {
//                    armourStack.stackSize = 1;
//                }
//                this.renderItemStack(armourStack, xOffset, -26);
//                xOffset += 16;
//            }
//            GlStateManager.popMatrix();
//        }
//        camera.posX = originalPositionX;
//        camera.posY = originalPositionY;
//        camera.posZ = originalPositionZ;
//        GlStateManager.enableDepth();
//        GlStateManager.enableLighting();
//        GlStateManager.disableBlend();
//        GlStateManager.enableLighting();
//        GlStateManager.disablePolygonOffset();
//        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
//        GlStateManager.popMatrix();
//    }
//
//    private void renderItemStack(ItemStack stack, int x, int y) {
//        GlStateManager.pushMatrix();
//        GlStateManager.depthMask(true);
//        GlStateManager.clear(256);
//        RenderHelper.enableStandardItemLighting();
//        this.minecraft.getRenderItem().zLevel = -150.0f;
//        GlStateManager.disableLighting();
//        GlStateManager.disableDepth();
//        GlStateManager.disableBlend();
//        GlStateManager.enableLighting();
//        GlStateManager.enableDepth();
//        GlStateManager.disableLighting();
//        GlStateManager.disableDepth();
//        GlStateManager.disableAlpha();
//        GlStateManager.disableAlpha();
//        GlStateManager.disableBlend();
//        GlStateManager.enableBlend();
//        GlStateManager.enableAlpha();
//        GlStateManager.enableAlpha();
//        GlStateManager.enableLighting();
//        GlStateManager.enableDepth();
//        this.minecraft.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
//        this.minecraft.getRenderItem().renderItemOverlays(this.minecraft.fontRenderer, stack, x, y);
//        this.minecraft.getRenderItem().zLevel = 0.0f;
//        RenderHelper.disableStandardItemLighting();
//        GlStateManager.disableCull();
//        GlStateManager.enableAlpha();
//        GlStateManager.disableBlend();
//        GlStateManager.disableLighting();
//        GlStateManager.scale(0.5f, 0.5f, 0.5f);
//        GlStateManager.disableDepth();
//        this.renderEnchantmentText(stack, x, y);
//        GlStateManager.enableDepth();
//        GlStateManager.scale(2.0f, 2.0f, 2.0f);
//        GlStateManager.popMatrix();
//    }
//
//    private void renderEnchantmentText(ItemStack stack, int x, int y) {
//        int sharpnessLevel;
//        int enchantmentY = y - 24;
//        if (stack.getEnchantmentTagList() != null && stack.getEnchantmentTagList().tagCount() >= 6) {
//            this.minecraft.fontRenderer.drawString("god", x * 2, enchantmentY, -43177);
//            return;
//        }
//        int color = -5592406;
//        if (stack.getItem() instanceof ItemArmor) {
//            int protectionLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.PROTECTION.effectId, stack);
//            int projectileProtectionLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.PROJECTILE_PROTECTION.effectId, stack);
//            int blastProtectionLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.BLAST_PROTECTION.effectId, stack);
//            int fireProtectionLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.FIRE_PROTECTION.effectId, stack);
//            int thornsLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.THORNS.effectId, stack);
//            int featherFallingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.FEATHER_FALLING.effectId, stack);
//            int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.UNBREAKING.effectId, stack);
//            if (protectionLevel > 0) {
//                this.minecraft.fontRenderer.drawString("pr" + protectionLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (unbreakingLevel > 0) {
//                this.minecraft.fontRenderer.drawString("un" + unbreakingLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (projectileProtectionLevel > 0) {
//                this.minecraft.fontRenderer.drawString("pp" + projectileProtectionLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (blastProtectionLevel > 0) {
//                this.minecraft.fontRenderer.drawString("bp" + blastProtectionLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (fireProtectionLevel > 0) {
//                this.minecraft.fontRenderer.drawString("fp" + fireProtectionLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (thornsLevel > 0) {
//                this.minecraft.fontRenderer.drawString("tho" + thornsLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (featherFallingLevel > 0) {
//                this.minecraft.fontRenderer.drawString("ff" + featherFallingLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (this.durability.getValue().booleanValue() && stack.getMaxDamage() - stack.getItemDamage() < stack.getMaxDamage()) {
//                this.minecraft.fontRenderer.drawString(stack.getMaxDamage() - stack.getItemDamage() + "", x * 2, enchantmentY + 2, -26215);
//                enchantmentY += 8;
//            }
//        }
//        if (stack.getItem() instanceof ItemBow) {
//            int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.POWER.effectId, stack);
//            int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.PUNCH.effectId, stack);
//            int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.FLAME.effectId, stack);
//            if (powerLevel > 0) {
//                this.minecraft.fontRenderer.drawString("po" + powerLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (punchLevel > 0) {
//                this.minecraft.fontRenderer.drawString("pu" + punchLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (flameLevel > 0) {
//                this.minecraft.fontRenderer.drawString("fl" + flameLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//        }
//        if (stack.getItem() instanceof ItemPickaxe) {
//            int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.EFFICIENCY.effectId, stack);
//            int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.FORTUNE.effectId, stack);
//            if (efficiencyLevel > 0) {
//                this.minecraft.fontRenderer.drawString("ef" + efficiencyLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (fortuneLevel > 0) {
//                this.minecraft.fontRenderer.drawString("fo" + fortuneLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//        }
//        if (stack.getItem() instanceof ItemAxe) {
//            sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.SHARPNESS.effectId, stack);
//            int fireAspectLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.FIRE_ASPECT.effectId, stack);
//            int efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.EFFICIENCY.effectId, stack);
//            if (sharpnessLevel > 0) {
//                this.minecraft.fontRenderer.drawString("sh" + sharpnessLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (fireAspectLevel > 0) {
//                this.minecraft.fontRenderer.drawString("fa" + fireAspectLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (efficiencyLevel > 0) {
//                this.minecraft.fontRenderer.drawString("ef" + efficiencyLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//        }
//        if (stack.getItem() instanceof ItemSword) {
//            sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.SHARPNESS.effectId, stack);
//            int knockbackLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.KNOCKBACK.effectId, stack);
//            int fireAspectLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.FIRE_ASPECT.effectId, stack);
//            if (sharpnessLevel > 0) {
//                this.minecraft.fontRenderer.drawString("sh" + sharpnessLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (knockbackLevel > 0) {
//                this.minecraft.fontRenderer.drawString("kn" + knockbackLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//            if (fireAspectLevel > 0) {
//                this.minecraft.fontRenderer.drawString("fa" + fireAspectLevel, x * 2, enchantmentY, color);
//                enchantmentY += 8;
//            }
//        }
//        if (stack.getItem() == Items.golden_apple && stack.hasEffect()) {
//            this.minecraft.fontRenderer.drawStringWithShadow("god", x * 2, enchantmentY, -3977919);
//        }
//    }
//
//    private String getDisplayName(EntityPlayer player) {
//        String name = player.getDisplayName().getFormattedText();
//        String heartUnicode = " \u2764";
//        if (Exeter.getInstance().getFriendManager().isFriend(player.getName())) {
//            name = Exeter.getInstance().getFriendManager().getFriendByAliasOrLabel(player.getName()).getAlias();
//        }
//        if (name.contains(this.minecraft.getSession().getUsername())) {
//            name = "You";
//        }
//        if (!this.health.getValue().booleanValue()) {
//            return name;
//        }
//        float health = player.getHealth();
//        EnumChatFormatting color = health > 18.0f ? EnumChatFormatting.GREEN : (health > 16.0f ? EnumChatFormatting.DARK_GREEN : (health > 12.0f ? EnumChatFormatting.YELLOW : (health > 8.0f ? EnumChatFormatting.GOLD : (health > 5.0f ? EnumChatFormatting.RED : EnumChatFormatting.DARK_RED))));
//        switch ((Health)((Object)this.healthLook.getValue())) {
//            case HUNDRED: {
//                health *= 5.0f;
//                break;
//            }
//            case TWENTY: {
//                break;
//            }
//            case TEN: {
//                health /= 2.0f;
//            }
//        }
//        name = Math.floor(health) == (double)health ? name + (Object)((Object)color) + " " + (health > 0.0f ? Integer.valueOf((int)Math.floor(health)) : "dead") : name + (Object)((Object)color) + " " + (health > 0.0f ? Integer.valueOf((int)health) : "dead");
//        if (this.healthLook.getValue() == Health.HUNDRED) {
//            name = name + "%";
//        }
//        if (this.healthLook.getValue() == Health.TEN && this.heart.getValue().booleanValue()) {
//            name = name + heartUnicode;
//        }
//        return name;
//    }
//
//    private int getDisplayColour(EntityPlayer player) {
//        int colour = -5592406;
//        if (Exeter.getInstance().getFriendManager().isFriend(player.getName())) {
//            return -11157267;
//        }
//        if (player.isInvisible()) {
//            colour = -1113785;
//        } else if (player.isSneaking()) {
//            colour = -6481515;
//        }
//        return colour;
//    }
//
//    private double interpolate(double previous, double current, float delta) {
//        return previous + (current - previous) * (double)delta;
//    }
//
//    public static enum Health {
//        HUNDRED,
//        TWENTY,
//        TEN;
//
//    }
//}
//
