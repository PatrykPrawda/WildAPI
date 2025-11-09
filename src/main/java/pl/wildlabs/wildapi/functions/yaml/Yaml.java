package pl.wildlabs.wildapi.functions.yaml;

import pl.wildlabs.wildapi.plugin.Logger;

public class Yaml {

    private String file;

    /**
     * Create new yaml instance
     */
    public Yaml() {
        file = null;
    }

    /**
     * Create new yaml instance for specified file
     * @param fileName file name (e.g. myYaml)
     */
    public Yaml(String fileName) {
        file = fileName;
    }

    /**
     * Set default yaml file to use functions without giving file name
     * @param fileName file name (e.g. myYaml)
     */
    public void setFile(String fileName) {
        file = fileName;
    }

    /**
     * Create new yaml file.
     * File name must be specified in new Yaml(...) or setFile(...)
     */
    public void createFile() {
        if(file != null) {
            createFile(file);
        } else {
            Logger.errorLog("&cNo file information, use 'setFile(String)' after 'createFile()'");
        }
    }

    /**
     * Create new specified yaml file
     * @param fileName file name (e.g. myYaml)
     */
    public void createFile(String fileName) {
        Create.createYamlFile(fileName);
    }

    /**
     * Delete yaml file.
     * File name must be specified in new Yaml(...) or setFile(...)
     * @return true if file deleted
     */
    public boolean deleteFile() {
        if(file != null) {
            return deleteFile(file);
        } else {
            return false;
        }
    }

    /**
     * Delete specified yaml file
     * @param fileName file name (e.g. myYaml)
     * @return true if file deleted
     */
    public boolean deleteFile(String fileName) {
        return Delete.deleteYamlFile(fileName);
    }

    /**
     * Set value
     * @param pathName path to the value (e.g. my.yaml.path)
     * @param value value to be set in path name
     */
    public void setValue(String pathName, String value) {
        if(file != null) {
            setValue(pathName, value, file);
        } else {
            Logger.errorLog("&cNo file information, use 'setFile(String)' after 'setValue(String,String)'");
        }
    }

    public void setValue(String pathName, String value, String fileName) {
        SetValue.setYamlValue(pathName, value, fileName);
    }

    public boolean removeValue(String pathName) {
        if(file != null) {
            return removeValue(pathName, file);
        } else {
            Logger.errorLog("&cNo file information, use 'setFile(String)' after 'removeValue(String)'");
        }
        return false;
    }

    public boolean removeValue(String pathName, String fileName) {
        return RemoveValue.removeYamlValue(pathName, fileName);
    }

    public String getStringValue(String pathName) {
        if(file != null) {
            return getStringValue(pathName, file);
        } else {
            Logger.errorLog("&cNo file information, use 'setFile(String)' after 'getStringValue(String)'");
        }
        return null;
    }

    public String getStringValue(String pathName, String fileName) {
        return GetStringValue.getYamlString(pathName, fileName);
    }

    public int getIntValue(String pathName) {
        if(file != null) {
            return getIntValue(pathName, file);
        } else {
            Logger.errorLog("&cNo file information, use 'setFile(String)' after 'getIntValue(String)'");
        }
        return -999999999;
    }

    public int getIntValue(String pathName, String fileName) {
        return GetIntValue.getYamlInt(pathName, fileName);
    }

    public boolean getBooleanValue(String pathName) {
        if(file != null) {
            return getBooleanValue(pathName, file);
        } else {
            Logger.errorLog("&cNo file information, use 'setFile(String)' after 'getBooleanValue()'");
        }
        return false;
    }

    public boolean getBooleanValue(String pathName, String fileName) {
        return GetBooleanValue.getYamlBoolean(pathName, fileName);
    }

    public boolean isExist() {
        return isExist(file);
    }

    public boolean isExist(String localFileName) {
        return IsExist.GetYamlFileExist(localFileName);
    }

    public boolean rename(String newFileName) {
        return rename(file, newFileName);
    }

    public boolean rename(String fileName, String newFileName) {
        boolean renamed = Rename.rename(fileName, newFileName);

        if(renamed) {
            file = newFileName;
        }

        return renamed;
    }

    public String getFullPath() {
        return getFullPath(file);
    }

    public String getFullPath(String fileName) {
        return GetPath.getFullPath(fileName);
    }

    public String getFullDirectoryPath() {
        return getFullDirectoryPath(file);
    }

    public String getFullDirectoryPath(String fileName) {
        return GetPath.getFullDirectoryPath(fileName);
    }
}
