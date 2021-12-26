package eu.raidersheaven.rhtinthealth.listeners;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity damagedEntity = event.getEntity();
        // Damaged entity is a player
        if (damagedEntity instanceof Player) {
            Player damaged = (Player) damagedEntity;
            if (event.getDamage() >= RHTintHealth.get().getConfig().getDouble("tint.minimum-damage")) {
               // Tint is enabled
                if (RHTintHealth.get().getDataPlayerManager().get(damaged).isToggle()) {
                    // Player border effect
                    RHTintHealth.get().getWorldManager().playBorderEffect(damaged);
                }
            }
        }
    }
}
