package co.proxa.durabilitynotify.handler;

import co.proxa.durabilitynotify.file.ConfigHandler;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class NotifyHandler {

    private final static DecimalFormat df = new DecimalFormat("#.#");
    private static String lastMessage = "";
    private static Player lastPlayer = null;

    //Only known use for this is fishing rods being cast into mobs.  It deals 3 durability damage
    //FIXME bogus as fuck
/*    public static void checkReallyImproperToolForLowDurability(Player player, ItemStack item, int usesLeft) {
        if (ToolHandler.isStringTool(item)) {
            if (usesLeft == 11 || usesLeft == 10 || usesLeft == 9 || usesLeft == 3 || usesLeft == 2 || usesLeft == 1) {
                //createToolWarning(player, item, usesLeft);
            }
        }
    }*/

    public static void createArmorWarning(Player player, ItemStack item, int percentLeft) {
        ChatColor color = ArmorHandler.getArmorColor(item);
        String itemName = formatName(item);
        String grammar = getGrammar(item);
        String percent = df.format(percentLeft);
        String message;
        if (percentLeft > 0) {
            message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.armorWarningMsg.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%PERCENT%", percent + "%")
                            .replace("%GRAMMAR%", grammar));
        } else {
            message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.armorWarningMsg.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%GRAMMAR%", grammar));
        }
        checkAndSendMessage(player, message, false);
    }

    public static void createArmorReminder(Player player, ItemStack item, int percentLeft) {
        if (percentLeft > 0) {
            ChatColor color = ArmorHandler.getArmorColor(item);
            String itemName = formatName(item);
            String grammar = getGrammar(item);
            String percent = df.format(percentLeft);
            String message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.reminderMsg.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%PERCENT%", percent + "%")
                            .replace("%GRAMMAR%", grammar));
            checkAndSendMessage(player, message, false);
        }
    }

    public static void createToolWarning(Player player, ItemStack item, int usesLeft, boolean improper) {
        ChatColor color = ToolHandler.getToolColor(item);
        String itemName = formatName(item);
        String grammar = getGrammar(item);
        String message = "";
        if (usesLeft > 0) {
            message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.toolWarningMsg.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%USES%", String.valueOf(usesLeft))
                            .replace("%GRAMMAR2%", getGrammar2(usesLeft))
                            .replace("%GRAMMAR%", grammar));
        } else {
            //FIXME this never executes because i'm checking for uses > 0 ?
            message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.toolWarningBroken.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%GRAMMAR%", grammar));
        }
        checkAndSendMessage(player, message, improper);
    }

    public static void createLiveNotification(Player player, ItemStack item, int usesLeft) {
        ChatColor color = ToolHandler.getToolColor(item);
        String itemName = formatName(item);
        String grammar = getGrammar(item);
        String message;
        if (usesLeft > 0) {
            message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.infoMsg.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%USES%", String.valueOf(usesLeft))
                            .replace("%GRAMMAR%", grammar));
        } else {
            message = ChatColor.translateAlternateColorCodes('&',
                    ConfigHandler.infoBroken.replaceAll("%COLOR%", color.toString())
                            .replace("%ITEM%", itemName)
                            .replace("%GRAMMAR%", grammar));
        }
        checkAndSendMessage(player, message, false);
    }

    public static void sendArmorCommandMessage(Player player, ItemStack item, double percent) {
        player.sendMessage(ArmorHandler.getArmorColor(item) + WordUtils.capitalize(formatName(item)) + ChatColor.WHITE + " - "
                + getColorByPercent(percent) + df.format(percent) + ChatColor.RESET + "%");
    }

    private static ChatColor getColorByPercent(double i) {
        if (i >= 25) {
            return ChatColor.GREEN;
        } else if (i >= 10) {
            return ChatColor.YELLOW;
        } else {
            return ChatColor.RED;
        }
    }

    private static void checkAndSendMessage(Player player, String message, boolean improper) {
        // this was an attempt at spam protection, but it breaks reminders completely
        if ((!message.equals(lastMessage) || message.equals(lastMessage) && player != lastPlayer)) {
            player.sendMessage(message);
            lastMessage = message;
            lastPlayer = player;
            if (improper) {
                //TODO Technically this should be past tense if the tool has already broken.  Really being a perfectionist here,
                // but it is irritating that the grammar is incorrect.  Might be a whore to fix though.
                player.sendMessage(ConfigHandler.improperToolMsg);
            }
        }
    }

    private static String formatName(ItemStack item) {
        return item.getType().name().toLowerCase().replace("_", " ");
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
                return ConfigHandler.grammarPlural;
            default:
                return ConfigHandler.grammarSingular;
        }
    }

    private static String getGrammar2(int uses) {
        if (uses > 1) {
            return ConfigHandler.grammar2Plural;
        }
        return ConfigHandler.grammar2Singular;
    }
}
