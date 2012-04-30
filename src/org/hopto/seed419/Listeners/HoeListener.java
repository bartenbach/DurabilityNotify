package org.hopto.seed419.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/29/12
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class HoeListener implements Listener {

    @EventHandler
    public void onPlayerHoeSoil(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if (Tools.isHoe(item)) {
            System.out.println("it's a hoe");
            switch(event.getAction()) {
                case RIGHT_CLICK_BLOCK:
                    switch (event.getClickedBlock().getType()) {
                        case DIRT:
                        case GRASS:
                        System.out.println("dirt or grass");
                        Player player = event.getPlayer();
                        int usesLeft = Tools.getUsesLeft(item);
                        System.out.println(usesLeft);

                        if (usesLeft == 10) {
                            Notify.sendMessage(player, item, usesLeft, false, ChatColor.WHITE);
                        } else if (usesLeft == 1) {
                            Notify.sendMessage(player, item, usesLeft, true, ChatColor.WHITE);
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
