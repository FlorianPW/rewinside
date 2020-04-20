package net.quatulo.lobby.utilities;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileBuilder {
    private File file;
    private YamlConfiguration configuration;

    public FileBuilder(String path) {
        this.file = new File(path);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileBuilder addDefault(String path, Object object) {
        this.configuration.options().copyDefaults(true);
        this.configuration.addDefault(path, object);
        save();

        return this;
    }

    public FileBuilder setValue(String path, Object object) {
        this.configuration.set(path, object);
        save();

        return this;
    }

    public Object getValue(String path) {
        return this.configuration.get(path);
    }

    public String getString(String path) {
        return this.configuration.getString(path);
    }

    public Integer getInteger(String path) {
        return Integer.valueOf(this.configuration.getInt(path));
    }

    public Double getDouble(String path) {
        return Double.valueOf(this.configuration.getDouble(path));
    }

    public Float getFloat(String path) {
        return Float.valueOf(Float.parseFloat(this.configuration.getString(path)));
    }

    public Short getShort(String path) {
        return Short.valueOf(Short.parseShort(this.configuration.getString(path)));
    }

    public Byte getByte(String path) {
        return Byte.valueOf(Byte.parseByte(this.configuration.getString(path)));
    }

    public Boolean getBoolean(String path) {
        return Boolean.valueOf(this.configuration.getBoolean(path));
    }

    public List<String> getStringList(String path) {
        return this.configuration.getStringList(path);
    }

    public FileBuilder save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException ex) {
            ex.getMessage();
        }
        return this;
    }
}
