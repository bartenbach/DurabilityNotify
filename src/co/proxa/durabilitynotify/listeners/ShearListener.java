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
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ShearListener implements Listener {

    private ConfigHandler lm;

    public ShearListener(ConfigHandler lm) {
        this.lm = lm;
    }

    @EventHandler
    void onPlayerUseShears(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK && event.hasItem() && event.getItem().getType() == Material.SHEARS) {

            Player player = event.getPlayer();

            if (!PermissionsHandler.hasToolPerms(player)) {
                return;
            }  //TODO This whole class was so not tested

            switch (event.getClickedBlock().getType()) {
                case LEAVES:
                case LONG_GRASS:
                case WEB:
                case VINE:
                case DEAD_BUSH:
                case WOOL:

                    ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

                    int usesLeft = ToolHandler.getUsesLeft(item);

                    if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {
                        if (lm.getShears().contains(usesLeft)) {
                            NotifyHandler.createToolWarning(player, item, usesLeft, false);
                        }
                    }

                default:
                    break;
            }
        }
    }

    @EventHandler
    void onPlayerShearSheep(PlayerShearEntityEvent event) {
        Player player = event.getPlayer();

        if (!PermissionsHandler.hasToolPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        int usesLeft = ToolHandler.getUsesLeft(item);

        if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {
            if (lm.getShears().contains(usesLeft)) {
                NotifyHandler.createToolWarning(player, item, usesLeft, false);
            }
        }
    }
}
