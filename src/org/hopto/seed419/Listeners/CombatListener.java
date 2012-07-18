package org.hopto.seed419.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.*;
import org.hopto.seed419.File.Paths;

public class CombatListener implements Listener {


    private DurabilityNotify dn;


    public CombatListener (DurabilityNotify dn) {
        this.dn = dn;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInflictDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (!Permissions.hasToolPerms(player) || player.getItemInHand().getType() == Material.AIR) {
                return;
            }

            ItemStack item = player.getItemInHand();
            int usesLeft = Tools.getUsesLeft(item);
            if (LiveNotify.onMap(player) && LiveNotify.nofityOn(player)) {
                Notify.sendLiveNotification(player, item, usesLeft, Tools.getToolColor(item));
            } else if (Tools.isSword(item)) {
                Notify.getProperToolMessage(player, item, usesLeft);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerTakeDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getDamage() > 0) {
            Player player = (Player) event.getEntity();

            if (Permissions.hasArmorPerms(player)) {
                checkArmor(player);
            }
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
        if (percentLeft >= 9.5 && percentLeft <= 11.0 && dn.getConfig().getBoolean(Paths.notifyAt10)) {
            Notify.sendArmorWarning(player, is, 10, Armor.getArmorColor(is));
        } else if (percentLeft >= 4.5 && percentLeft <= 6.0 && dn.getConfig().getBoolean(Paths.notifyAt5)) {
            Notify.sendArmorWarning(player, is, 5, Armor.getArmorColor(is));
        } else if (percentLeft == 0 && dn.getConfig().getBoolean(Paths.notifyOnBreak)) {
            Notify.sendArmorWarning(player, is, 0, Armor.getArmorColor(is));
        }
    }
}
