package eu.raidersheaven.rhtinthealth.commands;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.data.DataPlayer;
import eu.raidersheaven.rhtinthealth.utils.HexColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class TintHealthCommand extends Command {
    /**
     * Constructor
     *
     * @param name
     * @param description
     * @param usageMessage
     * @param aliases
     */
    public TintHealthCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    reload(player);
                } else if (args[0].equalsIgnoreCase("toggle")) {
                    toggle(player);
                }
            } else {
                sendUsage(player);
            }
        }
        return false;
    }

    /**
     * Send usage messages
     *
     * @param player
     */
    private void sendUsage(Player player) {
        RHTintHealth.get().getConfig().getStringList("commands.tinthealth.usage").forEach(message -> player.sendMessage(HexColor.format(message)));
    }

    /**
     * Reload configuration
     *
     * @param player
     */
    private void reload(Player player) {
        if (player.hasPermission("RHTintHealth.*") || player.hasPermission("RHTintHealth.reload")) {
            RHTintHealth.get().reloadConfig();
            player.sendMessage(HexColor.format(RHTintHealth.get().getConfig().getString("messages.reload")).replace("%prefix%", HexColor.format(RHTintHealth.get().getConfig().getString("messages.prefix"))));
        } else {
            player.sendMessage(HexColor.format(RHTintHealth.get().getConfig().getString("messages.no-permission")).replace("%prefix%", HexColor.format(RHTintHealth.get().getConfig().getString("messages.prefix"))));
        }
    }

    /**
     * Toggle health effect
     *
     * @param player
     */
    private void toggle(Player player) {
        DataPlayer dataPlayer = RHTintHealth.get().getDataPlayerManager().get(player);
        if (dataPlayer != null) {
            String message;
            dataPlayer.setToggle(!dataPlayer.isToggle());
            if (dataPlayer.isToggle()) {
                message = HexColor.format(Objects.requireNonNull(RHTintHealth.get().getConfig().getString("messages.tint.enabled")).replace("%prefix%", HexColor.format(RHTintHealth.get().getConfig().getString("messages.prefix"))));
            } else {
                message = HexColor.format(Objects.requireNonNull(RHTintHealth.get().getConfig().getString("messages.tint.disabled")).replace("%prefix%", HexColor.format(RHTintHealth.get().getConfig().getString("messages.prefix"))));
                RHTintHealth.get().getWorldManager().clearBorderEffect(player);
            }
            player.sendMessage(message);
        }
    }
}
