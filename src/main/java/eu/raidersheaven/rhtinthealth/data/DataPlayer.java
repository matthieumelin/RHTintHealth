package eu.raidersheaven.rhtinthealth.data;

import org.bukkit.entity.Player;

public class DataPlayer {
    private final Player player;
    private boolean toggle;

    /**
     * Constructor
     * @param player
     * @param toggle
     */
    public DataPlayer(Player player, boolean toggle) {
        this.player = player;
        this.toggle = toggle;
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
}
