package org.hopto.seed419.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockBreakListener implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (!Permissions.hasPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getItemInHand();
        int usesLeft = Tools.getUsesLeft(item);
        System.out.println("Block Break: " + usesLeft);

        if (Tools.isPickaxe(item)) {
            Notify.getProperToolMessage(player, item, usesLeft);
        } else {
            Notify.getImproperToolMessage(player, item, usesLeft);
        }
    }
}
