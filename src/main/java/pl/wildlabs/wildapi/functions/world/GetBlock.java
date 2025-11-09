package pl.wildlabs.wildapi.functions.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

class GetBlock {
    static Material getBlockMaterial(Location location) {
        return location.getBlock().getType();
    }

    static Block getBlock(Location location) {
        return location.getBlock();
    }
}
