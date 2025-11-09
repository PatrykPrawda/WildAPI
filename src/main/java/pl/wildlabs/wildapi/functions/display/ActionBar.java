package pl.wildlabs.wildapi.functions.display;

import pl.wildlabs.wildapi.plugin.Logger;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

class ActionBar {
    Logger logger = new Logger();

    static void displayActionBar(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(message));
        Logger.debugLog("Displayed actionbar for " + player.getDisplayName());
    }
}
