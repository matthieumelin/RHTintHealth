package eu.raidersheaven.rhtinthealth.managers;

import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import com.github.yannicklamprecht.worldborder.api.WorldBorderAction;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.data.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Map;

public class WorldManager {
    private WorldBorderApi worldBorderApi;

    /**
     * Constructor
     */
    public WorldManager() {
        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = RHTintHealth.get().getServer().getServicesManager().getRegistration(WorldBorderApi.class);
        if (worldBorderApiRegisteredServiceProvider != null) {
            this.worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();
        }
    }

    /**
     * Play border effect
     *
     * @param player
     */
    public void playBorderEffect(Player player) {
        final int[] distance = {256};

        worldBorderApi.setBorder(player, distance[0], player.getLocation());

        IWorldBorder iWorldBorder = worldBorderApi.getWorldBorder(player);
        iWorldBorder.setWarningDistanceInBlocks((int) iWorldBorder.getSize());
        iWorldBorder.send(player, WorldBorderAction.SET_WARNING_BLOCKS);

        long time = 0L;
        DataPlayerManager dataPlayerManager = RHTintHealth.get().getDataPlayerManager();
        DataPlayer dataPlayer = dataPlayerManager.get(player);

        if (dataPlayer != null) {
            Map<Player, Integer> tasks = dataPlayer.getTasks();
            // Check task is already created
            dataPlayer.cancelTasks();
            // Init a new task
            int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(RHTintHealth.get(), () -> {
                iWorldBorder.setWarningDistanceInBlocks(distance[0]);
                iWorldBorder.send(player, WorldBorderAction.SET_WARNING_BLOCKS);
                distance[0] = distance[0] - 4;

                // Check distance is equal to zero
                if (distance[0] <= 0) {
                    // Cancel task
                    dataPlayer.cancelTasks();
                }
            }, 0, time + 1);

            // Add task to map
            tasks.put(player, task);
        }

    }

    /**
     * Clear border effect
     *
     * @param player
     */
    public void clearBorderEffect(Player player) {
        worldBorderApi.sendRedScreenForSeconds(player, 0, RHTintHealth.get());
    }

    /**
     * Get world border api
     *
     * @return World border API
     */
    public WorldBorderApi getWorldBorderApi() {
        return worldBorderApi;
    }
}
