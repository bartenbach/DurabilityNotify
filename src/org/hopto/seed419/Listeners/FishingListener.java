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
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if (!Permissions.hasToolPerms(player)) {
            return;
        }
        switch (event.getState()) {
            case CAUGHT_ENTITY:
            case CAUGHT_FISH:
            case IN_GROUND:
                int usesLeft = Tools.getUsesLeft(event.getPlayer().getItemInHand());
                ItemStack item = event.getPlayer().getItemInHand();
                if (LiveNotify.onMap(player) && LiveNotify.nofityOn(player)) {
                    Notify.sendLiveNotification(player, item, usesLeft, Tools.getToolColor(item));
                } else if (usesLeft == 10 || usesLeft == 1) {
                    Notify.sendMessage(player, event.getPlayer().getItemInHand(), usesLeft, Tools.getToolColor(item));
                }
            default:
                break;
        }
    }
}
