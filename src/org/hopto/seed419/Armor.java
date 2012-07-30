package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 7/13/12
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Armor {

    public static double getPercentDurabilityLeft(ItemStack is) {
        return (((double)is.getDurability() / (double)is.getType().getMaxDurability()) * 100.00);
    }

    public static ChatColor getArmorColor(ItemStack is) {
        switch (is.getType()) {
            case DIAMOND_CHESTPLATE:
            case DIAMOND_BOOTS:
            case DIAMOND_HELMET:
            case DIAMOND_LEGGINGS:
                return ChatColor.AQUA;
            case IRON_CHESTPLATE:
            case IRON_HELMET:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            case CHAINMAIL_BOOTS:
            case CHAINMAIL_HELMET:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_CHESTPLATE:
                return ChatColor.GRAY;
            case GOLD_BOOTS:
            case GOLD_HELMET:
            case GOLD_LEGGINGS:
            case GOLD_CHESTPLATE:
                return ChatColor.GOLD;
            case LEATHER_BOOTS:
            case LEATHER_CHESTPLATE:
            case LEATHER_HELMET:
            case LEATHER_LEGGINGS:
                return ChatColor.RED;
            default:
                return ChatColor.WHITE;
        }
    }


}
