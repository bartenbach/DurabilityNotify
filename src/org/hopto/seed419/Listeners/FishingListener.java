package org.hopto.seed419.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
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
                if (usesLeft == 10 || usesLeft == 1) {
                    Notify.sendMessage(player, event.getPlayer().getItemInHand(), usesLeft, ChatColor.WHITE);
                }
            default:
                break;
        }
    }
}
