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
        int usesLeft = Tool.getUsesLeft(item);

        if (!LiveNotify.checkLiveNotify(player, item, usesLeft) && usesLeft > 0) {
            List<Integer> notifyList = Tool.getToolList(item);

            if (notifyList != null) {
                if (Tool.isSword(item)) {
                    if (notifyList.contains(usesLeft) || notifyList.contains(usesLeft+1)) {
                        Notify.createToolWarning(player, item, usesLeft, true);
                    }
                } else {
                    if (notifyList.contains(usesLeft)) {
                        Notify.createToolWarning(player, item, usesLeft, false);
                    }
                }
            }
        }
    }
}
