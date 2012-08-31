package org.hopto.seed419.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.*;

public class CombatListener implements Listener {


    private DurabilityNotify dn;


    public CombatListener (DurabilityNotify dn) {
        this.dn = dn;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    void onPlayerInflictDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (!Permissions.hasToolPerms(player) || player.getItemInHand().getType() == Material.AIR) {
                return;
            }

            ItemStack item = player.getItemInHand();
            int usesLeft = Tools.getUsesLeft(item);

            if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                if (Tools.isSword(item)) {
                    Notify.checkProperToolForLowDurability(player, item, usesLeft);
                } else if (Tools.isAxe(item)) {
                    Notify.checkImproperToolForLowDurability(player, item, usesLeft);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    void onPlayerTakeDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getDamage() > 0) {

            Player player = (Player) event.getEntity();

            if (!Permissions.hasArmorPerms(player)) {
                return;
            }

            switch (event.getCause()) {
                case DROWNING:
                case POISON:
                case STARVATION:
                case VOID:
                case FALL:
                case FIRE_TICK:
                case SUFFOCATION:
                case MAGIC:
                    return;
            }

            //TODO Live armor notifications?
            Armor.checkArmor(player);
        }
    }


}
