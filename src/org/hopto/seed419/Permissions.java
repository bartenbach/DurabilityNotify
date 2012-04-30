package org.hopto.seed419;

import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/29/12
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Permissions {

    public static boolean hasPerms(Player player) {
        return player.hasPermission("Durability.notify") || player.isOp();
    }
}
