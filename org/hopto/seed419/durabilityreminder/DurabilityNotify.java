package org.hopto.seed419;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.hopto.seed419.file.FileHandler;
import org.hopto.seed419.listeners.*;
import org.hopto.seed419.threads.ReminderThread;

/*  Attribute Only (Public) License
        Version 0.a3, July 11, 2011

    Copyright (C) 2012 Blake Bartenbach <seed419@gmail.com> (@seed419)

    Anyone is allowed to copy and distribute verbatim or modified
    copies of this license document and altering is allowed as long
    as you attribute the author(s) of this license document / files.

    ATTRIBUTE ONLY PUBLIC LICENSE
    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

      1. Attribute anyone attached to the license document.
         * Do not remove pre-existing attributes.

         Plausible attribution methods:
            1. Through comment blocks.
            2. Referencing on a site, wiki, or about page.

      2. Do whatever you want as long as you don't invalidate 1.


@license AOL v.a3 <http://aol.nexua.org>*/

public class DurabilityNotify extends JavaPlugin {

    private final BlockBreakListener pl = new BlockBreakListener();
    private final BowListener bl = new BowListener();
    private final FishingListener fl = new FishingListener();
    private final HoeListener hl = new HoeListener();
    private final LiveNotify ln = new LiveNotify();
    private final Permissions p = new Permissions(this);
    private final CombatListener scl = new CombatListener(this);
    private final FlintAndSteelListener fasl = new FlintAndSteelListener();
    private final ShearListener sl = new ShearListener();
    private Notify n;
    private FileHandler fh;
    private ReminderThread rt;
    private Armor a;
    PluginManager pm;

    @Override
    public void onEnable() {
        fh = new FileHandler(this);
        fh.checkFiles();

        rt = new ReminderThread(this);
        rt.checkEnabled();

        a = new Armor(this);
        n = new Notify(this);

        pm = getServer().getPluginManager();
        pm.registerEvents(pl, this);
        pm.registerEvents(bl, this);
        pm.registerEvents(fl, this);
        pm.registerEvents(hl, this);
        pm.registerEvents(scl, this);
        pm.registerEvents(fasl, this);
        pm.registerEvents(sl, this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "SimpleNotice");
    }

    @Override
    public void onDisable() {
        rt.stopThread();
        ln.clearMap();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && Permissions.hasToolPerms((Player) sender) && label.equalsIgnoreCase("dura")) {
            if (ln.onMap((Player)sender)) {
                ln.toggleNotify((Player)sender);
            } else {
                LiveNotify.putPlayerOnMap((Player)sender);
            }
            ln.sendMessage((Player)sender);
            return true;
        }
        return false;
    }
}
