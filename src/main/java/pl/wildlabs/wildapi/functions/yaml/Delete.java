package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;

class Delete {
    static boolean deleteYamlFile(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File("plugins_config/" + fileName + ".yml");
            if (file.exists()) {
                return file.delete();
            }
            return false;
        }
        return false;
    }
}