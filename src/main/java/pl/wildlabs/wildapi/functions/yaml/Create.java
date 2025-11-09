package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

class Create {
    static void createYamlFile(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File("plugins_config/" + fileName + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            Save.saveYamlFile((FileConfiguration)yamlConfiguration, file);
            Logger.debugLog("Created yaml file " + fileName);
        }
    }
}