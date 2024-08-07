package me.friendly.exeter.config;

import me.friendly.api.interfaces.Labeled;
import me.friendly.exeter.core.Exeter;

import java.io.File;

/**
 * An object that is saved to a file, and
 * an implementation of Labeled
 */
public abstract class Config
implements Labeled {
    private final String label;
    private final File file;
    private final File directory;

    /**
     * Instantiates label, file, and directory.
     * registers current Config instance with ConfigManager
     *
     * @param label the name of the file that will be created and saved to
     */
    public Config(String label) {
        this.label = label;
        this.directory = Exeter.getInstance().getDirectory();
        this.file = new File(this.directory, label);
        Exeter.getInstance().getConfigManager().register(this);
    }

    /**
     * Instantiates label, file, and directory.
     * registers current Config instance with ConfigManager
     *
     * @param label the name of the file that will be created and saved to
     * @param directory the directory that will be saved to
     */
    public Config(String label, File directory) {
        this.label = label;
        this.directory = directory;
        this.file = new File(directory, label);
        Exeter.getInstance().getConfigManager().register(this);
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public File getDirectory() {
        return this.directory;
    }

    public File getFile() {
        return this.file;
    }

    public abstract void load(Object... var1);

    public abstract void save(Object... var1);
}

