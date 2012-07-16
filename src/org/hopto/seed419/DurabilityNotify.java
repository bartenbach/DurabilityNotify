package org.hopto.seed419;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.hopto.seed419.File.FileHandler;
import org.hopto.seed419.Listeners.*;

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

    private final static BlockBreakListener pl = new BlockBreakListener();
    private final static BowListener bl = new BowListener();
    private final static FishingListener fl = new FishingListener();
    private final static HoeListener hl = new HoeListener();
    private final static LiveNotify ln = new LiveNotify();
    private static CombatListener scl;
    private static Permissions perm;
    private static FileHandler fh;
    PluginManager pm;

    @Override
    public void onEnable() {
        /*Handle configuration file*/
        fh = new FileHandler(this);
        fh.checkFiles();

        /*Pass Instance variables to classes*/
        perm = new Permissions(this);
        scl = new CombatListener(this);

        /*Register events*/
        pm = getServer().getPluginManager();
        pm.registerEvents(pl, this);
        pm.registerEvents(bl, this);
        pm.registerEvents(fl, this);
        pm.registerEvents(hl, this);
        pm.registerEvents(scl, this);
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
