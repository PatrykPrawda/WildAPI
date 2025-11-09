package pl.wildlabs.wildapi.plugin.commands;

import org.bukkit.command.CommandSender;
import pl.wildlabs.wildapi.plugin.WildAPI;
import pl.wildlabs.wildapi.functions.display.Display;
import pl.wildlabs.wildapi.functions.interfaces.command.IPluginCommand;

import java.util.Arrays;

public class Remove implements IPluginCommand {
    @Override
    public String command() {
        return "remove";
    }

    @Override
    public String[] subCommands() {
        return new String[] { "bossbars" };
    }

    @Override
    public String description() {
        return "Remove all bossbars";
    }

    @Override
    public String invalidSubcommand() {
        return "Use: " + command() + " (" + String.join("|", Arrays.toString(subCommands()) + ")");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args[1].equalsIgnoreCase("bossbars"))
            Display.removeAllBossBars(WildAPI.plugin);
    }
}
