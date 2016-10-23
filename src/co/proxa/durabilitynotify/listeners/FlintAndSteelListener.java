package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.file.ConfigHandler;
import co.proxa.durabilitynotify.handler.LiveNotifyHandler;
import co.proxa.durabilitynotify.handler.NotifyHandler;
import co.proxa.durabilitynotify.handler.PermissionsHandler;
import co.proxa.durabilitynotify.handler.ToolHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FlintAndSteelListener implements Listener {

    private ConfigHandler lm;

    public FlintAndSteelListener(ConfigHandler lm) {
        this.lm = lm;
    }

    @EventHandler
    void onPlayerUseFlintAndSteel(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem() && event.getItem().getType() == Material.FLINT_AND_STEEL) {

            Player player = event.getPlayer();

            if (!PermissionsHandler.hasToolPerms(player)) {
                return;
            }

            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            int usesLeft = ToolHandler.getUsesLeft(item);

            if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {
                if (lm.getFlintAndSteel().contains(usesLeft)) {
                    NotifyHandler.createToolWarning(player, item, usesLeft, false);
                }
            }
        }
    }
}
