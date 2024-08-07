package me.friendly.exeter.module;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.io.File;
import java.util.*;

import me.friendly.api.interfaces.Labeled;
import me.friendly.exeter.core.Exeter;
import me.friendly.exeter.presets.Preset;
import me.friendly.exeter.properties.NumberProperty;
import me.friendly.exeter.properties.Property;
import net.minecraft.client.Minecraft;

/**
 * Client Module.
 * An implementation of Labeled
 */
public class Module
implements Labeled {
    private final String label;
    private String tag;
    private final String[] aliases;
    /** Properties for the Module. */
    private final List<Property<?>> properties = new ArrayList<>();
    private final List<Preset> presets = new ArrayList<Preset>();
    protected Minecraft minecraft = Minecraft.getMinecraft();

    /**
     * Creates a new Module. It's important that the given label
     * does not contain any whitespaces and that no modules with the
     * same name exist. A modules name is its unique identifier.
     *
     * @param label the label for the new module.
     * @param aliases the aliases of the new module.
     */
    protected Module(String label, String[] aliases) {
        this.label = this.tag = label;
        this.aliases = aliases;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public String getTag() {
        return this.tag;
    }

    protected void setTag(String tag) {
        this.tag = tag;
    }

    public List<Property<?>> getProperties() {
        return this.properties;
    }

    protected void offerProperties(Property<?> ... properties) {
        this.properties.addAll(Arrays.asList(properties));
        // We dont want alphabet sort
        //this.properties.sort((p1, p2) -> p1.getAliases()[0].compareTo(p2.getAliases()[0]));
    }

    public Property<?> getPropertyByAlias(String alias) {
        for (Property<?> property : properties) {
            for (String propertyAlias : property.getAliases()) {
                if (!alias.equalsIgnoreCase(propertyAlias)) continue;
                return property;
            }
        }
        return null;
    }

    public List<Preset> getPresets() {
        return this.presets;
    }

    protected void offsetPresets(Preset ... presets) {
        for (Preset preset : presets) {
            this.presets.add(preset);
        }
        this.presets.sort((p1, p2) -> p1.getLabel().compareTo(p2.getLabel()));
    }

    public Preset getPresetByLabel(String label) {
        for (Preset preset : presets) {
            if (!label.equalsIgnoreCase(preset.getLabel())) continue;
            return preset;
        }
        return null;
    }

    /**
     * Loads module property values from saved config.
     * Parses files in json format using Google Gson.
     * Called by {@link ModuleManager}
     *
     * @param node
     */
    public void loadConfig(JsonObject node) {
        File modsFolder = new File(Exeter.getInstance().getDirectory(), "modules");
        if (!modsFolder.exists()) {
            modsFolder.mkdir();
        }
        node.entrySet().forEach(entry -> {
            Optional<Property> property1 = null;
            for (Property<?> prop : getProperties()) {
                if (property1 != null || !prop.getAliases()[0].equalsIgnoreCase((entry.getKey()).toLowerCase())) continue;
                property1 = Optional.ofNullable(prop);
            }
            if (property1 != null && property1.isPresent()) {
                Object type = (entry.getValue()).getAsString();
                if ((property1.get()).getValue() instanceof Number) {
                    if ((property1.get()).getValue() instanceof Integer) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsInt();
                    } else if (property1.get().getValue() instanceof Long) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsLong();
                    } else if (property1.get().getValue() instanceof Boolean) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsBoolean();
                    } else if (property1.get().getValue() instanceof Double) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsDouble();
                    } else if (property1.get().getValue() instanceof Float) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsFloat();
                    }
                } else {
                    if ((property1.get()).getValue() instanceof Enum) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsString();
                        (property1.get()).setValue(type.toString());
                        return;
                    }
                    if (property1.get().getValue() instanceof Boolean) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsBoolean();
                    } else if (property1.get().getValue() instanceof String) {
                        type = (entry.getValue()).getAsJsonPrimitive().getAsString();
                    }
                }
                property1.get().setValue(type);
            }
        });
    }

    /**
     * Saves module property values from saved config.
     * saves to file in json format using Google Gson.
     * Called by {@link ModuleManager}
     */
    public void saveConfig() {
        File modsFolder = new File(Exeter.getInstance().getDirectory(), "modules");
        if (!modsFolder.exists()) {
            modsFolder.mkdir();
        }
        if (this.getProperties().size() < 1) {
            return;
        }
        File jsonFile = new File(modsFolder, this.getLabel().toLowerCase().replace(" ", "") + ".json");
        if (jsonFile.exists()) {
            jsonFile.delete();
        } else {
            try {
                jsonFile.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = jsonFile;
        JsonObject node = new JsonObject();
        Collection<Property> settings1 = Collections.unmodifiableCollection(this.getProperties());
        settings1.forEach(setting -> {
            if (setting instanceof NumberProperty) {
                return;
            }
            node.addProperty(setting.getAliases()[0], setting.getValue().toString());
        });
        if (node.entrySet().isEmpty()) {
            return;
        }
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (FileWriter writer = new FileWriter(file);){
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)node));
        }
        catch (IOException e) {
            e.printStackTrace();
            file.delete();
        }
    }
}

