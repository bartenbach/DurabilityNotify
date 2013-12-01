package co.proxa.durabilitynotify.threads;

import org.bukkit.entity.Player;
import co.proxa.durabilitynotify.Armor;
import co.proxa.durabilitynotify.DurabilityNotify;
import co.proxa.durabilitynotify.file.Paths;

public class ReminderThread extends Thread {

    private DurabilityNotify dn;
    private Armor a;

    public ReminderThread(DurabilityNotify dn, Armor a) {
        this.dn = dn;
        this.a = a;
    }

    @Override
    public void run() {
        while (dn != null) {
            try {
                Thread.sleep(dn.getConfig().getInt(Paths.reminderMinutes) * 1000 * 60);
                for (Player p : dn.getServer().getOnlinePlayers()) {
                    a.checkArmorForReminder(p);
                }
            } catch (InterruptedException ex) {} catch (NullPointerException n) {}
        }
    }

    public void checkEnabled() {
        if (dn.getConfig().getBoolean(Paths.reminderEnabled)) {
            this.start();
            System.out.println("Reminder thread started!  Checking every " + Paths.reminderMinutes + " minutes.");
        }
    }

    public void stopThread() {
        this.dn = null;
    }

}
