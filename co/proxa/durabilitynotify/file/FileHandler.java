package co.proxa.durabilitynotify.file;

import co.proxa.durabilitynotify.DurabilityNotify;

public class FileHandler {

    private DurabilityNotify dn;

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
