package pl.wildlabs.wildapi.functions.interfaces.command;

import org.bukkit.command.CommandSender;

public interface IPluginCommand {

    String command();
    String[] subCommands();
    String description();

    String invalidSubcommand();

    void execute(CommandSender sender, String[] args);
}
