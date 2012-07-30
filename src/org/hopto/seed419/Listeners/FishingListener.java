package org.hopto.seed419.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.LiveNotify;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

public class FishingListener implements Listener {


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
                    Notify.getProperToolMessage(player, item, usesLeft);
                }
                break;
            case IN_GROUND:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    Notify.getImproperToolMessage(player, item, usesLeft);
                }
                break;
            case CAUGHT_ENTITY:
                if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                    Notify.getReallyImproperToolMessage(player, item ,usesLeft);
                }
                break;
            default:
                break;
        }
    }
}
