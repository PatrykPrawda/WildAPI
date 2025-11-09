package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;
import java.io.IOException;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.configuration.file.FileConfiguration;

class Save {
    static void saveYamlFile(FileConfiguration yamlConfig, File yamlFile) {
        if (yamlConfig != null && yamlFile != null) {
            try {
                yamlConfig.save(yamlFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Logger.debugLog("Saved yaml file");
        }
    }
}