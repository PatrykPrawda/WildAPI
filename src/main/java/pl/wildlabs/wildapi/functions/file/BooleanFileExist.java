package pl.wildlabs.wildapi.functions.file;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;
import pl.wildlabs.wildapi.plugin.WildAPI;

class BooleanFileExist {
    static boolean FileExist(String LocalFileName) {
        Logger.debugLog("Checking if file " + LocalFileName + " exist");
        if (LocalFileName != null) {
            if (!LocalFileName.isEmpty()) {
                LocalFileName = WildAPI.plugin.getDataFolder().getParentFile() + "/" + LocalFileName;
                return (new File(LocalFileName)).exists();
            }  return false;
        }  return false;
    }
}
