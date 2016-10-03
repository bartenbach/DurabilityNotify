package co.proxa.durabilitynotify;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class LiveNotify {

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
            player.sendMessage(ChatColor.GREEN + "[Live durability notifications on]");
        } else {
            player.sendMessage(ChatColor.DARK_RED + "[Live durability notifications off]");
        }
    }

    public static boolean checkLiveNotify(Player player, ItemStack item, int usesLeft) {
        if (onMap(player) && notifyOn(player)) {
            Notify.createLiveNotification(player, item, usesLeft);
            return true;
        }
        return false;
    }

    public void clearMap() {
        liveNotifications = null;
    }

}
