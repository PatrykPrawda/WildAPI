package pl.wildlabs.wildapi.functions.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class World {
    public static int getSafeY(Player player, int positionX, int positionZ) {
        return GetSafe.getSafeY(player.getWorld(), positionX, positionZ);
    }

    public static int getSafeY(org.bukkit.World world, int positionX, int positionZ) {
        return GetSafe.getSafeY(world, positionX, positionZ);
    }

    public static Location getRandomSafeLocation(Player player, int maxX, int maxZ) {
        return GetSafe.getRandomSafeLocation(player.getWorld(), maxX, maxZ);
    }

    public static Location getRandomSafeLocation(org.bukkit.World world, int maxX, int maxZ) {
        return GetSafe.getRandomSafeLocation(world, maxX, maxZ);
    }

    public static Material getBlockMaterial(Location location) {
        return GetBlock.getBlockMaterial(location);
    }

    public static Material getBlockMaterial(org.bukkit.World world, Location location) {
        return GetBlock.getBlockMaterial(new Location(world, location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    public static Material getBlockMaterial(org.bukkit.World world, int positionX, int positionY, int positionZ) {
        return GetBlock.getBlockMaterial(new Location(world, positionX, positionY, positionZ));
    }

    public static Block getBlock(Location location) {
        return GetBlock.getBlock(location);
    }

    public static Block getBlock(org.bukkit.World world, Location location) {
        return GetBlock.getBlock(new Location(world, location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    public static Block getBlock(org.bukkit.World world, int positionX, int positionY, int positionZ) {
        return GetBlock.getBlock(new Location(world, positionX, positionY, positionZ));
    }
}
