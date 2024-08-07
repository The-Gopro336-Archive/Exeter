package me.friendly.exeter.logging;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public final class Logger {
    /** Logger instance */
    private static Logger logger = null;

    /**
     * Appends message param to client tag,
     * and prints it to the log.
     *
     * @param message to be printed
     */
    public void print(String message) {
        System.out.println(String.format("[%s] %s", "Exeter", message));
    }

    /**
     * Appends message param to client tag,
     * and prints it to the chat.
     *
     * @param message to be printed
     */
    public void printToChat(String message) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(String.format("§c[%s] §7%s", "Exeter", message.replace("&", "§"))).setStyle(new Style().setColor(TextFormatting.GRAY)));
    }

    public static Logger getLogger() {
        return logger == null ? (logger = new Logger()) : logger;
    }
}

