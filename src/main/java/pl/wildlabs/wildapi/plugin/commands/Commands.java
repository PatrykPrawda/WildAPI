package pl.wildlabs.wildapi.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.wildlabs.wildapi.functions.interfaces.command.IPluginCommand;

import java.util.Arrays;

public class Commands implements CommandExecutor {

    private IPluginCommand[] commands = {
      new Check(),
            new Remove()
    };

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                for(IPluginCommand command : commands) {
                    if(args[0].equalsIgnoreCase(command.command())) {
                        for(String subCommand : command.subCommands()) {
                            if(args[1].equalsIgnoreCase(subCommand)) {
                                command.execute(sender, args);
                            }
                        }
                        command.invalidSubcommand();
                    }
                }
                sender.sendMessage("§Command not found, use §f/foxapi §cto see commands list");
            } else {
                sender.sendMessage("§8〔§c§l-§8〕 §7Fox§6§lAPI §8「§c§l+§8」 §7Commands §8〔§c§l-§8〕");
                for(IPluginCommand command : commands) {
                    sender.sendMessage("§f§o* §e§o/foxapi " + command.command() + " (" + String.join("|", Arrays.toString(command.subCommands()) + ") §f- " + command.description()));
                }
            }
            return true;
        }
        sender.sendMessage("This command can only be run by a player.");

        return false;
    }
}
