package co.proxa.durabilitynotify.handler;

import co.proxa.durabilitynotify.file.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ArmorHandler {

    private static ConfigHandler lm;

    public ArmorHandler(ConfigHandler lm) {
        this.lm = lm;
    }

    private static double getPercentDurabilityLeft(ItemStack is) {
        return (100 - (((double)is.getDurability() / (double)is.getType().getMaxDurability()) * 100.00));
    }

    private static boolean isApproximatePercentage(double currentDurability, List<Integer> configList) {
        for (double d : configList) {
            if (currentDurability >= (d - 1.0) && currentDurability <= (d + 1.0)) {
                return true;
            }
        }
        return false;
    }

    public void checkArmor(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        if (helmet != null && notifyHelmet(helmet, getPercentDurabilityLeft(helmet))) {
            NotifyHandler.createArmorWarning(player, helmet, (int) getPercentDurabilityLeft(helmet));
        }
        if (chestplate != null && notifyChestplate(chestplate, getPercentDurabilityLeft(chestplate))) {
            NotifyHandler.createArmorWarning(player, chestplate, (int) getPercentDurabilityLeft(chestplate));
        }
        if (leggings != null && notifyLeggings(leggings, getPercentDurabilityLeft(leggings))) {
            NotifyHandler.createArmorWarning(player, leggings, (int) getPercentDurabilityLeft(leggings));
        }
        if (boots != null && notifyBoots(boots, getPercentDurabilityLeft(boots))) {
            NotifyHandler.createArmorWarning(player, boots, (int) getPercentDurabilityLeft(boots));
        }
    }

    private static boolean notifyHelmet(ItemStack helmet, double durability) {
        boolean isNotifyTime = false;
        switch (helmet.getType()) {
            case LEATHER_HELMET:
                isNotifyTime = isApproximatePercentage(durability, lm.getLeatherHelmet());
                break;
            case GOLD_HELMET:
                isNotifyTime = isApproximatePercentage(durability, lm.getGoldHelmet());
                break;
            case CHAINMAIL_HELMET:
                isNotifyTime = isApproximatePercentage(durability, lm.getChainmailHelmet());
                break;
            case IRON_HELMET:
                isNotifyTime = isApproximatePercentage(durability, lm.getIronHelmet());
                break;
            case DIAMOND_HELMET:
                isNotifyTime = isApproximatePercentage(durability, lm.getDiamondHelmet());
                break;
        }
        return isNotifyTime;
    }

    private static boolean notifyChestplate(ItemStack chestplate, double durability) {
        boolean isNotifyTime = false;
        switch (chestplate.getType()) {
            case LEATHER_CHESTPLATE:
                isNotifyTime = isApproximatePercentage(durability, lm.getLeatherChestplate());
                break;
            case GOLD_CHESTPLATE:
                isNotifyTime = isApproximatePercentage(durability, lm.getGoldChestplate());
                break;
            case CHAINMAIL_CHESTPLATE:
                isNotifyTime = isApproximatePercentage(durability, lm.getChainmailChestplate());
                break;
            case IRON_CHESTPLATE:
                isNotifyTime = isApproximatePercentage(durability, lm.getIronChestplate());
                break;
            case DIAMOND_CHESTPLATE:
                isNotifyTime = isApproximatePercentage(durability, lm.getDiamondChestplate());
                break;
        }
        return isNotifyTime;
    }

    private static boolean notifyLeggings(ItemStack leggings, double durability) {
        boolean isNotifyTime = false;
        switch (leggings.getType()) {
            case LEATHER_LEGGINGS:
                isNotifyTime = isApproximatePercentage(durability, lm.getLeatherLeggings());
                break;
            case GOLD_LEGGINGS:
                isNotifyTime = isApproximatePercentage(durability, lm.getGoldLeggings());
                break;
            case CHAINMAIL_LEGGINGS:
                isNotifyTime = isApproximatePercentage(durability, lm.getChainmailLeggings());
                break;
            case IRON_LEGGINGS:
                isNotifyTime = isApproximatePercentage(durability, lm.getIronLeggings());
                break;
            case DIAMOND_LEGGINGS:
                isNotifyTime = isApproximatePercentage(durability, lm.getDiamondLeggings());
                break;
        }
        return isNotifyTime;
    }

    private static boolean notifyBoots(ItemStack boots, double durability) {
        boolean isNotifyTime = false;
        switch (boots.getType()) {
            case LEATHER_BOOTS:
                isNotifyTime = isApproximatePercentage(durability, lm.getLeatherBoots());
                break;
            case GOLD_BOOTS:
                isNotifyTime = isApproximatePercentage(durability, lm.getGoldBoots());
                break;
            case CHAINMAIL_BOOTS:
                isNotifyTime = isApproximatePercentage(durability, lm.getChainmailBoots());
                break;
            case IRON_BOOTS:
                isNotifyTime = isApproximatePercentage(durability, lm.getIronBoots());
                break;
            case DIAMOND_BOOTS:
                isNotifyTime = isApproximatePercentage(durability, lm.getDiamondBoots());
                break;
        }
        return isNotifyTime;
    }

    public static void checkArmorForReminder(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        if (helmet != null && notifyHelmet(helmet, getPercentDurabilityLeft(helmet))) {
            NotifyHandler.createArmorReminder(player, helmet, (int) getPercentDurabilityLeft(helmet));
        }
        if (chestplate != null && notifyChestplate(chestplate, getPercentDurabilityLeft(chestplate))) {
            NotifyHandler.createArmorReminder(player, chestplate, (int) getPercentDurabilityLeft(chestplate));
        }
        if (leggings != null && notifyLeggings(leggings, getPercentDurabilityLeft(leggings))) {
            NotifyHandler.createArmorReminder(player, leggings, (int) getPercentDurabilityLeft(leggings));
        }
        if (boots != null && notifyBoots(boots, getPercentDurabilityLeft(boots))) {
            NotifyHandler.createArmorReminder(player, boots, (int) getPercentDurabilityLeft(boots));
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

    public void checkArmorCommand(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        player.sendMessage(ChatColor.WHITE + "--------------------------");
        if (helmet != null) {
            NotifyHandler.sendArmorCommandMessage(player, helmet, getPercentDurabilityLeft(helmet));
        } else {
            player.sendMessage(ChatColor.GRAY + "Helmet " + ChatColor.WHITE + "-" + ChatColor.GRAY + " none");
        }
        if (chestplate != null) {
            NotifyHandler.sendArmorCommandMessage(player, chestplate, getPercentDurabilityLeft(chestplate));
        } else {
            player.sendMessage(ChatColor.GRAY + "Chestplate " + ChatColor.WHITE + "-" + ChatColor.GRAY + " none");
        }
        if (leggings != null) {
            NotifyHandler.sendArmorCommandMessage(player, leggings, getPercentDurabilityLeft(leggings));
        } else {
            player.sendMessage(ChatColor.GRAY + "Leggings " + ChatColor.WHITE + "-" + ChatColor.GRAY + " none");
        }
        if (boots != null) {
            NotifyHandler.sendArmorCommandMessage(player, boots, getPercentDurabilityLeft(boots));
        } else {
            player.sendMessage(ChatColor.GRAY + "Boots " + ChatColor.WHITE + "-" + ChatColor.GRAY + " none");
        }
        player.sendMessage(ChatColor.WHITE + "--------------------------");
    }
}
