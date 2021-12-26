package eu.raidersheaven.rhtinthealth.listeners;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.data.DataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        DataPlayer dataPlayer = RHTintHealth.get().getDataPlayerManager().get(event.getEntity());
        if (dataPlayer != null) {
            // Cancel active tasks
            RHTintHealth.get().getDataPlayerManager().get(event.getEntity()).cancelTasks();
        }
    }
}
