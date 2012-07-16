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
    private final static String info = ChatColor.WHITE + "[" + ChatColor.GREEN + "Info" + ChatColor.WHITE + "]";
    private final static DecimalFormat df = new DecimalFormat("#.#");
    private static String lastmessage = "";
    private static Player lastPlayer = null;


    public static void getProperToolMessage(Player player, ItemStack item, int usesLeft) {
        ChatColor color = Tools.getToolColor(item);
        if (Tools.isWoodTool(item) || Tools.isStoneTool(item) || Tools.isGoldTool(item) || Tools.isIronTool(item)) {
            if (usesLeft == 10 || usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, color);
            }
        } else if (Tools.isDiamondTool(item)) {
            if (usesLeft == 500 || usesLeft == 200 || usesLeft == 50 || usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, color);
            }
        }
    }

    public static void sendArmorWarning(Player player, ItemStack item, double percentLeft, ChatColor color) {
        String message;
        if (percentLeft > 0) {
            message = warning + ChatColor.YELLOW + " Your " + color + item.getType().name().toLowerCase().replace("_", " ")
                    + ChatColor.YELLOW + getGrammar(item) + ChatColor.GRAY +  df.format(percentLeft) + "%"
                    + ChatColor.YELLOW + " durability";
        } else {
            message = warning + ChatColor.YELLOW + " Your " +
                    color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                    getGrammar2(item);
        }
        checkAndSendMessage(player, message);
    }

    public static void sendMessage(Player player, ItemStack item, int usesLeft, ChatColor color) {
        String message = warning + ChatColor.YELLOW + " Your " + color +
                item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW + " has only " + ChatColor.GRAY
                +  usesLeft + ChatColor.YELLOW + (usesLeft == 1 ? " use" : " uses") + " left";
        checkAndSendMessage(player, message);
    }

    public static void sendLiveNotification(Player player, ItemStack item, int usesLeft, ChatColor color) {
        String message;
        if (usesLeft > 0) {
            message = info + ChatColor.GREEN + " Your " + color + item.getType().name().toLowerCase().replace("_", " ")
                    + ChatColor.GREEN + " has " + ChatColor.WHITE +  usesLeft + ChatColor.GREEN + " uses left";
        } else {
            message = info + ChatColor.GREEN + " Your " + color + item.getType().name().toLowerCase().replace("_", " ")
                    + ChatColor.GREEN + " has broken";
        }
        checkAndSendMessage(player, message);
    }

    private static void checkAndSendMessage(Player player, String message) {
        if ((!message.equals(lastmessage) || message.equals(lastmessage) && player != lastPlayer)) {
            player.sendMessage(message);
            lastmessage = message;
            lastPlayer = player;
        }
    }

    private static String getGrammar(ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_CHESTPLATE:
            case CHAINMAIL_HELMET:
            case DIAMOND_HELMET:
            case CHAINMAIL_LEGGINGS:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
                return " are at ";
            default:
                return " is at ";
        }
    }

    private static String getGrammar2(ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_CHESTPLATE:
            case CHAINMAIL_HELMET:
            case DIAMOND_HELMET:
            case CHAINMAIL_LEGGINGS:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
                return " have broken";
            default:
                return " has broken";
        }    }
}
