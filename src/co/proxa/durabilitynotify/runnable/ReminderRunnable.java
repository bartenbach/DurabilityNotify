package co.proxa.durabilitynotify.runnable;

import org.bukkit.entity.Player;
import co.proxa.durabilitynotify.handler.ArmorHandler;
import co.proxa.durabilitynotify.DurabilityNotify;

public class ReminderRunnable implements Runnable {


    private DurabilityNotify dn;


    public ReminderRunnable(DurabilityNotify dn) {
        this.dn = dn;
    }

    @Override
    public void run() {
        for (Player p : dn.getServer().getOnlinePlayers()) {
            ArmorHandler.checkArmorForReminder(p);
        }
    }

}
