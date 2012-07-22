package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class Notify {


    private final static String warning = ChatColor.DARK_RED + "[" + ChatColor.YELLOW + "Warning" + ChatColor.DARK_RED + "]";
    private final static String info = ChatColor.WHITE + "[" + ChatColor.GREEN + "Info" + ChatColor.WHITE + "]";
    private final static DecimalFormat df = new DecimalFormat("#.#");
    private static String lastmessage = "";
    private static Player lastPlayer = null;


    public static void getProperToolMessage(Player player, ItemStack item, int usesLeft) {
        if (Tools.isWoodTool(item) || Tools.isStoneTool(item) || Tools.isGoldTool(item) || Tools.isIronTool(item) ||
                Tools.isStringTool(item)) {
            if (usesLeft == 10 || usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft);
            }
        } else if (Tools.isDiamondTool(item)) {
            if (usesLeft == 500 || usesLeft == 200 || usesLeft == 50 || usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft);
            }
        }
    }

    public static void getImproperToolMessage(Player player, ItemStack item, int usesLeft) {
        if (Tools.isWoodTool(item) || Tools.isStoneTool(item) || Tools.isGoldTool(item) || Tools.isIronTool(item)) {
            if (usesLeft == 10 || usesLeft == 11 || usesLeft == 2 || usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft);
            }
        } else if (Tools.isDiamondTool(item)) {
            if (usesLeft == 500 || usesLeft == 200 || usesLeft == 50 || usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft);
            }
        }
    }

    public static void sendArmorWarning(Player player, ItemStack item, int percentLeft) {
        ChatColor color = Armor.getArmorColor(item);
        String message;
        if (percentLeft > 0) {
            message = warning + ChatColor.YELLOW + " Your " + color + item.getType().name().toLowerCase().replace("_", " ")
                    + ChatColor.YELLOW + getGrammar(item) + ChatColor.GRAY +  df.format(percentLeft) + "%"
                    + ChatColor.YELLOW + " durability remaining";
        } else {
            message = warning + ChatColor.YELLOW + " Your " +
                    color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                    getGrammar(item) + "broken";
        }
        checkAndSendMessage(player, message);
    }

    public static void sendMessage(Player player, ItemStack item, int usesLeft) {
        ChatColor color = Tools.getToolColor(item);
        String message = warning + ChatColor.YELLOW + " Your " + color +
                item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW + getGrammar(item) + ChatColor.GRAY
                +  usesLeft + ChatColor.YELLOW + (usesLeft == 1 ? " use" : " uses") + " left";
        checkAndSendMessage(player, message);
    }

    public static void sendLiveNotification(Player player, ItemStack item, int usesLeft) {
        ChatColor color = Tools.getToolColor(item);
        String message;
        if (usesLeft > 0) {
            message = info + ChatColor.GREEN + " Your " + color + item.getType().name().toLowerCase().replace("_", " ")
                    + ChatColor.GREEN + getGrammar(item) + ChatColor.WHITE +  usesLeft + ChatColor.GREEN + " uses left";
        } else {
            message = info + ChatColor.GREEN + " Your " + color + item.getType().name().toLowerCase().replace("_", " ")
                    + ChatColor.GREEN + getGrammar(item) + "broken";
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
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
            case SHEARS:
                return " have ";
            default:
                return " has ";
        }
    }

}
