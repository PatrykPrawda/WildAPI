package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;

class IsExist {
    static boolean GetYamlFileExist(String LocalFileName) {
        Logger.debugLog("Checking if yaml file " + LocalFileName + " exist");
        if (LocalFileName != null) {
            if (!LocalFileName.isEmpty()) {
                LocalFileName = "plugins_config/" + LocalFileName + ".yml";
                return (new File(LocalFileName)).exists();
            }  return false;
        }  return false;
    }
}