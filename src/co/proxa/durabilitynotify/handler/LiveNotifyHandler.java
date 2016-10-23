package co.proxa.durabilitynotify.handler;

import co.proxa.durabilitynotify.file.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class LiveNotifyHandler {

    private static HashMap<String,Boolean> liveNotifications = new HashMap<String, Boolean>();

    private static boolean notifyOn(Player player) {
        return liveNotifications.get(player.getName());
    }

    public static void putPlayerOnMap(Player player) {
        liveNotifications.put(player.getName(), true);
    }

    public static boolean onMap(Player player) {
        return liveNotifications.containsKey(player.getName());
    }

    public void toggleNotify(Player player) {
        liveNotifications.put(player.getName(), !liveNotifications.get(player.getName()));
    }

    public void sendMessage(Player player) {
        if (liveNotifications.get(player.getName())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.liveNotifyOnMsg));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.liveNotifyOffMsg));
        }
    }

    public static boolean checkLiveNotify(Player player, ItemStack item, int usesLeft) {
        if (onMap(player) && notifyOn(player)) {
            NotifyHandler.createLiveNotification(player, item, usesLeft);
            return true;
        }
        return false;
    }

    public void clearMap() {
        liveNotifications = null;
    }

}
