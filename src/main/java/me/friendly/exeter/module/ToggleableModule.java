package me.friendly.exeter.module;

import java.util.ArrayList;
import java.util.List;
import me.friendly.api.event.Listener;
import me.friendly.api.interfaces.Toggleable;
import me.friendly.exeter.core.Exeter;
import me.friendly.exeter.keybind.Keybind;

/**
 * A Module implementation of Toggleable.
 */
public class ToggleableModule
extends Module implements Toggleable {

    private boolean running;
    private boolean drawn;
    private final int color;
    private final ModuleType moduleType;
    protected final List<Listener<?>> listeners = new ArrayList<>();

    /**
     * Creates a new Module. It's important that the given label
     * does not contain any whitespaces and that no modules with the
     * same name exist. A modules name is its unique identifier.
     *
     * @param label the label for the new module.
     * @param aliases the aliases of the new module.
     * @param drawn whether or not the module will be drawn in the module list.
     * @param color the color the module will be in the module list.
     * @param moduleType the ModuleType this module belongs to.
     */
    private ToggleableModule(String label, String[] aliases, boolean drawn, int color, ModuleType moduleType) {
        super(label, aliases);
        this.drawn = drawn;
        this.color = color;
        this.moduleType = moduleType;
        Exeter.getInstance().getKeybindManager().register(new Keybind(label, 0){

            @Override
            public void onPressed() {
                ToggleableModule.this.toggle();
            }
        });
    }

    /**
     * Creates a new Module. It's important that the given label
     * does not contain any whitespaces and that no modules with the
     * same name exist. A modules name is its unique identifier.
     *
     * @param label the label for the new module.
     * @param aliases the aliases of the new module.
     * @param color the color the module will be in the module list.
     * @param moduleType the ModuleType this module belongs to.
     */
    protected ToggleableModule(String label, String[] aliases, int color, ModuleType moduleType) {
        this(label, aliases, true, color, moduleType);
    }

    /**
     * Creates a new Module. It's important that the given label
     * does not contain any whitespaces and that no modules with the
     * same name exist. A modules name is its unique identifier.
     *
     * @param label the label for the new module.
     * @param aliases the aliases of the new module.
     * @param moduleType the ModuleType this module belongs to.
     */
    protected ToggleableModule(String label, String[] aliases, ModuleType moduleType) {
        this(label, aliases, false, 0, moduleType);
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void setRunning(boolean running) {
        this.running = running;
        if (this.isRunning()) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Override
    public void toggle() {
        this.setRunning(!this.running);
    }

    public boolean isDrawn() {
        return this.drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public int getColor() {
        return this.color;
    }

    public ModuleType getModuleType() {
        return this.moduleType;
    }

    protected void onEnable() {
        this.listeners.forEach(listener -> Exeter.getInstance().getEventManager().register(listener));
    }

    protected void onDisable() {
        this.listeners.forEach(listener -> Exeter.getInstance().getEventManager().unregister(listener));
    }
}

