package eu.raidersheaven.rhtinthealth.listeners;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Create player cache
        RHTintHealth.get().getDataPlayerManager().create(event.getPlayer());
    }
}
