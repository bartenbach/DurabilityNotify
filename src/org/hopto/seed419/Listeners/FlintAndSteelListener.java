package org.hopto.seed419.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.LiveNotify;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

public class FlintAndSteelListener implements Listener {

    @EventHandler
    void onPlayerUseFlintAndSteel(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem() && event.getItem().getType() == Material.FLINT_AND_STEEL) {

            Player player = event.getPlayer();

            if (!Permissions.hasToolPerms(player)) {
                return;
            }

            ItemStack item = event.getPlayer().getItemInHand();

            int usesLeft = Tools.getUsesLeft(item);

            if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                Notify.checkProperToolForLowDurability(player, item, usesLeft);
            }
        }
    }
}
