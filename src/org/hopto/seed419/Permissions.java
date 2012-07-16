package org.hopto.seed419;

import org.bukkit.entity.Player;
import org.hopto.seed419.File.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/29/12
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Permissions {


    private static DurabilityNotify dn;


    public Permissions(DurabilityNotify dn) {
        this.dn = dn;
    }

    public static boolean hasToolPerms(Player player) {
        return player.hasPermission("dura.tools") || player.hasPermission("dura.*")
                || dn.getConfig().getBoolean(Paths.useOP) && player.isOp();
    }

    public static boolean hasArmorPerms(Player player) {
        return player.hasPermission("dura.armor") || player.hasPermission("dura.*")
                || dn.getConfig().getBoolean(Paths.useOP) && player.isOp();
    }
}
