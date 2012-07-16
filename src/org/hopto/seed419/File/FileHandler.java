package org.hopto.seed419.File;

import org.hopto.seed419.DurabilityNotify;

public class FileHandler {


    private DurabilityNotify  dn;


    public FileHandler(DurabilityNotify dn) {
        this.dn = dn;
    }


    public void checkFiles() {
        if (!dn.getDataFolder().exists()) {
            dn.getDataFolder().mkdirs();
        }
        dn.getConfig().options().copyDefaults(true);
        dn.saveConfig();
    }
}



