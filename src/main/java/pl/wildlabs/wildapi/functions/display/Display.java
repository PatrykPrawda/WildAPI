package pl.wildlabs.wildapi.functions.display;

import javax.annotation.Nullable;
import pl.wildlabs.wildapi.plugin.Logger;
import pl.wildlabs.wildapi.plugin.WildAPI;
import pl.wildlabs.wildapi.functions.yaml.Yaml;
import pl.wildlabs.wildapi.functions.data.BossBarData;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Display {

    public static void displayActionBar(Player player, String Message) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.action_bars")) {
            if (player != null && Message != null && player.isOnline())
                ActionBar.displayActionBar(player, Message);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    @Deprecated
    public static void displayBossBar(Player player, String Message, int Filled, BarColor Color) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.boss_bars")) {
            if (player != null && Message != null && Filled >= 0 && Filled <= 100 && Color != null && player.isOnline())
                BossHealthBar.display(player, Message, Filled, Color);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static BossBarData createBossBarData(Player player, String Message, int Filled, BarColor Color) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.boss_bars")) {
            if (player != null && Message != null && Filled >= 0 && Filled <= 100 && Color != null) {
                if (player.isOnline())
                    return BossHealthBar.displayAndReturnBossBarData(player, Message, Filled, Color);
                return null;
            }
            return null;
        }
        Logger.log("&cThis function is disabled.");
        return null;
    }

    @Deprecated
    public static void updateBossBar(Player player, String Message, int Filled, BarColor Color) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.boss_bars")) {
            if (player != null && Message != null && Filled >= 0 && Filled <= 100 && Color != null && player.isOnline())
                BossHealthBar.update(player, Message, Filled, Color);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    @Deprecated
    public static void removeBossBar(Player player) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.boss_bars")) {
            if (player != null && player.isOnline())
                BossHealthBar.removeByPlayer(player);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void removeBossBarByData(BossBarData bossBarData) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.boss_bars")) {
            if (bossBarData != null)
                BossHealthBar.removeByBossBarData(bossBarData);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void displayTitleAndSubTitle(Player player, String Title, @Nullable String SubTitle, @Nullable int FadeIn, @Nullable int Stay, @Nullable int FadeOut) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.title_and_subtitle")) {
            if (FadeIn < 1)
                FadeIn = 1;
            if (Stay < 20)
                Stay = 20;
            if (FadeOut < 1)
                FadeOut = 1;
            if (player != null && Title != null && player.isOnline())
                TitleAndSubtitle.DisplayTitle(player, Title, SubTitle, FadeIn, Stay, FadeOut);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void createHologram(String name, String text, Location location, int time) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.holograms")) {
            if (name != null && text != null && location != null)
                Hologram.createHologram(name, text, location, time);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void createHologramBelow(String parentHologramName, String childHologramName, String text, int time) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.holograms")) {
            if (parentHologramName != null && childHologramName != null && text != null)
                Hologram.createHologramBelow(parentHologramName, childHologramName, text, time);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void removeHologram(String name) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.holograms")) {
            if (name != null)
                Hologram.removeHologram(name);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void updateHologram(String name, String text) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.holograms")) {
            if (name != null && text != null)
                Hologram.updateHologram(name, text);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void checkHologram(LivingEntity entity) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.display.holograms")) {
            if (entity != null)
                Hologram.checkHolograms(entity);
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }

    public static void removeAllBossBars(Plugin plugin) {
        if (plugin == WildAPI.plugin) {
            BossHealthBar.removeAllBossBars();
        } else {
            Logger.log("&e" + plugin.getName() + " &7don't have access to &cremove all boss bars&7!");
        }
    }

    public static void removeAllHolograms(Plugin plugin) {
        if (plugin == WildAPI.plugin) {
            Hologram.removeAllHolograms();
        } else {
            Logger.log("&e" + plugin.getName() + " &7don't have access to &cremove all holograms&7!");
        }
    }

    public static void loadAllHolograms(Plugin plugin) {
        if (plugin == WildAPI.plugin) {
            Hologram.loadAllHolograms();
        } else {
            Logger.log("&e" + plugin.getName() + " &7don't have access to &cload all holograms&7!");
        }
    }
}
