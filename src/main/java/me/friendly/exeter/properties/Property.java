package me.friendly.exeter.properties;

public class Property<T> {
    private final String[] aliases;
    protected T value;

    public Property(T value, String... aliases) {
        this.value   = value;
        this.aliases = aliases;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /**
     * This method does not exist in Exeter 1.8,
     * but was added as part of the process in
     * making it buildable. Given that this is
     * decompiled code, some errors exist and have
     * to be manually fixed. This method was added
     * to fix an error in {@link EnumProperty}, and
     * should only be called from {@link EnumProperty}
     * Objects.
     *
     * @author Gopro336
     * @param value the string value input
     */
    public void setValue(String value) {}
}

