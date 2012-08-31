package org.hopto.seed419.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.LiveNotify;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

public class ShearListener implements Listener {

    @EventHandler
    void onPlayerUseShears(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK && event.hasItem() && event.getItem().getType() == Material.SHEARS) {

            Player player = event.getPlayer();

            if (!Permissions.hasToolPerms(player)) {
                return;
            }

            switch (event.getClickedBlock().getType()) {
                case LEAVES:
                case LONG_GRASS:
                case WEB:
                case VINE:
                case DEAD_BUSH:
                case WOOL:

                    ItemStack item = event.getPlayer().getItemInHand();

                    int usesLeft = Tools.getUsesLeft(item);

                    if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                        Notify.checkProperToolForLowDurability(player, item, usesLeft);
                    }

                default:
                    break;
            }
        }
    }

    @EventHandler
    void onPlayerShearSheep(PlayerShearEntityEvent event) {

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
