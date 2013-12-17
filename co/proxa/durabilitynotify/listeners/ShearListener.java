package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ShearListener implements Listener {

    private ListManager lm;

    public ShearListener(ListManager lm) {
        this.lm = lm;
    }

    @EventHandler
    void onPlayerUseShears(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK && event.hasItem() && event.getItem().getType() == Material.SHEARS) {

            Player player = event.getPlayer();

            if (!Permissions.hasToolPerms(player)) {
                return;
            }  //TODO This whole class was so not tested

            switch (event.getClickedBlock().getType()) {
                case LEAVES:
                case LONG_GRASS:
                case WEB:
                case VINE:
                case DEAD_BUSH:
                case WOOL:

                    ItemStack item = event.getPlayer().getItemInHand();

                    int usesLeft = Tool.getUsesLeft(item);

                    if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                        if (lm.getShears().contains(usesLeft)) {
                            Notify.createToolWarning(player, item, usesLeft);
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

        if (!Permissions.hasToolPerms(player)) {
            return;
        }

        ItemStack item = event.getPlayer().getItemInHand();
        int usesLeft = Tool.getUsesLeft(item);

        if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
            if (lm.getShears().contains(usesLeft)) {
                Notify.createToolWarning(player, item, usesLeft);
            }
        }
    }
}
