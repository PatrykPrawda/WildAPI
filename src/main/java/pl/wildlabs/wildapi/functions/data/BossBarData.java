package pl.wildlabs.wildapi.functions.data;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarData {
    Player player;
    BossBar bossBar;

    public BossBarData(Player p, BossBar b) {
        this.player = p;
        this.bossBar = b;
    }

    public Player getPlayer() {
        return this.player;
    }

    public BossBar getBossBar() {
        return this.bossBar;
    }

    public boolean checkByBossBarData(BossBarData b) {
        return (b.getPlayer() == this.player && b.getBossBar() == this.bossBar);
    }

    public boolean checkByBossHealthBar(BossBar b) {
        return (b == this.bossBar);
    }

    public boolean checkByPlayer(Player p) {
        return (p == this.player);
    }
}
