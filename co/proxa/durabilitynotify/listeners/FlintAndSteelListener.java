package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FlintAndSteelListener implements Listener {

    private ListManager lm;

    public FlintAndSteelListener(ListManager lm) {
        this.lm = lm;
    }

    @EventHandler
    void onPlayerUseFlintAndSteel(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem() && event.getItem().getType() == Material.FLINT_AND_STEEL) {

            Player player = event.getPlayer();

            if (!Permissions.hasToolPerms(player)) {
                return;
            }

            ItemStack item = event.getPlayer().getItemInHand();
            int usesLeft = Tool.getUsesLeft(item);

            if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                if (lm.getFlintAndSteel().contains(usesLeft)) {
                    Notify.createToolWarning(player, item, usesLeft, false);
                }
            }
        }
    }
}
