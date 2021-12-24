package eu.raidersheaven.rhtinthealth;

import eu.raidersheaven.rhtinthealth.managers.CommandManager;
import eu.raidersheaven.rhtinthealth.managers.ConfigManager;
import eu.raidersheaven.rhtinthealth.managers.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RHTintHealth extends JavaPlugin {
    private static RHTintHealth instance;

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;

        // Save default config
        saveDefaultConfig();

        // Init all managers
        configManager = new ConfigManager();

        new ListenerManager();
        new CommandManager();
    }

    /**
     * Get plugin instance
     * @return Plugin instance
     */
    public static RHTintHealth get() {
        return instance;
    }

    /**
     * Get config manager
     * @return Config manager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }
}
