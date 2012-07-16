package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 7/13/12
 * Time: 8:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class LiveNotify {


    private static HashMap<Player,Boolean> liveNotifications = new HashMap<Player, Boolean>();


    public static boolean nofityOn(Player player) {
        return liveNotifications.get(player);
    }

    public static void putPlayerOnMap(Player player) {
        liveNotifications.put(player, true);
    }

    public static boolean onMap(Player player) {
        return liveNotifications.containsKey(player);
    }

    public void toggleNotify(Player player) {
        liveNotifications.put(player, !liveNotifications.get(player));
    }

    public void sendMessage(Player player) {
        if (liveNotifications.get(player)) {
            player.sendMessage(ChatColor.GREEN + "[Live durability notifications on]");
        } else {
            player.sendMessage(ChatColor.DARK_RED + "[Live durability notifications off]");
        }
    }
}
