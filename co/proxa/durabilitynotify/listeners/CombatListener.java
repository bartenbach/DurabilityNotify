package co.proxa.durabilitynotify.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import co.proxa.durabilitynotify.*;

public class CombatListener implements Listener {

    private ListManager lm;
    private Armor a;

    public CombatListener (ListManager lm, Armor a) {
        this.lm = lm;
        this.a = a;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    void onPlayerInflictDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (!Permissions.hasToolPerms(player) || player.getItemInHand().getType() == Material.AIR) {
                return;
            }

            ItemStack item = player.getItemInHand();
            int usesLeft = Tools.getUsesLeft(item);

            if (!LiveNotify.checkLiveNotify(player, item, usesLeft)) {
                boolean isNotifyTime = false;
                if (Tools.isSword(item)) {
                    switch (item.getType()) {
                        case WOOD_SWORD:
                            isNotifyTime = lm.getWoodenSword().contains(usesLeft);
                            break;
                        case STONE_SWORD:
                            isNotifyTime = lm.getStoneSword().contains(usesLeft);
                            break;
                        case IRON_SWORD:
                            isNotifyTime = lm.getIronSword().contains(usesLeft);
                            break;
                        case GOLD_SWORD:
                            isNotifyTime = lm.getGoldSword().contains(usesLeft);
                            break;
                        case DIAMOND_SWORD:
                            isNotifyTime = lm.getDiamondSword().contains(usesLeft);
                            break;
                        default:
                            break;
                    }
                    if (isNotifyTime) {
                        Notify.sendNotification(player,item,usesLeft);
                    }
                } else if (Tools.isAxe(item)) {
                    // TODO: not sure how to handle this yet...
                    Notify.checkImproperToolForLowDurability(player, item, usesLeft);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    void onPlayerTakeDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getDamage() > 0) {

            Player player = (Player) event.getEntity();

            if (!Permissions.hasArmorPerms(player)) {
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

            //TODO Live armor notifications?
            a.checkArmor(player);
        }
    }
}
