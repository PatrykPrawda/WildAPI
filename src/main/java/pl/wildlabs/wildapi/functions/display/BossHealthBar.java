package pl.wildlabs.wildapi.functions.display;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import pl.wildlabs.wildapi.plugin.Logger;
import pl.wildlabs.wildapi.functions.data.BossBarData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

class BossHealthBar {
    static ArrayList<BossBarData> bossHealthBarData = new ArrayList<>();

    static void removeAllBossBars() {
        if (bossHealthBarData.size() > 0) {
            for (BossBarData bossHealthBarDatum : bossHealthBarData) {
                if (bossHealthBarDatum != null) {
                    removeByPlayer(bossHealthBarDatum.getPlayer());
                }
                return;
            }
        }
        Logger.debugLog("Removed all bossbars");
    }

    static void update(Player player, String Message, int Filled, BarColor Color) {
        Iterator<BossBarData> iterator = bossHealthBarData.iterator();
        if (iterator.hasNext()) {
            BossBarData bossHealthBarDatum = iterator.next();
            removeByPlayer(bossHealthBarDatum.getPlayer());
            display(player, Message, Filled, Color);
        }

        if (player != null)
            Logger.debugLog("Updated bossbar for " + player.getDisplayName());
    }

    static BossBarData updateAndReturnBossBarData(BossBarData bossBarData) {
        for (BossBarData bossHealthBarDatum : bossHealthBarData) {
            if (bossHealthBarDatum.equals(bossBarData)) {
                bossHealthBarDatum.getBossBar().removeAll();
                bossHealthBarData.remove(bossHealthBarDatum);
                BossBarData data = displayAndReturnBossBarData(bossBarData.getPlayer(), bossBarData.getBossBar().getTitle(), (int)bossBarData.getBossBar().getProgress(), bossBarData.getBossBar().getColor());
                bossHealthBarData.add(data);
                return data;
            }
        }
        return null;
    }

    static void display(Player player, String Message, int Filled, BarColor Color) {
        Objects.requireNonNull(Message, "&7Fox&c&lAPI");

        Message = ChatColor.translateAlternateColorCodes('&', Message);

        removeByPlayer(player);

        BossBar bossBar = Bukkit.createBossBar(Message, Color, BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
        bossBar.addPlayer(player);
        bossBar.setProgress((Filled / 100.0F));
        bossBar.setVisible(true);
        bossHealthBarData.add(new BossBarData(player, bossBar));

        Logger.debugLog("Displayed bossbar for " + player.getDisplayName());
    }

    static BossBarData displayAndReturnBossBarData(Player player, String Message, int Filled, BarColor Color) {
        Objects.requireNonNull(Message, "&7Fox&c&lAPI");

        Message = ChatColor.translateAlternateColorCodes('&', Message);

        removeByPlayer(player);

        BossBar bossBar = Bukkit.createBossBar(Message, Color, BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
        bossBar.addPlayer(player);
        bossBar.setProgress((Filled / 100.0F));
        bossBar.setVisible(true);
        BossBarData bossBarData = new BossBarData(player, bossBar);
        bossHealthBarData.add(bossBarData);

        Logger.debugLog("Displayed bossbar for " + player.getDisplayName());

        return bossBarData;
    }

    static void removeByPlayer(Player player) {
        for (BossBarData bossHealthBarDatum : bossHealthBarData) {
            if (bossHealthBarDatum.checkByPlayer(player)) {
                bossHealthBarDatum.getBossBar().removeAll();
                bossHealthBarData.remove(bossHealthBarDatum);
                break;
            }
        }
        if (player != null)
            Logger.debugLog("Removed bossbar for " + player.getDisplayName());
    }

    static void removeByBossBarData(BossBarData bossBarData) {
        for (BossBarData bossHealthBarDatum : bossHealthBarData) {
            if (bossHealthBarDatum.equals(bossBarData)) {
                bossHealthBarDatum.getBossBar().removeAll();
                bossHealthBarData.remove(bossHealthBarDatum);
                break;
            }
        }
        Logger.debugLog("Removed bossbar for " + bossBarData.getPlayer().getDisplayName());
    }
}
