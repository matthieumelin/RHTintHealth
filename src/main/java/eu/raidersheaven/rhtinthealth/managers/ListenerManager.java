package eu.raidersheaven.rhtinthealth.managers;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.listeners.EntityDamageListener;

import java.util.Collections;

public class ListenerManager {
    /**
     * Constructor
     */
    public ListenerManager() {
        Collections.singletonList(
                new EntityDamageListener()
        ).forEach(listener -> RHTintHealth.get().getServer().getPluginManager().registerEvents(listener, RHTintHealth.get()));
    }
}
