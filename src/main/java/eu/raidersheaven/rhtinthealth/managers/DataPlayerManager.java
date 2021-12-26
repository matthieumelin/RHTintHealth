package eu.raidersheaven.rhtinthealth.managers;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.config.Config;
import eu.raidersheaven.rhtinthealth.data.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataPlayerManager {
    private final List<DataPlayer> datas;

    private final Config configPlayers;

    /**
     * Constructor
     */
    public DataPlayerManager() {
        this.datas = new ArrayList<>();
        this.configPlayers = RHTintHealth.get().getConfigManager().get("players");
        this.loadAll();
    }

    /**
     * Load all datas
     */
    private void loadAll() {
        ConfigurationSection section = configPlayers.getConfig().getConfigurationSection("players");
        if (section != null) {
            section
                    .getKeys(false).forEach(key -> {
                        Player player = Bukkit.getPlayer(key);
                        if (player != null) {
                            create(player);
                        }
                    });
        }
    }

    /**
     * Create data for player
     *
     * @param player
     */
    public void create(Player player) {
        // Check player is not registered in players config, so its added
        if (!configPlayers.getConfig().contains("players." + player.getName())) {
            configPlayers.getConfig().set("players." + player.getName() + ".toggle", true);
            configPlayers.save();
        }
        datas.add(new DataPlayer(player, configPlayers.getConfig().getBoolean("players." + player.getName() + ".toggle")));
    }

    /**
     * Delete date for player
     *
     * @param player
     */
    public void delete(Player player) {
        DataPlayer dataPlayer = get(player);
        if (dataPlayer != null) {
            configPlayers.getConfig().set("players." + player.getName() + ".toggle", dataPlayer.isToggle());
            configPlayers.save();
            datas.remove(dataPlayer);
        }
    }

    /**
     * Get data
     *
     * @param player
     * @return Data player
     */
    public DataPlayer get(Player player) {
        return datas.stream().filter(data -> data != null && data.getPlayer().equals(player)).findFirst().orElse(new DataPlayer(player, false));
    }
}
