package pl.wildlabs.wildapi.plugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.wildlabs.wildapi.plugin.Enchants;

public class EntityDamageByEntity implements Listener {

    Enchants _enchants;

    public EntityDamageByEntity(Enchants enchants) {
        _enchants = enchants;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        _enchants.executeEnchant(event);
    }
}
