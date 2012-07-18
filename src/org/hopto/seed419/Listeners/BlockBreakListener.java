package org.hopto.seed419.Listeners;

import org.bukkit.block.Block;
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
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (!Permissions.hasToolPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getItemInHand();

        if (!Tools.isPickaxe(item) && !Tools.isAxe(item) && !Tools.isShovel(item) && !Tools.isSword(item)) {
            return;
        }

        int usesLeft = Tools.getUsesLeft(item);

        // Live notifications
        if (LiveNotify.onMap(player) && LiveNotify.nofityOn(player)) {
            Notify.sendLiveNotification(player, item, usesLeft, Tools.getToolColor(item));
        } else {
        // It seems like every block is the proper tool for anything -.-
            Notify.getProperToolMessage(player, item, usesLeft);
        }
    }

    //This is really confusing.  Durability doesn't seem to matter based on blocks.
/*    private boolean isPickaxeBlock(Block block) {
        switch (block.getType()) {
            case STONE:
            case COBBLESTONE:
            case COBBLESTONE_STAIRS:
            case COAL_ORE:
            case IRON_ORE:
            case DIAMOND_ORE:
            case GOLD_ORE:
            case LAPIS_ORE:
            case REDSTONE_ORE:
                return true;
            default:
                return false;
        }
    }*/

    private boolean isShovelBlock(Block block) {
        switch (block.getType()) {
            case SNOW:
            case DIRT:
            case GRAVEL:
            case SNOW_BLOCK:
            case SAND:
                return true;
            default:
                return false;
        }

    }

}
