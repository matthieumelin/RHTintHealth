package eu.raidersheaven.rhtinthealth.managers;

import eu.raidersheaven.rhtinthealth.config.Config;
import eu.raidersheaven.rhtinthealth.config.ConfigPlayers;

import java.util.Collections;
import java.util.List;

public class ConfigManager {
    private final List<Config> configs;

    /**
     * Constructor
     */
    public ConfigManager() {
        this.configs = Collections.singletonList(
                new ConfigPlayers("players")
        );
    }

    /**
     * Get config
     * @param name
     * @return Config
     */
    public Config get(String name) {
        return configs.stream().filter(config -> config != null && config.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    /**
     * Get configs
     * @return Configs
     */
    public List<Config> getConfigs() {
        return configs;
    }
}
