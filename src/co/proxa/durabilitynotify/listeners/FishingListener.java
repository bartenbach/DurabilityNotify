package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishingListener implements Listener {

    private ListManager lm;

    public FishingListener(ListManager lm) {
        this.lm = lm;
    }


    @EventHandler
    void onPlayerFish(PlayerFishEvent event) {

        Player player = event.getPlayer();

        if (!Permissions.hasToolPerms(player)) {
            return;
        }

        int usesLeft = Tool.getUsesLeft(event.getPlayer().getInventory().getItemInMainHand());
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        switch (event.getState()) {
            case CAUGHT_FISH:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    if (lm.getFishingRod().contains(usesLeft)) {
                        Notify.createToolWarning(player, item, usesLeft, false);
                    }
                }
                break;
            case IN_GROUND:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    //TODO TEST
                    if (lm.getFishingRod().contains(usesLeft) || lm.getFishingRod().contains(usesLeft+1)) {
                        Notify.createToolWarning(player, item ,usesLeft, true);
                    }
                }
                break;
            case CAUGHT_ENTITY:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    //TODO fix
                    Notify.checkReallyImproperToolForLowDurability(player, item, usesLeft);
                }
                break;
            default:
                break;
        }
    }
}
