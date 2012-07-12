package org.hopto.seed419.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
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

    @EventHandler
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
}
