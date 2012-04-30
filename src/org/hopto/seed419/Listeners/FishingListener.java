package org.hopto.seed419.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
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

        if (!Permissions.hasPerms(player)) {
            return;
        }

        switch (event.getState()) {
            case CAUGHT_ENTITY:
            case CAUGHT_FISH:
            case IN_GROUND:

                ItemStack item = event.getPlayer().getItemInHand();
                int usesLeft = Tools.getUsesLeft(item);

                if (usesLeft == 10) {
                    Notify.sendMessage(player, item, usesLeft, false, ChatColor.WHITE);
                } else if (usesLeft == 1) {
                    Notify.sendMessage(player, item, usesLeft, true, ChatColor.WHITE);
                }

        }

    }
}
