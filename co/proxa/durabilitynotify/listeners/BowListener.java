package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class BowListener implements Listener {

    private ListManager lm;

    public BowListener(ListManager lm) {
        this.lm = lm;
    }

    @EventHandler
    void onBowFire(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (!Permissions.hasToolPerms(player)) {
                return;
            }

            int usesLeft = Tool.getUsesLeft(event.getBow());

            if (!LiveNotify.checkLiveNotify(player, event.getBow(), usesLeft)) {
                if (lm.getBow().contains(usesLeft)) {
                    Notify.createToolWarning(player, event.getBow(), usesLeft);
                }
            }
        }
    }
}
