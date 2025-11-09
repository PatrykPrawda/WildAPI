package pl.wildlabs.wildapi.functions.yaml;

import java.io.File;

class Rename {
    static boolean rename(String fileName, String newFileName) {
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File("plugins_config/" + fileName + ".yml");
            File newFile = new File("plugins_config/" + newFileName + ".yml");
            if (file.exists()) {
                return file.renameTo(newFile);
            }
            return false;
        }
        return false;
    }
}
