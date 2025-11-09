package pl.wildlabs.wildapi.functions.interfaces.enchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public interface IEnchant {

    String name();
    String friendlyName();

    int maxLevel();

    Material[] canPutOn = new Material[0];

    Enchantment[] incompatibleWith = new Enchantment[0];
    String[] incompatibleWithCustomEnchant = new String[0];

    IEnchantExecutor getExecutor();
}
