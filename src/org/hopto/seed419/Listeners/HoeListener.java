package org.hopto.seed419.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.LiveNotify;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Tools;

public class HoeListener implements Listener {

    @EventHandler
    void onPlayerHoeSoil(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if (Tools.isHoe(item)) {
            switch(event.getAction()) {
                case RIGHT_CLICK_BLOCK:
                    switch (event.getClickedBlock().getType()) {
                        case DIRT:
                        case GRASS:
                            Player player = event.getPlayer();
                            int usesLeft = Tools.getUsesLeft(item);

                            if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                                Notify.checkProperToolForLowDurability(player, item, usesLeft);
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
