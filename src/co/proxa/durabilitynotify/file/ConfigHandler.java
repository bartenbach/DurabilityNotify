/**
 * Copyright (C) 2013 Blake Bartenbach
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package co.proxa.durabilitynotify.file;

import co.proxa.durabilitynotify.DurabilityNotify;

import java.util.List;

public class ConfigHandler {

    private DurabilityNotify dn;
    private List<Integer> woodenPickaxe;
    private List<Integer> stonePickaxe;
    private List<Integer> ironPickaxe;
    private List<Integer> goldPickaxe;
    private List<Integer> diamondPickaxe;
    private List<Integer> woodenShovel;
    private List<Integer> stoneShovel;
    private List<Integer> ironShovel;
    private List<Integer> goldShovel;
    private List<Integer> diamondShovel;
    private List<Integer> woodenAxe;
    private List<Integer> stoneAxe;
    private List<Integer> ironAxe;
    private List<Integer> goldAxe;
    private List<Integer> diamondAxe;
    private List<Integer> woodenSword;
    private List<Integer> stoneSword;
    private List<Integer> ironSword;
    private List<Integer> goldSword;
    private List<Integer> diamondSword;
    private List<Integer> woodenHoe;
    private List<Integer> stoneHoe;
    private List<Integer> ironHoe;
    private List<Integer> goldHoe;
    private List<Integer> diamondHoe;
    private List<Integer> bow;
    private List<Integer> fishingRod;
    private List<Integer> flintAndSteel;
    private List<Integer> shears;
    //private List<Integer> carrotStick;
    private List<Integer> leatherHelmet;
    private List<Integer> leatherChestplate;
    private List<Integer> leatherLeggings;
    private List<Integer> leatherBoots;
    private List<Integer> goldHelmet;
    private List<Integer> goldChestplate;
    private List<Integer> goldLeggings;
    private List<Integer> goldBoots;
    private List<Integer> chainmailHelmet;
    private List<Integer> chainmailChestplate;
    private List<Integer> chainmailLeggings;
    private List<Integer> chainmailBoots;
    private List<Integer> ironHelmet;
    private List<Integer> ironChestplate;
    private List<Integer> ironLeggings;
    private List<Integer> ironBoots;
    private List<Integer> diamondHelmet;
    private List<Integer> diamondChestplate;
    private List<Integer> diamondLeggings;
    private List<Integer> diamondBoots;
    public static String reminderMsg;
    public static String liveNotifyOnMsg;
    public static String liveNotifyOffMsg;
    public static String infoMsg;
    public static String toolWarningMsg;
    public static String toolWarningBroken;
    public static String armorWarningMsg;
    public static String armorWarningBroken;
    public static String infoBroken;
    public static String improperToolMsg;
    public static String grammarSingular;
    public static String grammarPlural;
    public static String grammar2Singular;
    public static String grammar2Plural;

    public ConfigHandler(DurabilityNotify dn) {
        this.dn = dn;
    }

    public void initializeLists() {
        woodenPickaxe = dn.getConfig().getIntegerList(Paths.woodenPickaxe);
        stonePickaxe = dn.getConfig().getIntegerList(Paths.stonePickaxe);
        ironPickaxe = dn.getConfig().getIntegerList(Paths.ironPickaxe);
        goldPickaxe = dn.getConfig().getIntegerList(Paths.goldPickaxe);
        diamondPickaxe = dn.getConfig().getIntegerList(Paths.diamondPickaxe);
        woodenShovel = dn.getConfig().getIntegerList(Paths.woodenShovel);
        stoneShovel = dn.getConfig().getIntegerList(Paths.stoneShovel);
        ironShovel = dn.getConfig().getIntegerList(Paths.ironShovel);
        goldShovel = dn.getConfig().getIntegerList(Paths.goldShovel);
        diamondShovel = dn.getConfig().getIntegerList(Paths.diamondShovel);
        woodenAxe = dn.getConfig().getIntegerList(Paths.woodenAxe);
        stoneAxe = dn.getConfig().getIntegerList(Paths.stoneAxe);
        ironAxe = dn.getConfig().getIntegerList(Paths.ironAxe);
        goldAxe = dn.getConfig().getIntegerList(Paths.goldAxe);
        diamondAxe = dn.getConfig().getIntegerList(Paths.diamondAxe);
        woodenSword = dn.getConfig().getIntegerList(Paths.woodenSword);
        stoneSword = dn.getConfig().getIntegerList(Paths.stoneSword);
        ironSword = dn.getConfig().getIntegerList(Paths.ironSword);
        goldSword = dn.getConfig().getIntegerList(Paths.goldSword);
        diamondSword = dn.getConfig().getIntegerList(Paths.diamondSword);
        woodenHoe = dn.getConfig().getIntegerList(Paths.woodenHoe);
        stoneHoe = dn.getConfig().getIntegerList(Paths.stoneHoe);
        ironHoe = dn.getConfig().getIntegerList(Paths.ironHoe);
        goldHoe = dn.getConfig().getIntegerList(Paths.goldHoe);
        diamondHoe = dn.getConfig().getIntegerList(Paths.diamondHoe);
        bow = dn.getConfig().getIntegerList(Paths.bow);
        fishingRod = dn.getConfig().getIntegerList(Paths.fishingRod);
        flintAndSteel = dn.getConfig().getIntegerList(Paths.flintAndSteel);
        shears = dn.getConfig().getIntegerList(Paths.shears);
        //carrotStick = dn.getConfig().getIntegerList(Paths.carrotStick);
        leatherHelmet = dn.getConfig().getIntegerList(Paths.leatherHelmet);
        leatherChestplate = dn.getConfig().getIntegerList(Paths.leatherChestplate);
        leatherLeggings = dn.getConfig().getIntegerList(Paths.leatherLeggings);
        leatherBoots = dn.getConfig().getIntegerList(Paths.leatherBoots);
        chainmailHelmet = dn.getConfig().getIntegerList(Paths.chainmailHelmet);
        chainmailChestplate = dn.getConfig().getIntegerList(Paths.chainmailChestplate);
        chainmailLeggings = dn.getConfig().getIntegerList(Paths.chainmailLeggings);
        chainmailBoots = dn.getConfig().getIntegerList(Paths.chainmailBoots);
        goldHelmet = dn.getConfig().getIntegerList(Paths.goldHelmet);
        goldChestplate = dn.getConfig().getIntegerList(Paths.goldChestplate);
        goldLeggings = dn.getConfig().getIntegerList(Paths.goldLeggings);
        goldBoots = dn.getConfig().getIntegerList(Paths.goldBoots);
        ironHelmet = dn.getConfig().getIntegerList(Paths.ironHelmet);
        ironChestplate = dn.getConfig().getIntegerList(Paths.ironChestplate);
        ironLeggings = dn.getConfig().getIntegerList(Paths.ironLeggings);
        ironBoots = dn.getConfig().getIntegerList(Paths.ironBoots);
        diamondHelmet = dn.getConfig().getIntegerList(Paths.diamondHelmet);
        diamondChestplate = dn.getConfig().getIntegerList(Paths.diamondChestplate);
        diamondLeggings = dn.getConfig().getIntegerList(Paths.diamondLeggings);
        diamondBoots = dn.getConfig().getIntegerList(Paths.diamondBoots);
        reminderMsg = dn.getConfig().getString(Paths.reminderMsg);
        liveNotifyOffMsg = dn.getConfig().getString(Paths.liveDuraOffMsg);
        liveNotifyOnMsg = dn.getConfig().getString(Paths.liveDuraOnMsg);
        infoMsg = dn.getConfig().getString(Paths.infoMsg);
        infoBroken = dn.getConfig().getString(Paths.infoBrokenMsg);
        toolWarningMsg = dn.getConfig().getString(Paths.toolWarningMsg);
        toolWarningBroken = dn.getConfig().getString(Paths.toolWarningBrokenMsg);
        armorWarningMsg = dn.getConfig().getString(Paths.armorWarningMsg);
        armorWarningBroken = dn.getConfig().getString(Paths.armorWarningBrokenMsg);
        improperToolMsg = dn.getConfig().getString(Paths.improperToolMsg);
        grammarSingular = dn.getConfig().getString(Paths.grammarSingular);
        grammarPlural = dn.getConfig().getString(Paths.grammarPlural);
        grammar2Singular = dn.getConfig().getString(Paths.grammar2Singular);
        grammar2Plural = dn.getConfig().getString(Paths.grammar2Plural);
    }

    public List<Integer> getWoodenPickaxe() {
        return woodenPickaxe;
    }

    public List<Integer> getStonePickaxe() {
        return stonePickaxe;
    }

    public List<Integer> getIronPickaxe() {
        return ironPickaxe;
    }

    public List<Integer> getGoldPickaxe() {
        return goldPickaxe;
    }

    public List<Integer> getDiamondPickaxe() {
        return diamondPickaxe;
    }

    public List<Integer> getWoodenShovel() {
        return woodenShovel;
    }

    public List<Integer> getStoneShovel() {
        return stoneShovel;
    }

    public List<Integer> getIronShovel() {
        return ironShovel;
    }

    public List<Integer> getGoldShovel() {
        return goldShovel;
    }

    public List<Integer> getDiamondShovel() {
        return diamondShovel;
    }

    public List<Integer> getWoodenAxe() {
        return woodenAxe;
    }

    public List<Integer> getStoneAxe() {
        return stoneAxe;
    }

    public List<Integer> getIronAxe() {
        return ironAxe;
    }

    public List<Integer> getGoldAxe() {
        return goldAxe;
    }

    public List<Integer> getDiamondAxe() {
        return diamondAxe;
    }

    public List<Integer> getWoodenSword() {
        return woodenSword;
    }

    public List<Integer> getStoneSword() {
        return stoneSword;
    }

    public List<Integer> getIronSword() {
        return ironSword;
    }

    public List<Integer> getGoldSword() {
        return goldSword;
    }

    public List<Integer> getDiamondSword() {
        return diamondSword;
    }

    public List<Integer> getWoodenHoe() {
        return woodenHoe;
    }

    public List<Integer> getStoneHoe() {
        return stoneHoe;
    }

    public List<Integer> getIronHoe() {
        return ironHoe;
    }

    public List<Integer> getGoldHoe() {
        return goldHoe;
    }

    public List<Integer> getDiamondHoe() {
        return diamondHoe;
    }

    public List<Integer> getBow() {
        return bow;
    }

    public List<Integer> getFishingRod() {
        return fishingRod;
    }

    public List<Integer> getFlintAndSteel() {
        return flintAndSteel;
    }

    public List<Integer> getShears() {
        return shears;
    }

/*    public List<Integer> getCarrotStick() {
        return carrotStick;
    }*/

    public List<Integer> getLeatherHelmet() {
        return leatherHelmet;
    }

    public List<Integer> getLeatherChestplate() {
        return leatherChestplate;
    }

    public List<Integer> getLeatherLeggings() {
        return leatherLeggings;
    }

    public List<Integer> getLeatherBoots() {
        return leatherBoots;
    }

    public List<Integer> getGoldHelmet() {
        return goldHelmet;
    }

    public List<Integer> getGoldChestplate() {
        return goldChestplate;
    }

    public List<Integer> getGoldLeggings() {
        return goldLeggings;
    }

    public List<Integer> getGoldBoots() {
        return goldBoots;
    }

    public List<Integer> getChainmailHelmet() {
        return chainmailHelmet;
    }

    public List<Integer> getChainmailChestplate() {
        return chainmailChestplate;
    }

    public List<Integer> getChainmailLeggings() {
        return chainmailLeggings;
    }

    public List<Integer> getChainmailBoots() {
        return chainmailBoots;
    }

    public List<Integer> getIronHelmet() {
        return ironHelmet;
    }

    public List<Integer> getIronChestplate() {
        return ironChestplate;
    }

    public List<Integer> getIronLeggings() {
        return ironLeggings;
    }

    public List<Integer> getIronBoots() {
        return ironBoots;
    }

    public List<Integer> getDiamondHelmet() {
        return diamondHelmet;
    }

    public List<Integer> getDiamondChestplate() {
        return diamondChestplate;
    }

    public List<Integer> getDiamondLeggings() {
        return diamondLeggings;
    }

    public List<Integer> getDiamondBoots() {
        return diamondBoots;
    }

}
