package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

class GetIntValue {
    static int getYamlInt(String name, String fileName) {
        Logger.debugLog("Getting yaml int value from " + fileName);
        if (name != null && fileName != null) {
            if (!name.isEmpty()) {
                File file = new File("plugins_config/" + fileName + ".yml");
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
                return yamlConfiguration.getInt(name);
            }  return -999999;
        }  return -999999;
    }
}