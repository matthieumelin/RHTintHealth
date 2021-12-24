package eu.raidersheaven.rhtinthealth.config;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config {
    public final String name;
    public File file;
    public FileConfiguration config;

    /**
     * Constructor
     * @param name
     */
    public Config(String name) {
        this.name = name;
        this.create();
    }

    /**
     * Create config
     */
    private void create() {
        File dataFolder = new File(RHTintHealth.get().getDataFolder() + "/data");

        // Check if data folder exist
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        // Initialize config file
        file = new File(dataFolder + "/" + name + ".yml");

        // Config file is not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Initialize config
        config = new YamlConfiguration();

        // Load config
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public abstract void addDefaults();

    /**
     * Save config
     */
    public void save(){
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get config name
     * @return Config name
     */
    public String getName() {
        return name;
    }

    /**
     * Get config file
     * @return Config file
     */
    public File getFile() {
        return file;
    }

    /**
     * Get config
     * @return Config
     */
    public FileConfiguration getConfig() {
        return config;
    }
}
