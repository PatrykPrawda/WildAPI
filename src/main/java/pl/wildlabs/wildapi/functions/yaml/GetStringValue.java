package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

class GetStringValue {
    static String getYamlString(String name, String fileName) {
        Logger.debugLog("Getting yaml string value from " + fileName);
        if (name != null && fileName != null) {
            if (!name.isEmpty()) {
                File file = new File("plugins_config/" + fileName + ".yml");
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
                return yamlConfiguration.getString(name);
            }  return null;
        }  return null;
    }
}