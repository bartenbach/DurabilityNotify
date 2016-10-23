package co.proxa.durabilitynotify.handler;

import co.proxa.durabilitynotify.DurabilityNotify;
import org.bukkit.entity.Player;
import co.proxa.durabilitynotify.file.Paths;

public class PermissionsHandler {


    private static DurabilityNotify dn;


    public PermissionsHandler(DurabilityNotify dura) {
        dn = dura;
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
