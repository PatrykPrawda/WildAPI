package pl.wildlabs.wildapi.functions.world;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

class GetSafe {
    static int getSafeY(org.bukkit.World world, int positionX, int positionZ) {

        int minY = world.getMinHeight();

        for(int i = world.getMaxHeight(); i > minY; i--) {
            Location location = new Location(world, positionX, i, positionZ);
            Location locationBelow = new Location(world, positionX, i - 1, positionZ);

            if(location.getBlock().getType() == Material.AIR && locationBelow.getBlock().getType() != Material.AIR)
                return i;
        }
        return world.getMaxHeight();
    }

    static Location getRandomSafeLocation(org.bukkit.World world, int maxX, int maxZ) {

        int randomX = new Random().nextInt(maxX * 2) - maxX;
        int randomZ = new Random().nextInt(maxZ * 2) - maxZ;
        int safeY = getSafeY(world, randomX, randomZ);

        return new Location(world, randomX, safeY, randomZ);
    }
}
