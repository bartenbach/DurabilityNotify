package org.hopto.seed419.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.Armor;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/30/12
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class CombatListener implements Listener {

    //TODO Armor listener  dura/maxdura * 100 = percent dura left

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerSwordSwing(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();

            if (!Permissions.hasPerms(player)) {
                return;
            }

            ItemStack item = player.getItemInHand();
            int usesLeft = Tools.getUsesLeft(item);

            if (Tools.isSword(item)) {
                Notify.getProperToolMessage(player, item, usesLeft);
            } else {
                Notify.getImproperToolMessage(player, item, usesLeft);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getDamage() > 0) {
            Player player = (Player) event.getEntity();
            checkArmor(player);
        }
    }

    private void checkArmor(Player player) {
        if (player.getInventory().getHelmet() != null) {
            checkDurability(player, player.getInventory().getHelmet());
        }
        if (player.getInventory().getChestplate() != null) {
            checkDurability(player, player.getInventory().getChestplate());
        }
        if (player.getInventory().getLeggings() != null) {
            checkDurability(player, player.getInventory().getLeggings());
        }
        if (player.getInventory().getBoots() != null) {
            checkDurability(player, player.getInventory().getBoots());
        }
    }

    private void checkDurability(Player player, ItemStack is) {
        double percentLeft = (100.00 - Armor.getPercentDurabilityLeft(is));
        if (percentLeft >= 90.0 && percentLeft <= 91.0 || percentLeft >= 95.0 && percentLeft <=96.0) {
            Notify.sendArmorWarning(player, is, percentLeft, Armor.getArmorColor(is));
        }
    }
}
