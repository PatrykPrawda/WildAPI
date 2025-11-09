package pl.wildlabs.wildapi.plugin;

import pl.wildlabs.wildapi.functions.display.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

class Events implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntityType().equals(EntityType.ARMOR_STAND))
            Display.checkHologram(event.getEntity());
    }
}
