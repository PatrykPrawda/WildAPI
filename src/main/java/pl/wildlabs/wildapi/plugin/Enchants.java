package pl.wildlabs.wildapi.plugin;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import pl.wildlabs.wildapi.functions.experimental.Functions;
import pl.wildlabs.wildapi.functions.interfaces.enchant.IEnchant;

import java.util.ArrayList;
import java.util.Objects;

public class Enchants {

    ArrayList<IEnchant> enchants = new ArrayList<>();

    public void registerEnchant(IEnchant enchant) {
        enchants.add(enchant);
    }

    public void executeEnchant(EntityDamageByEntityEvent event) {

        if(event != null) {

            Player p = null;
            LivingEntity e = null;

            if(event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if(arrow.getShooter() instanceof Player)
                    p = (Player) arrow.getShooter();
                else
                    e = (LivingEntity) arrow.getShooter();
            } else {
                if(event.getDamager() instanceof Player) {
                    p = (Player) event.getDamager();
                } else {
                    e = (LivingEntity) event.getDamager();
                }
            }

            ItemMeta itemInMainHandMeta = null;

            if(p != null) {
                itemInMainHandMeta = p.getInventory().getItemInMainHand().getItemMeta();
            } else {
                assert e != null;
                itemInMainHandMeta = Objects.requireNonNull(e.getEquipment()).getItemInMainHand().getItemMeta();
            }

            assert itemInMainHandMeta != null;

            for (IEnchant enchant : enchants) {
                for (int i = 0; i < Objects.requireNonNull(itemInMainHandMeta.getLore()).size(); i++) {
                    String lore = Objects.requireNonNull(itemInMainHandMeta.getLore()).get(i);
                    if (lore.contains(enchant.friendlyName()))
                        enchant.getExecutor().execute(event, enchant, Functions.fromRoman(lore.replace(enchant.friendlyName() + " ", "")));
                }
            }
        }
    }
}
