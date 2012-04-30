package org.hopto.seed419;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.hopto.seed419.Listeners.*;

/**
 * Created with IntelliJ IDEA.
 * User: seed419
 * Date: 4/28/12
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class DurabilityReminder extends JavaPlugin {

    private final static BlockBreakListener pl = new BlockBreakListener();
    private final static BowListener bl = new BowListener();
    private final static FishingListener fl = new FishingListener();
    private final static HoeListener hl = new HoeListener();
    private final static CombatListener scl = new CombatListener();
    PluginManager pm;

    public void onEnable() {
        pm = getServer().getPluginManager();
        pm.registerEvents(pl, this);
        pm.registerEvents(bl, this);
        pm.registerEvents(fl, this);
        pm.registerEvents(hl, this);
        pm.registerEvents(scl, this);
    }

}
