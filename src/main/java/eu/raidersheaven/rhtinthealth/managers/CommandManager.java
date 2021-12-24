package eu.raidersheaven.rhtinthealth.managers;

import eu.raidersheaven.rhtinthealth.RHTintHealth;
import eu.raidersheaven.rhtinthealth.commands.TintHealthCommand;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

public class CommandManager {
    /**
     * Constructor
     */
    public CommandManager() {
        try {
            Field field = RHTintHealth.get().getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(RHTintHealth.get().getServer());

            Collections.singletonList(
                    new TintHealthCommand("thinthealth", "ThintHealth Description", "thinthealth", Arrays.asList("tint", "th"))
            ).forEach(command -> commandMap.register(command.getName(), command));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
