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

        if (!Tool.isPickaxe(item) && !Tool.isAxe(item) && !Tool.isShovel(item) && !Tool.isSword(item)) {
            return;
        } //TODO is this check needed?

        int usesLeft = Tool.getUsesLeft(item);

        if (!LiveNotify.checkLiveNotify(player, item, usesLeft) && usesLeft > 0) {

            List<Integer> notifyList = Tool.getToolList(item);

            assert notifyList != null;

            if (Tool.isSword(item)) {
                if (notifyList.contains(usesLeft) || notifyList.contains(usesLeft+1)) {
                    Notify.createToolWarning(player, item, usesLeft);
                    Notify.sendImproperToolWarning(player);
                }
            } else {
                if (notifyList.contains(usesLeft)) {  //TODO test this
                    Notify.createToolWarning(player, item, usesLeft);
                }
            }
        }
    }
}
