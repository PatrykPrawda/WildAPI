package pl.wildlabs.wildapi.functions.display;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import pl.wildlabs.wildapi.plugin.Logger;
import pl.wildlabs.wildapi.plugin.WildAPI;
import pl.wildlabs.wildapi.functions.yaml.Yaml;
import pl.wildlabs.wildapi.functions.data.HologramData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class Hologram {
    static ArrayList<HologramData> hologramData = new ArrayList<>();
    static boolean timer = false;
    static int maxHolograms = -1;
    static File hologramYml = new File(WildAPI.plugin.getDataFolder() + "/holograms.yml");
    static FileConfiguration hologramYmlConfig = YamlConfiguration.loadConfiguration(hologramYml);

    static void createHologramBelow(String hname, String name, String text, int time) {
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null) {
                if (Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".name")).equalsIgnoreCase(hname))
                    hologramData.add(new HologramData(name, text, time, new Location(Bukkit.getWorld(Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y") - 0.25D, hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z"))));
                createHologram(name, text, hologramData.get(hologramData.size() - 1).getLocation(), time);
            }
        }
    }

    static void createHologram(String name, String text, Location location, int time) {
        if (maxHolograms < 0) {
            maxHolograms = new Yaml(WildAPI.configFile).getIntValue("FoxAPI.options.holograms.max-holograms-per-chunk");
        }
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null &&
                    Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".name")).equalsIgnoreCase(name)) {
                return;
            }
        }

        int hologramsInChunk = 0;
        for (Entity en : location.getChunk().getEntities()) {
            if (en.getType() == EntityType.ARMOR_STAND && en.getCustomName() != null)
                hologramsInChunk++;
        }
        if (hologramsInChunk > maxHolograms) {
            return;
        }
        Objects.requireNonNull(name, "hologram");
        Objects.requireNonNull(text, "&7Fox&e&lAPI");

        int lastEmptyId = 0;
        for (int j = 0; j < hologramYml.length(); j++) {
            if (hologramYmlConfig.getString("hologram.id_" + j + ".name") == null && hologramYmlConfig.getString("hologram.id_" + j + ".text") == null) {
                lastEmptyId = j;
                break;
            }
        }
        for (int j = 0; j < hologramYml.length(); j++) {
            if (hologramYmlConfig.getString("hologram.id_" + j + ".name") != null &&
                    Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + j + ".name")).equalsIgnoreCase(name)) {
                for (Entity en : location.getChunk().getEntities()) {
                    if (en.getCustomName() != null &&
                            en.getCustomName().equalsIgnoreCase(hologramYmlConfig.getString("hologram.id_" + j + ".text"))) {
                        en.remove();
                        hologramYmlConfig.set("hologram.id_" + j, null);
                        saveHolograms(hologramYmlConfig, hologramYml);
                    }
                }
            }
        }

        text = ChatColor.translateAlternateColorCodes('&', text);

        Location l = new Location(location.getWorld(), location.getX(), location.getY() + 1.5D, location.getZ());
        ArmorStand h = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(l, EntityType.ARMOR_STAND);
        h.setVisible(false);
        h.setGravity(false);
        h.setInvulnerable(true);
        h.setCustomName(text);
        h.setCustomNameVisible(true);
        h.setMarker(true);
        if (!hologramYml.exists()) {
            try {
                hologramYml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".name", name);
        hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".location.world", location.getWorld().getName());
        hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".location.x", location.getX());
        hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".location.y", location.getY());
        hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".location.z", location.getZ());
        hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".text", text);
        if (time > 1)
            hologramYmlConfig.set("hologram.id_" + lastEmptyId + ".time", time);
        hologramData.add(new HologramData(name, text, time, location));
        saveHolograms(hologramYmlConfig, hologramYml);
        if (!timer) {
            RunTimer();
        }
        Logger.debugLog("Created hologram named " + name);
    }

    private static void RunTimer() {
        if (!timer)
            timer = true;
        Bukkit.getScheduler().runTaskLater((Plugin) WildAPI.plugin, new Runnable() {
            public void run() {
                Hologram.RunTimer();
                for (int i = 0; i < Hologram.hologramYml.length(); i++) {
                    if (Hologram.hologramYmlConfig.getString("hologram.id_" + i + ".name") != null &&
                            Hologram.hologramYmlConfig.getString("hologram.id_" + i + ".text") != null &&
                            Hologram.hologramYmlConfig.getString("hologram.id_" + i + ".time") != null) {
                        if (Hologram.hologramYmlConfig.getInt("hologram.id_" + i + ".time") <= 0) {
                            Hologram.removeHologram(Hologram.hologramYmlConfig.getString("hologram.id_" + i + ".name"));
                        } else {
                            Hologram.hologramYmlConfig.set("hologram.id_" + i + ".time", Hologram.hologramYmlConfig.getInt("hologram.id_" + i + ".time") - 1);
                        }
                    }
                }
            }
        }, 20L);
    }

    static void saveHolograms(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.debugLog("Saved all holograms");
    }

    static void removeHologram(String name) {
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null &&
                    Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".name")).equalsIgnoreCase(name)) {
                Location l = new Location(Bukkit.getWorld(Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z"));
                for (Entity en : l.getChunk().getEntities()) {
                    if (en.getCustomName() != null &&
                            en.getCustomName().equalsIgnoreCase(hologramYmlConfig.getString("hologram.id_" + i + ".text")) &&
                            en.getType() == EntityType.ARMOR_STAND) {
                        ((LivingEntity) en).remove();
                    }
                }
                hologramYmlConfig.set("hologram.id_" + i, null);
                saveHolograms(hologramYmlConfig, hologramYml);
            }
        }
    }

    static void createFromFile() {
        if (hologramYml.exists()) {
            for (int i = 0; i < hologramYml.length(); i++) {
                if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null) {
                    Location location = new Location(Bukkit.getWorld(Objects.requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z"));
                    createHologram(hologramYmlConfig.getString("hologram.id_" + i + ".name"), hologramYmlConfig.getString("hologram.id_" + i + ".text"), location, 0);
                }
            }
        }
    }

    static void updateHologram(String name, String text) {
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null && ((String)Objects.<String>requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".name"))).equalsIgnoreCase(name)) {
                Location l = new Location(Bukkit.getWorld(Objects.<String>requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z"));
                createHologram(name, text, l, hologramYmlConfig.getInt("hologram.id_" + i + ".time"));
                break;
            }
        }
        Logger.debugLog("Updated hologram named " + name);
    }

    static void checkHolograms(LivingEntity entity) {
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null) {
                Location l = new Location(Bukkit.getWorld(Objects.<String>requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z"));
                if (entity.getLocation().equals(l) && ((String)Objects.<String>requireNonNull(entity.getCustomName())).equalsIgnoreCase(hologramYmlConfig.getString("hologram.id_" + i + ".text"))) {
                    createHologram(hologramYmlConfig.getString("hologram.id_" + i + ".name"), hologramYmlConfig.getString("hologram.id_" + i + ".text"), l, hologramYmlConfig.getInt("hologram.id_" + i + "time"));
                }
            }
        }
        Logger.debugLog("Checking hologram named " + entity.getCustomName());
    }

    static void removeAllHolograms() {
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null && hologramYmlConfig.getString("hologram.id_" + i + ".text") != null) {
                Location l = new Location(Bukkit.getWorld(Objects.<String>requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z"));
                for (Entity en : l.getChunk().getEntities()) {
                    if (en.getCustomName() != null &&
                            en.getCustomName().equalsIgnoreCase(hologramYmlConfig.getString("hologram.id_" + i + ".text"))) {
                        en.remove();
                    }
                }
            }
        }
        Logger.debugLog("Removed all holograms");
    }

    static void loadAllHolograms() {
        for (int i = 0; i < hologramYml.length(); i++) {
            if (hologramYmlConfig.getString("hologram.id_" + i + ".name") != null && hologramYmlConfig.getString("hologram.id_" + i + ".text") != null) {
                createHologram(hologramYmlConfig.getString("hologram.id_" + i + ".name"), hologramYmlConfig.getString("hologram.id_" + i + ".text"), new Location(Bukkit.getWorld(Objects.<String>requireNonNull(hologramYmlConfig.getString("hologram.id_" + i + ".location.world"))), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.x"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.y"), hologramYmlConfig.getDouble("hologram.id_" + i + ".location.z")), hologramYmlConfig.getInt("hologram.id_" + i + ".text"));
            }
        }
        Logger.debugLog("Loaded all holograms");
    }
}
