package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlockBreakListener implements Listener {

    private ListManager lm;

    public BlockBreakListener(ListManager lm) {
        this.lm = lm;
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!Permissions.hasToolPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getItemInHand();

        if (!Tools.isPickaxe(item) && !Tools.isAxe(item) && !Tools.isShovel(item) && !Tools.isSword(item)) {
            return;
        }

        int usesLeft = Tools.getUsesLeft(item);

        if (!LiveNotify.checkLiveNotify(player, item, usesLeft) && usesLeft > 0) {

            List<Integer> notifyList = null;

            if (Tools.isSword(item)) {
                // TODO: fix improper tool notifications
                Notify.checkImproperToolForLowDurability(player, item, usesLeft);
            } else {
                if (Tools.isPickaxe(item)) {
                    switch (item.getType()) {
                    case WOOD_PICKAXE:
                        notifyList = lm.getWoodenPickaxe();
                        break;
                    case STONE_PICKAXE:
                        notifyList = lm.getStonePickaxe();
                        break;
                    case IRON_PICKAXE:
                        notifyList = lm.getIronPickaxe();
                        break;
                    case GOLD_PICKAXE:
                        notifyList = lm.getGoldPickaxe();
                        break;
                    case DIAMOND_PICKAXE:
                        notifyList = lm.getDiamondPickaxe();
                        break;
                    default:
                        break;
                    }
                } else if (Tools.isAxe(item)) {
                    switch (item.getType()) {
                    case WOOD_AXE:
                        notifyList = lm.getWoodenAxe();
                        break;
                    case STONE_AXE:
                        notifyList = lm.getStoneAxe();
                        break;
                    case IRON_AXE:
                        notifyList = lm.getIronAxe();
                        break;
                    case GOLD_AXE:
                        notifyList = lm.getGoldAxe();
                        break;
                    case DIAMOND_AXE:
                        notifyList = lm.getDiamondAxe();
                        break;
                    default:
                        break;
                    }
                } else if (Tools.isShovel(item)) {
                    switch (item.getType()) {
                    case WOOD_SPADE:
                        notifyList = lm.getWoodenShovel();
                        break;
                    case STONE_SPADE:
                        notifyList = lm.getStoneShovel();
                        break;
                    case IRON_SPADE:
                        notifyList = lm.getIronShovel();
                        break;
                    case GOLD_SPADE:
                        notifyList = lm.getGoldShovel();
                        break;
                    case DIAMOND_SPADE:
                        notifyList = lm.getDiamondShovel();
                        break;
                    default:
                        break;
                    }
                }
                assert notifyList != null;
                if (notifyList.contains(usesLeft)) {
                    Notify.sendNotification(player,item,usesLeft);
                }
            }
        }
    }
}
