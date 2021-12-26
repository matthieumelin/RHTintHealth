package eu.raidersheaven.rhtinthealth;

import eu.raidersheaven.rhtinthealth.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RHTintHealth extends JavaPlugin {
    private static RHTintHealth instance;

    private WorldManager worldManager;
    private ConfigManager configManager;
    private DataPlayerManager dataPlayerManager;

    @Override
    public void onEnable() {
        instance = this;

        // Save default config
        saveDefaultConfig();

        // Init all managers
        worldManager = new WorldManager();
        configManager = new ConfigManager();
        dataPlayerManager = new DataPlayerManager();

        new ListenerManager();
        new CommandManager();
    }

    @Override
    public void onDisable() {
        // Stop all tasks
        Bukkit.getScheduler().cancelTasks(this);
    }

    /**
     * Get plugin instance
     * @return Plugin instance
     */
    public static RHTintHealth get() {
        return instance;
    }

    /**
     * Get world manager
     * @return World manager
     */
    public WorldManager getWorldManager() {
        return worldManager;
    }

    /**
     * Get config manager
     * @return Config manager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Get data player
     * @return Data player
     */
    public DataPlayerManager getDataPlayerManager() {
        return dataPlayerManager;
    }
}
