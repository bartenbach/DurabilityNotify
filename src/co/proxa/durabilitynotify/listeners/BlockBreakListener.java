package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.handler.LiveNotifyHandler;
import co.proxa.durabilitynotify.handler.NotifyHandler;
import co.proxa.durabilitynotify.handler.PermissionsHandler;
import co.proxa.durabilitynotify.handler.ToolHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlockBreakListener implements Listener {

    @EventHandler
    void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!PermissionsHandler.hasToolPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        int usesLeft = ToolHandler.getUsesLeft(item);

        if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft) && usesLeft > 0) {
            List<Integer> notifyList = ToolHandler.getToolList(item);

            if (notifyList != null) {
                if (ToolHandler.isSword(item)) {
                    if (notifyList.contains(usesLeft) || notifyList.contains(usesLeft+1)) {
                        NotifyHandler.createToolWarning(player, item, usesLeft, true);
                    }
                } else {
                    if (notifyList.contains(usesLeft)) {
                        NotifyHandler.createToolWarning(player, item, usesLeft, false);
                    }
                }
            }
        }
    }
}
