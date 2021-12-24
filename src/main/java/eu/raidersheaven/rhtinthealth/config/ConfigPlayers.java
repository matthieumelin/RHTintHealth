package eu.raidersheaven.rhtinthealth.config;

public class ConfigPlayers extends Config {
    /**
     * Constructor
     *
     * @param name
     */
    public ConfigPlayers(String name) {
        super(name);
    }

    @Override
    public void addDefaults() {
        // Enable default values
        config.options().copyDefaults(true);

        // Save config
        save();
    }
}
