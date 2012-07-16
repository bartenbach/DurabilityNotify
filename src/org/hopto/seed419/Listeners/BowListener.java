package org.hopto.seed419.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.hopto.seed419.Notify;
import org.hopto.seed419.Permissions;
import org.hopto.seed419.Tools;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BowListener implements Listener {

    @EventHandler
    public void onBowFire(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!Permissions.hasToolPerms(player)) {
                return;
            }
            int usesLeft = Tools.getUsesLeft(event.getBow());
            if (usesLeft == 10 || usesLeft == 1) {
                Notify.sendMessage(player, event.getBow(), usesLeft, ChatColor.WHITE);
            }
        }
    }
}
