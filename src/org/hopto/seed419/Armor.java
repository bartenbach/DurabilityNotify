package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hopto.seed419.File.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 7/13/12
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Armor {


    private static DurabilityNotify dn;


    public Armor(DurabilityNotify dn) {
        this.dn = dn;
    }

    public static double getPercentDurabilityLeft(ItemStack is) {
        return (((double)is.getDurability() / (double)is.getType().getMaxDurability()) * 100.00);
    }

    public static void checkArmor(Player player) {
        if (player.getInventory().getHelmet() != null) {
            checkDurability(player, player.getInventory().getHelmet());
        }
        if (player.getInventory().getChestplate() != null) {
            checkDurability(player, player.getInventory().getChestplate());
        }
        if (player.getInventory().getLeggings() != null) {
            checkDurability(player, player.getInventory().getLeggings());
        }
        if (player.getInventory().getBoots() != null) {
            checkDurability(player, player.getInventory().getBoots());
        }
    }

    private static void checkDurability(Player player, ItemStack is) {
        double percentLeft = (100.00 - Armor.getPercentDurabilityLeft(is));
        if (percentLeft >= 9.0 && percentLeft <= 11.0 && dn.getConfig().getBoolean(Paths.notifyAt10)) {
            Notify.createArmorWarning(player, is, 10);
        } else if (percentLeft >= 4.0 && percentLeft <= 6.0 && dn.getConfig().getBoolean(Paths.notifyAt5)) {
            Notify.createArmorWarning(player, is, 5);
        } else if (percentLeft == 0 && dn.getConfig().getBoolean(Paths.notifyOnBreak)) {
            Notify.createArmorWarning(player, is, 0);
        }
    }

    public static void checkArmorForReminder(Player player) {
        if (player.getInventory().getHelmet() != null) {
            remindDurability(player, player.getInventory().getHelmet());
        }
        if (player.getInventory().getChestplate() != null) {
            remindDurability(player, player.getInventory().getChestplate());
        }
        if (player.getInventory().getLeggings() != null) {
            remindDurability(player, player.getInventory().getLeggings());
        }
        if (player.getInventory().getBoots() != null) {
            remindDurability(player, player.getInventory().getBoots());
        }
    }

    private static void remindDurability(Player player, ItemStack is) {
        double percentLeft = (100.00 - Armor.getPercentDurabilityLeft(is));
        if (percentLeft >= 5.0 && percentLeft <= 10.0 && dn.getConfig().getBoolean(Paths.notifyAt10)) {
            Notify.createArmorReminder(player, is, 10);
        } else if (percentLeft >= 0.0 && percentLeft <= 5.0 && dn.getConfig().getBoolean(Paths.notifyAt5)) {
            Notify.createArmorReminder(player, is, 5);
        }
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
