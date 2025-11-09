package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

class RemoveValue {
    static boolean removeYamlValue(String name, String fileName) {
        try {
            Logger.debugLog("Removed value " + name + " in " + fileName);
            if (name != null && fileName != null && !name.isEmpty()) {
                File file = new File("plugins_config/" + fileName + ".yml");
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
                yamlConfiguration.set(name, null);
                Save.saveYamlFile(yamlConfiguration, file);
                return true;
            }
        } catch(Exception e) {
            return false;
        }
        return false;
    }
}