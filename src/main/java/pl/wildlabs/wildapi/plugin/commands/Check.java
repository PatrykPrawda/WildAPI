package pl.wildlabs.wildapi.plugin.commands;

import org.bukkit.command.CommandSender;
import pl.wildlabs.wildapi.functions.interfaces.command.IPluginCommand;

import java.util.Arrays;

class Check implements IPluginCommand {

    @Override
    public String command() {
        return "update";
    }

    @Override
    public String[] subCommands() {
        return new String[] { "check" };
    }

    @Override
    public String description() {
        return "Check for updates";
    }

    @Override
    public String invalidSubcommand() {
        return "Use: " + command() + " (" + String.join("|", Arrays.toString(subCommands()) + ")");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§8〔§c§l-§8〕 §7Fox§6§lAPI §8「§c§l+§8」 §7Updater §8〔§c§l-§8〕");
        sender.sendMessage("§f§o* §cThis function is not ready for use.");
    }
}
