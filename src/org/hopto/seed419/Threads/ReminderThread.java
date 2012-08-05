package org.hopto.seed419.Threads;

import org.bukkit.entity.Player;
import org.hopto.seed419.Armor;
import org.hopto.seed419.DurabilityNotify;
import org.hopto.seed419.File.Paths;

/**
 * Attribute Only (Public) License
 * Version 0.a3, July 11, 2011
 * <p/>
 * Copyright (C) 2012 Blake Bartenbach <seed419@gmail.com> (@seed419)
 * <p/>
 * Anyone is allowed to copy and distribute verbatim or modified
 * copies of this license document and altering is allowed as long
 * as you attribute the author(s) of this license document / files.
 * <p/>
 * ATTRIBUTE ONLY PUBLIC LICENSE
 * TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 * <p/>
 * 1. Attribute anyone attached to the license document.
 * Do not remove pre-existing attributes.
 * <p/>
 * Plausible attribution methods:
 * 1. Through comment blocks.
 * 2. Referencing on a site, wiki, or about page.
 * <p/>
 * 2. Do whatever you want as long as you don't invalidate 1.
 *
 * @license AOL v.a3 <http://aol.nexua.org>
 */

public class ReminderThread extends Thread {

    private DurabilityNotify dn;

    public ReminderThread(DurabilityNotify dn) {
        this.dn = dn;
    }

    @Override
    public void run() {
        while (dn != null) {
            try {
                Thread.sleep(dn.getConfig().getInt(Paths.reminderMinutes) * 1000 * 60);
                for (Player p : dn.getServer().getOnlinePlayers()) {
                    Armor.checkArmorForReminder(p);
                }
            } catch (InterruptedException ex) {} catch (NullPointerException n) {}
        }
    }

    public void checkEnabled() {
        if (dn.getConfig().getBoolean(Paths.reminderEnabled)) {
            this.start();
        }
    }

    public void stopThread() {
        this.dn = null;
    }

}
