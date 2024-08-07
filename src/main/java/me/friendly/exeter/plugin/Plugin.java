package me.friendly.exeter.plugin;

/**
 * Plugins can be used to add functionality, modules or commands
 * to Exeter. Plugins are located in the exeter/plugins folder.
 * A Plugin should be a jar file. Dependencies like Mixin don't
 * need to be included as they are already included in the Exeter
 * jar.
 */
public abstract class Plugin {
    private final String name;
    private boolean running;

    public Plugin(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning(boolean state) {
        boolean update = this.running != state;
        this.running = state;
        if (update) {
            if (this.isRunning()) {
                this.startPlugin();
            } else {
                this.stopPlugin();
            }
        }
    }

    public abstract void startPlugin();

    public abstract void stopPlugin();
}

