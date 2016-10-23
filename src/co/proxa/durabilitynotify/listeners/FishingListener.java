package co.proxa.durabilitynotify.listeners;

import co.proxa.durabilitynotify.file.ConfigHandler;
import co.proxa.durabilitynotify.handler.LiveNotifyHandler;
import co.proxa.durabilitynotify.handler.NotifyHandler;
import co.proxa.durabilitynotify.handler.PermissionsHandler;
import co.proxa.durabilitynotify.handler.ToolHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishingListener implements Listener {

    private ConfigHandler lm;

    public FishingListener(ConfigHandler lm) {
        this.lm = lm;
    }


    @EventHandler
    void onPlayerFish(PlayerFishEvent event) {

        Player player = event.getPlayer();

        if (!PermissionsHandler.hasToolPerms(player)) {
            return;
        }

        int usesLeft = ToolHandler.getUsesLeft(event.getPlayer().getInventory().getItemInMainHand());
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        switch (event.getState()) {
            case CAUGHT_FISH:
                if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {
                    if (lm.getFishingRod().contains(usesLeft)) {
                        NotifyHandler.createToolWarning(player, item, usesLeft, false);
                    }
                }
                break;
            case IN_GROUND:
                if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {
                    //TODO TEST
                    if (lm.getFishingRod().contains(usesLeft) || lm.getFishingRod().contains(usesLeft+1)) {
                        NotifyHandler.createToolWarning(player, item ,usesLeft, true);
                    }
                }
                break;
            case CAUGHT_ENTITY:
                if (!LiveNotifyHandler.checkLiveNotify(player, item, usesLeft)) {
                    //TODO fix
                    //NotifyHandler.checkReallyImproperToolForLowDurability(player, item, usesLeft);
                }
                break;
            default:
                break;
        }
    }
}
