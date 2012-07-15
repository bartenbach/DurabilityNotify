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
            if (usesLeft == 10) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.GOLD);
            } else if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GOLD);
            }
        } else if (Tools.isIronTool(item)) {
            if (usesLeft == 10) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.GRAY);
            } else if (usesLeft == 1) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.GRAY);
            }
        } else if (Tools.isDiamondTool(item)) {
            if (usesLeft == 500 || usesLeft == 200 || usesLeft == 50) {
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
            if (usesLeft == 500 || usesLeft == 501 || usesLeft == 200 || usesLeft == 201 || usesLeft == 50 || usesLeft == 51) {
                Notify.sendMessage(player, item, usesLeft, false, ChatColor.AQUA);
            } else if (usesLeft == 1 || usesLeft == 2) {
                Notify.sendMessage(player, item, usesLeft, true, ChatColor.AQUA);
            }
        }
    }

    public static void sendArmorWarning(Player player, ItemStack item, double percentLeft, ChatColor color) {
        if (percentLeft > 0) {
            String message = warning + ChatColor.YELLOW + " Your " +
                    color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                    getGrammar(item) + ChatColor.GRAY +  df.format(percentLeft) + "% " + ChatColor.YELLOW + " durability";
            checkAndSendMessage(player, message);
        } else {
            String message = warning + ChatColor.YELLOW + " Your " +
                    color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                    getGrammar2(item);
            checkAndSendMessage(player, message);
        }
    }

    public static void sendMessage(Player player, ItemStack item, int usesLeft, boolean lastuse, ChatColor color) {
        String message = warning + ChatColor.YELLOW + " Your " +
                color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW +
                " has only " + ChatColor.GRAY +  usesLeft + ChatColor.YELLOW + (lastuse ? " use" : " uses") + " left";
        checkAndSendMessage(player, message);
    }

    public static void sendLiveNotification(Player player, ItemStack item, int usesLeft, ChatColor color) {
        String message = info + ChatColor.GREEN + " Your " +
                color + item.getType().name().toLowerCase().replace("_", " ") + ChatColor.GREEN +
                " has " + ChatColor.WHITE +  usesLeft + ChatColor.GREEN + " uses left";
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
