package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.file.ConfigHandler;
import co.proxa.durabilitynotify.handler.LiveNotifyHandler;
import co.proxa.durabilitynotify.handler.NotifyHandler;
import co.proxa.durabilitynotify.handler.PermissionsHandler;
import co.proxa.durabilitynotify.handler.ToolHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class BowListener implements Listener {

    private ConfigHandler lm;

    public BowListener(ConfigHandler lm) {
        this.lm = lm;
    }

    @EventHandler
    void onBowFire(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (!PermissionsHandler.hasToolPerms(player)) {
                return;
            }

            int usesLeft = ToolHandler.getUsesLeft(event.getBow());

            if (!LiveNotifyHandler.checkLiveNotify(player, event.getBow(), usesLeft)) {
                if (lm.getBow().contains(usesLeft)) {
                    NotifyHandler.createToolWarning(player, event.getBow(), usesLeft, false);
                }
            }
        }
    }
}
