package pl.wildlabs.wildapi.functions.interfaces.enchant;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface IEnchantExecutor {

    void execute(EntityDamageByEntityEvent event, IEnchant enchant, int enchantLevel);
}
