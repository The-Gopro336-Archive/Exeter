package me.friendly.exeter.command;

import java.util.StringJoiner;

import net.minecraft.client.Minecraft;

public abstract class Command {
    private final String[] aliases;
    private final Argument[] arguments;
    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Command(String[] aliases, Argument ... arguments) {
        this.aliases = aliases;
        this.arguments = arguments;
    }

    /**
     * Called when a chatmessage starting with the prefix is sent
     * and fits returns true for the string array. Keep in mind that
     * the current GuiScreen will be closed after this method
     * has been called!
     *
     * @param input the input.
     */
    public String dispatch(String[] input) {
        Argument[] arguments = this.getArguments();
        boolean valid = false;
        if (input.length < arguments.length) {
            return String.format("%s %s", input[0], this.getSyntax());
        }
        if (input.length - 1 > arguments.length) {
            return String.format("Maximum number of arguments is &e%s&7.", arguments.length);
        }
        if (arguments.length > 0) {
            for (int index = 0; index < arguments.length; ++index) {
                Argument argument = arguments[index];
                argument.setPresent(index < input.length);
                argument.setValue(input[index + 1]);
                valid = argument.isPresent();
            }
        } else {
            valid = true;
        }
        return valid ? this.dispatch() : "Invalid argument(s).";
    }

    public final String[] getAliases() {
        return this.aliases;
    }

    public final Argument[] getArguments() {
        return this.arguments;
    }

    public Argument getArgument(String label) {
        for (Argument argument : arguments) {
            if (!label.equalsIgnoreCase(argument.getLabel())) continue;
            return argument;
        }
        return null;
    }

    /**
     * Returns the syntax of the Command. Called,
     * and printed to chat when the user enters
     * a command with incorrect syntax.
     *
     * @return the syntax of the command, as a String
     */
    public String getSyntax() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Argument argument : arguments) {
            stringJoiner.add(String.format("&e[%s]", argument.getLabel()));
        }
        return stringJoiner.toString();
    }

    public abstract String dispatch();
}

