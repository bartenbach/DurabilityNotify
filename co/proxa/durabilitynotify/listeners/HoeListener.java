package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HoeListener implements Listener {

    private ListManager lm;

    public HoeListener(ListManager lm) {
        this.lm = lm;
    }

    // This event could fire a lot when we don't want it.  For the sake of performance, we're checking firstly to see
    // if the player is holding a hoe.  If not, then we don't care about it.  We're not even assigning any variables
    // until we are sure that the player is holding a hoe and actually right clicked grass or dirt.

    @EventHandler
    void onPlayerHoeSoil(PlayerInteractEvent event) {
        if (Tool.isHoe(event.getPlayer().getItemInHand())) {

            //TODO: refactor this without so many switches.  looks awful.

            switch(event.getAction()) {
                case RIGHT_CLICK_BLOCK:

                    switch (event.getClickedBlock().getType()) {
                        case DIRT:
                        case GRASS:

                            ItemStack item = event.getPlayer().getItemInHand();
                            Player player = event.getPlayer();

                            int usesLeft = Tool.getUsesLeft(item);

                            if (!Permissions.hasToolPerms(player)) {
                                return;
                            }

                            if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                                boolean isNotifyTime = false;

                                switch (item.getType()) {
                                    case WOOD_HOE:
                                        isNotifyTime = lm.getWoodenHoe().contains(usesLeft);
                                        break;
                                    case STONE_HOE:
                                        isNotifyTime = lm.getStoneHoe().contains(usesLeft);
                                        break;
                                    case IRON_HOE:
                                        isNotifyTime = lm.getIronHoe().contains(usesLeft);
                                        break;
                                    case GOLD_HOE:
                                        isNotifyTime = lm.getGoldHoe().contains(usesLeft);
                                        break;
                                    case DIAMOND_HOE:
                                        isNotifyTime = lm.getDiamondHoe().contains(usesLeft);
                                        break;
                                    default:
                                        break;
                                }
                                if (isNotifyTime) {
                                    Notify.createToolWarning(player, item, usesLeft, false);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
    }
}
