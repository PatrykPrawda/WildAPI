package pl.wildlabs.wildapi.plugin;

import org.bukkit.Bukkit;

public class Logger {
    public static void log(String message) {
        String msg = message;
        msg = msg.replace("&", "§");
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    public static void debugLog(String message) {
        if (WildAPI.debug_enabled) {
            String msg = WildAPI.prefix_debug + message;
            msg = msg.replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(msg);
        }
    }

    public static void errorLog(String message) {
        String msg = message.replace("&", "§");
        Bukkit.getConsoleSender().sendMessage(msg);
    }
}
