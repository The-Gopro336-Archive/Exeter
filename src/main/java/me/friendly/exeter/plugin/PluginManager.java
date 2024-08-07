package me.friendly.exeter.plugin;

import java.io.*;
import java.io.File;
import java.net.*;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import me.friendly.exeter.core.Exeter;

public class PluginManager
extends ListManager<Plugin>
implements PluginManagerImpl {
    /**
     * Scans the plugin list for Plugins
     * that match the name param given.
     * It then returns optional with the plugin
     *
     * @param name the name of the plugin.
     */
    @Override
    public Optional<Plugin> get(String name) {
        for (Plugin plugin : getList()) {
            if (!plugin.getName().equalsIgnoreCase(name)) continue;
            return Optional.of(plugin);
        }
        return Optional.empty();
    }

    /** @return the Plugin directory */
    @Override
    public File getFile() {
        return new File(Exeter.getInstance().getDirectory(), "plugins");
    }

    /**
     * Called on load.
     *
     * Loads all found Plugins.
     */
    @Override
    public void onLoad() throws IOException {
        File[] files;
        if (!this.getFile().exists()) {
            this.getFile().mkdirs();
            this.getFile().mkdir();
        }
        if ((files = this.getFile().listFiles()).length > 0) {
            for (File file : files) {
                if (!file.isFile()) continue;
                try {
                    JarFile jarFile = new JarFile(file);
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        if (!name.endsWith(".class")) continue;
                        String className = name.replaceAll("/", ".");
                        className = className.substring(0, className.length() - 6);
                        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:///" + file.getAbsolutePath())});
                        Class<?> clazz = classLoader.loadClass(className);
                        if (clazz == null || !clazz.getSuperclass().equals(Plugin.class)) continue;
                        this.getList().add((Plugin)clazz.newInstance());
                    }
                }
                catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** @return if an update is needed or not. */
    @Override
    public boolean needsUpdate() {
        return false;
    }
}

