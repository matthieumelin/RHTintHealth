package eu.raidersheaven.rhtinthealth.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DataPlayer {
    private final Player player;
    private boolean toggle;
    private Map<Player, Integer> tasks;

    /**
     * Constructor
     * @param player
     * @param toggle
     */
    public DataPlayer(Player player, boolean toggle) {
        this.player = player;
        this.toggle = toggle;
        this.tasks = new HashMap<>();
    }

    /**
     * Cancel all tasks
     */
    public void cancelTasks() {
        // Player have an active task
        if (tasks.containsKey(player)) {
            Bukkit.getScheduler().cancelTask(tasks.get(player));
            tasks.remove(player);
        }
    }

    /**
     * Get player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Check is toggled
     * @return Toggle
     */
    public boolean isToggle() {
        return toggle;
    }

    /**
     * Set new toggle
     * @param toggle
     */
    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    /**
     * Get all tasks
     * @return Tasks
     */
    public Map<Player, Integer> getTasks() {
        return tasks;
    }
}
