package pl.wildlabs.wildapi.functions.experimental;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.wildlabs.wildapi.functions.interfaces.enchant.IEnchant;
import pl.wildlabs.wildapi.functions.interfaces.enchant.IEnchantExecutor;

public class test implements IEnchant, IEnchantExecutor {

    @Override
    public String name() {
        return "test";
    }

    @Override
    public String friendlyName() {
        return "Test enchant";
    }

    @Override
    public int maxLevel() {
        return 1;
    }

    @Override
    public IEnchantExecutor getExecutor() {
        return this;
    }

    private final Material[] canPutOn = {
            Material.BOW
    };
    private final Enchantment[] incompatibleWith = {

    };
    private final String[] incompatibleWithCustomEnchant = {

    };

    @Override
    public void execute(EntityDamageByEntityEvent event, IEnchant enchant, int enchantLevel) {

    }
}
