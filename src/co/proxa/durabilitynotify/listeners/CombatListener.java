package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.file.ConfigHandler;
import co.proxa.durabilitynotify.handler.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CombatListener implements Listener {

    private ConfigHandler lm;
    private ArmorHandler a;

    public CombatListener (ConfigHandler lm, ArmorHandler a) {
        this.lm = lm;
        this.a = a;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    void onPlayerInflictDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (!PermissionsHandler.hasToolPerms(player) || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                return;
            }

            ItemStack item = player.getInventory().getItemInMainHand();
            int usesLeft = ToolHandler.getUsesLeft(item);

            if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {

                List<Integer> notifyList = ToolHandler.getToolList(item);

                if (ToolHandler.isSword(item) && notifyList.contains(usesLeft)) {
                    NotifyHandler.createToolWarning(player, item, usesLeft, false);
                } else {
                    if (notifyList != null) {
                        if (notifyList.contains(usesLeft) || notifyList.contains(usesLeft+1)) {
                            NotifyHandler.createToolWarning(player, item, usesLeft, true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    void onPlayerTakeDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getDamage() > 0) {

            Player player = (Player) event.getEntity();

            if (!PermissionsHandler.hasArmorPerms(player)) {
                return;
            }

            switch (event.getCause()) {
            case DROWNING:
            case POISON:
            case STARVATION:
            case VOID:
            case FALL:
            case FIRE_TICK:
            case SUFFOCATION:
            case MAGIC:
                return;
            }

            a.checkArmor(player);
        }
    }
}
