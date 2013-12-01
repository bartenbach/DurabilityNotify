package co.proxa.durabilitynotify;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Armor {

    private ListManager lm;

    public Armor(ListManager lm) {
        this.lm = lm;
    }

    private double getPercentDurabilityLeft(ItemStack is) {
        return (100 - (((double)is.getDurability() / (double)is.getType().getMaxDurability()) * 100.00));
    }

    private boolean isApproximatePercentage(double currentDurability, List<Integer> configList) {
        for (double d : configList) {
            if (currentDurability >= (d - 1) && currentDurability <= (d + 1)) {
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

        if (helmet != null) {
            checkHelmet(player, helmet, getPercentDurabilityLeft(helmet));
        }
        if (chestplate != null) {
            checkChestplate(player, chestplate, getPercentDurabilityLeft(chestplate));
        }
        if (leggings != null) {
            checkLeggings(player, leggings, getPercentDurabilityLeft(leggings));
        }
        if (player.getInventory().getBoots() != null) {
            checkboots(player, boots, getPercentDurabilityLeft(boots));
        }
    }

    private void checkHelmet(Player player, ItemStack helmet, double durability) {
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
        if (isNotifyTime) {
            Notify.createArmorWarning(player, helmet, (int) durability);
        }
    }

    private void checkChestplate(Player player, ItemStack chestplate, double durability) {
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
        if (isNotifyTime) {
            Notify.createArmorWarning(player, chestplate, (int) durability);
        }
    }

    private void checkLeggings(Player player, ItemStack leggings, double durability) {
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
        if (isNotifyTime) {
            Notify.createArmorWarning(player, leggings, (int) durability);
        }
    }

    private void checkboots(Player player, ItemStack boots, double durability) {
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
        if (isNotifyTime) {
            Notify.createArmorWarning(player, boots, (int) durability);
        }
    }


/*    private static void checkDurability(Player player, ItemStack is) {
        double percentLeft = (100.00 - Armor.getPercentDurabilityLeft(is));
        if (percentLeft >= 9.0 && percentLeft <= 11.0 && dn.getConfig().getBoolean(Paths.notifyAt10)) {
            Notify.createArmorWarning(player, is, 10);
        } else if (percentLeft >= 4.0 && percentLeft <= 6.0 && dn.getConfig().getBoolean(Paths.notifyAt5)) {
            Notify.createArmorWarning(player, is, 5);
        } else if (percentLeft == 0 && dn.getConfig().getBoolean(Paths.notifyOnBreak)) {
            Notify.createArmorWarning(player, is, 0);
        }
    }*/

    public void checkArmorForReminder(Player player) {
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

    private void remindDurability(Player player, ItemStack is) {
/*        double percentLeft = (100.00 - getPercentDurabilityLeft(is));
        if (percentLeft >= 5.0 && percentLeft <= 10.0 && dn.getConfig().getBoolean(Paths.notifyAt10)) {
            Notify.createArmorReminder(player, is, 10);
        } else if (percentLeft >= 0.0 && percentLeft <= 5.0 && dn.getConfig().getBoolean(Paths.notifyAt5)) {
            Notify.createArmorReminder(player, is, 5);
        }*/
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
