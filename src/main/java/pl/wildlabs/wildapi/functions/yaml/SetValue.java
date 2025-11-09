package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

class SetValue {
    static void setYamlValue(String name, String value, String fileName) {
        if (name != null && value != null && fileName != null && !name.isEmpty() && !value.isEmpty() && !fileName.isEmpty()) {
            File file = new File("plugins_config/" + fileName + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            if (value.matches("[0-9]+")) {
                int intValue = Integer.parseInt(value);
                yamlConfiguration.set(name, Integer.valueOf(intValue));
            } else if (value.equalsIgnoreCase("true")) {
                yamlConfiguration.set(name, Boolean.valueOf(true));
            } else if (value.equalsIgnoreCase("false")) {
                yamlConfiguration.set(name, Boolean.valueOf(false));
            } else {
                yamlConfiguration.set(name, value);
            }  Save.saveYamlFile((FileConfiguration)yamlConfiguration, file);
            Logger.debugLog("Setted value in yaml file " + fileName);
        }
    }
}