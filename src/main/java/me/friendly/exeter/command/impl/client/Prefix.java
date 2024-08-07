package me.friendly.exeter.command.impl.client;

import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.core.Exeter;

public final class Prefix
extends Command {
    public Prefix() {
        super(new String[]{"prefix"}, new Argument("character"));
    }

    @Override
    public String dispatch() {
        String prefix = getArgument("character").getValue();
        Exeter.getInstance().getCommandManager().setPrefix(prefix);
        return String.format("&e%s&7 is now your prefix.", prefix);
    }
}

