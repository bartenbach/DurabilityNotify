package org.hopto.seed419.Listeners;

import org.bukkit.Material;
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

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (!Permissions.hasToolPerms(player) || event.getPlayer().getItemInHand().getType() == Material.AIR) {
            return;
        }
        System.out.println("You have permission.");

        ItemStack item = event.getPlayer().getItemInHand();
        int usesLeft = Tools.getUsesLeft(item);

        // Live notifications
        if (LiveNotify.onMap(player)) {
            if (LiveNotify.nofityOn(player)) {
                Notify.sendLiveNotification(player, item, usesLeft, Tools.getToolColor(item));
            }
        }

        // It seems like every block is the proper tool for anything -.-
        Notify.getProperToolMessage(player, item, usesLeft);
    }

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
