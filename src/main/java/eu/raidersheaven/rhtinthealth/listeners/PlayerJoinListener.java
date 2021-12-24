package eu.raidersheaven.rhtinthealth.listeners;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration configPlayers = RHTintHealth.get().getConfigManager().get("players").getConfig();

        // Player is not registered in players config, so its added
        if (!configPlayers.contains("players." + player.getName())) {
            configPlayers.set("players." + player.getName() + ".toggle", false);
        }
    }
}
