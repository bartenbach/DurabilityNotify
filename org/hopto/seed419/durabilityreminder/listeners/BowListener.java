package org.hopto.seed419.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.hopto.seed419.LiveNotify;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

public class BowListener implements Listener {

    @EventHandler
    void onBowFire(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (!Permissions.hasToolPerms(player)) {
                return;
            }

            int usesLeft = Tools.getUsesLeft(event.getBow());

            if (!LiveNotify.checkLiveNotify(player, event.getBow(), usesLeft)) {
                Notify.checkProperToolForLowDurability(player, event.getBow(), usesLeft);
            }
        }
    }
}
