package org.hopto.seed419.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.LiveNotify;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

public class BlockBreakListener implements Listener {

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (!Permissions.hasToolPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getItemInHand();

        if (!Tools.isPickaxe(item) && !Tools.isAxe(item) && !Tools.isShovel(item) && !Tools.isSword(item) ||
                item.getType() == Material.SHEARS) {
            return;
        }

        int usesLeft = Tools.getUsesLeft(item);

        if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
            Notify.getProperToolMessage(player, item, usesLeft);
        }
    }
}
