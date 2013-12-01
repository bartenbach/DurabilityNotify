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

        int usesLeft = Tools.getUsesLeft(event.getPlayer().getItemInHand());
        ItemStack item = event.getPlayer().getItemInHand();

        switch (event.getState()) {
            case CAUGHT_FISH:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    if (lm.getFishingRod().contains(usesLeft)) {
                        Notify.sendNotification(player,item,usesLeft);
                    }
                }
                break;
            case IN_GROUND:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    Notify.checkImproperToolForLowDurability(player, item, usesLeft);
                }
                break;
            case CAUGHT_ENTITY:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    Notify.checkReallyImproperToolForLowDurability(player, item, usesLeft);
                }
                break;
            default:
                break;
        }
    }
}
