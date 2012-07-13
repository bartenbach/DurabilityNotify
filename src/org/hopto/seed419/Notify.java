package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Notify {

    private final static String warning = ChatColor.DARK_RED + "[" + ChatColor.YELLOW + "Warning" + ChatColor.DARK_RED + "]";
    private final static DecimalFormat df = new DecimalFormat("#.#");

    public static void getProperToolMessage(Player player, ItemStack item, int usesLeft) {
        if (Tools.isWoodTool(item)) {
            if (usesLeft == 10) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.WHITE);
            } else if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.WHITE);
            }
        } else if (Tools.isStoneTool(item)) {
            if (usesLeft == 10) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.GRAY);
            } else if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GRAY);
            }
        } else if (Tools.isGoldTool(item)) {
            if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GOLD);
            }
        } else if (Tools.isIronTool(item)) {
            if (usesLeft == 10) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.GRAY);
            } else if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GRAY);
            }
        } else if (Tools.isDiamondTool(item)) {
            if (usesLeft == 500) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 200) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 50) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.AQUA);
            }
        }
    }

    public static void getImproperToolMessage(Player player, ItemStack item, int usesLeft) {
        if (Tools.isWoodTool(item)) {
            if (usesLeft == 10 || usesLeft == 11) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.WHITE);
            } else if (usesLeft == 1 || usesLeft == 2) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.WHITE);
            }
        } else if (Tools.isStoneTool(item)) {
            if (usesLeft == 10 || usesLeft == 11) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.GRAY);
            } else if (usesLeft == 1 || usesLeft == 2) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GRAY);
            }
        } else if (Tools.isGoldTool(item)) {
            if (usesLeft == 1 || usesLeft == 2) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GOLD);
            }
        } else if (Tools.isIronTool(item)) {
            if (usesLeft == 10 || usesLeft == 11) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.GRAY);
            } else if (usesLeft == 1 || usesLeft == 2) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GRAY);
            }
        } else if (Tools.isDiamondTool(item)) {
            if (usesLeft == 500 || usesLeft == 501) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 200 || usesLeft == 201) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 50 || usesLeft == 51) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 1 || usesLeft == 2) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.AQUA);
            }
        }
    }

    public static void sendArmorWarning(Player player, ItemStack item, double percentLeft, ChatColor color) {
        if (percentLeft > 0) {
            player.sendMessage(warning + ChatColor.YELLOW + " Your " +
                    color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                    " is at " + ChatColor.GRAY +  df.format(percentLeft) + "% " + ChatColor.YELLOW + " durability");
        } else {
            player.sendMessage(warning + ChatColor.YELLOW + " Your " +
                    color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                    " has broke!");
        }
    }

    public static void sendMessage(Player player, ItemStack item, int usesLeft, boolean lastuse, ChatColor color) {
        player.sendMessage(warning + ChatColor.YELLOW + " Your " +
                color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                " has only " + ChatColor.GRAY +  usesLeft + ChatColor.YELLOW + (lastuse ? " use" : " uses") + " left");
    }
}
