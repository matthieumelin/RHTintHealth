package eu.raidersheaven.rhtinthealth.listeners;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.data.DataPlayer;
import eu.raidersheaven.rhtinthealth.managers.DataPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        DataPlayerManager dataPlayerManager = RHTintHealth.get().getDataPlayerManager();
        DataPlayer dataPlayer = dataPlayerManager.get(event.getPlayer());

        // Data player exist
        if (dataPlayer != null) {
            // Cancel active tasks
            dataPlayer.cancelTasks();
            // Delete player cache
            dataPlayerManager.delete(event.getPlayer());
        }
    }
}
