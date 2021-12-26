package eu.raidersheaven.rhtinthealth.managers;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.listeners.EntityDamageListener;
import eu.raidersheaven.rhtinthealth.listeners.PlayerDeathListener;
import eu.raidersheaven.rhtinthealth.listeners.PlayerJoinListener;
import eu.raidersheaven.rhtinthealth.listeners.PlayerQuitListener;

import java.util.Arrays;

public class ListenerManager {
    /**
     * Constructor
     */
    public ListenerManager() {
        Arrays.asList(
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new PlayerDeathListener(),
                new EntityDamageListener()
        ).forEach(listener -> RHTintHealth.get().getServer().getPluginManager().registerEvents(listener, RHTintHealth.get()));
    }
}
