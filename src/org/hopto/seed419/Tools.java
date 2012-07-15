package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 1:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tools {

    public static int getUsesLeft(ItemStack item) {
        return item.getType().getMaxDurability() - item.getDurability();
    }

     public static boolean isStoneTool(ItemStack is) {
        switch (is.getType()) {
            case STONE_AXE:
            case STONE_HOE:
            case STONE_PICKAXE:
            case STONE_SPADE:
            case STONE_SWORD:
                return true;
            default:
                return false;
        }
    }

    public static boolean isWoodTool(ItemStack is) {
        switch (is.getType()) {
            case WOOD_AXE:
            case WOOD_HOE:
            case WOOD_PICKAXE:
            case WOOD_SPADE:
            case WOOD_SWORD:
                return true;
            default:
                return false;
        }
    }

    public static boolean isGoldTool(ItemStack is) {
        switch (is.getType()) {
            case GOLD_AXE:
            case GOLD_HOE:
            case GOLD_PICKAXE:
            case GOLD_SPADE:
            case GOLD_SWORD:
                return true;
            default:
                return false;
        }
    }

    public static boolean isIronTool(ItemStack is) {
        switch (is.getType()) {
            case IRON_AXE:
            case IRON_HOE:
            case IRON_PICKAXE:
            case IRON_SPADE:
            case IRON_SWORD:
            case SHEARS:
                return true;
            default:
                return false;
        }
    }

    public static boolean isDiamondTool(ItemStack is) {
        switch (is.getType()) {
            case DIAMOND_AXE:
            case DIAMOND_HOE:
            case DIAMOND_PICKAXE:
            case DIAMOND_SPADE:
            case DIAMOND_SWORD:
                return true;
            default:
                return false;
        }
    }

    public static boolean isHoe(ItemStack is) {
        switch (is.getType()) {
            case WOOD_HOE:
            case DIAMOND_HOE:
            case STONE_HOE:
            case IRON_HOE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSword(ItemStack is) {
        switch (is.getType()) {
            case WOOD_SWORD:
            case DIAMOND_SWORD:
            case STONE_SWORD:
            case IRON_SWORD:
            case GOLD_SWORD:
                return true;
            default:
                return false;
        }
    }

    public static boolean isPickaxe(ItemStack is) {
        switch (is.getType()) {
            case WOOD_PICKAXE:
            case DIAMOND_PICKAXE:
            case STONE_PICKAXE:
            case IRON_PICKAXE:
            case GOLD_PICKAXE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isShovel(ItemStack is) {
        switch (is.getType()) {
            case WOOD_SPADE:
            case DIAMOND_SPADE:
            case STONE_SPADE:
            case GOLD_SPADE:
            case IRON_SPADE:
                return true;
            default:
                return false;
        }
    }

    public static ChatColor getToolColor(ItemStack is) {
        switch (is.getType()) {
            case DIAMOND_SWORD:
            case DIAMOND_SPADE:
            case DIAMOND_PICKAXE:
            case DIAMOND_AXE:
            case DIAMOND_HOE:
                return ChatColor.AQUA;
            case GOLD_HOE:
            case GOLD_AXE:
            case GOLD_SPADE:
            case GOLD_SWORD:
            case GOLD_PICKAXE:
                return ChatColor.GOLD;
            case IRON_AXE:
            case IRON_PICKAXE:
            case IRON_HOE:
            case IRON_SPADE:
            case IRON_SWORD:
            case SHEARS:
                return ChatColor.GRAY;
            default:
                return null;
        }
    }

}
