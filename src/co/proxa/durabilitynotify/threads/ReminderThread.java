package co.proxa.durabilitynotify.threads;

import org.bukkit.entity.Player;
import co.proxa.durabilitynotify.Armor;
import co.proxa.durabilitynotify.DurabilityNotify;

public class ReminderThread extends Thread {

    private DurabilityNotify dn;
    private Armor a;
    private int minutes;

    public ReminderThread(DurabilityNotify dn, Armor a, int minutes) {
        this.dn = dn;
        this.a = a;
        this.minutes = minutes;
    }

    @Override
    public void run() {
        while (dn != null) {
            try {
                Thread.sleep(this.minutes * 1000 * 60);
                for (Player p : dn.getServer().getOnlinePlayers()) {
                    a.checkArmorForReminder(p);
                }
            } catch (InterruptedException ex) {} catch (NullPointerException n) {}
        }
    }

    public void startThread() {
        this.start();
        dn.getLogger().info("Reminder thread started!  Checking every " + this.minutes + " minutes.");
    }

    public void stopThread() {
        this.dn = null;
    }

}
