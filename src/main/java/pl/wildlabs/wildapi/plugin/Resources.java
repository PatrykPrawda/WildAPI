package pl.wildlabs.wildapi.plugin;

import pl.wildlabs.wildapi.functions.yaml.Yaml;

import java.io.File;

public class Resources {

    protected String serverUrl = "http://foxworks.mncv.pl/files/foxapi/resources/";

    protected String[] resources = new String[] {
            "lang/en.yml",
            "lang/pl.yml",
            "translations/1_13/blocks.yml",
            "translations/1_13/items.yml"
    };

    public void installResources() {
        for(String resource : resources) {
            Yaml resourceYaml = new Yaml(resource.replace(".yml", ""));

            if(!resourceYaml.isExist()) {

            }
        }
    }

    protected String resourcesPath = "FoxAPI/resources/resources";
    protected String latestResourcesPath = "FoxAPI/resources/latest_resources";
    protected String resourcesDir = "plugins_config/FoxAPI/resources";

    protected int resourcesCount = -1;

    public void checkResources() {

        Logger.log("Checking resources...");

        int resourcesVersion = -1;
        int latestResourcesVersion = -1;

        boolean downloadNewResources = false;

        Yaml resourcesYaml = new Yaml(resourcesPath);

        String resourcesListUrl = serverUrl + "resources.yml";

        Logger.log("Downloading resources.yml from " + resourcesListUrl);

        IOUtils.downloadResource(resourcesListUrl, "plugins_config/" + latestResourcesPath + ".yml");

        createDirectory(resourcesDir);

        Yaml latestResourcesYaml = new Yaml(latestResourcesPath);

        Logger.log("Downloaded resources.yml and saved as latest_resources.yml");

        if(resourcesYaml.isExist()) resourcesVersion = resourcesYaml.getIntValue("version");
        if(latestResourcesYaml.isExist()) latestResourcesVersion = latestResourcesYaml.getIntValue("version");

        Logger.log("Latest resources version is " + latestResourcesVersion);

        if(latestResourcesVersion < 0) return;

        if(resourcesVersion < 0 || latestResourcesVersion > resourcesVersion) downloadNewResources = true;

        Logger.log("resources: " + resourcesVersion + ", latest: " + latestResourcesVersion + ", download?: " + downloadNewResources);

        resourcesYaml.deleteFile();

        if(downloadNewResources) {
            latestResourcesYaml.rename(resourcesPath);

            for(int i = 1; i < 9999; i++) {

                Logger.log("Checking: " + i);

                String value = latestResourcesYaml.getStringValue("" + i);
                if(value == null) {
                    resourcesCount = i - 1;
                    break;
                }

                Yaml resYaml = new Yaml(value);

                if(resYaml.isExist()) {
                    resYaml.deleteFile();
                }
                createDirectory(resourcesDir + "/" + value);
            }
        }

        verifyResources();
    }

    protected void createDirectory(String filePath) {
        String[] splitted = filePath.split("/");

        StringBuilder path = new StringBuilder();

        for(int i = 0; i < splitted.length; i++) {
            if(i == 0) {
                path.append(splitted[i]);
            } else {
                path.append("/" + splitted[i]);
            }

            File checkingDirectory = new File(path.toString());

            if(!checkingDirectory.exists()) {
                checkingDirectory.mkdir();
            }

            if(i + 1 == splitted.length - 1) {
                if(splitted[i + 1].contains(".")) {
                    return;
                }
            }
        }
    }

    protected void downloadResource(String resource) {
        Logger.log("Downloading 'plugins_config/FoxAPI/" + resource + "' from '" + serverUrl + resource + "'");

        IOUtils.downloadResource(serverUrl + resource, "plugins_config/FoxAPI/" + resource);
    }

    protected void verifyResources() {
        Yaml resourcesYaml = new Yaml(resourcesPath);

        if(resourcesCount < 0) {
            resourcesCount = 9999;
        }

        for(int i = 0; i < resourcesCount; i++) {
            String value = resourcesYaml.getStringValue("" + i);
            if(value == null) break;

            if(!new Yaml(value).isExist()) {
                downloadResource(resourcesDir + "/" + value);
            }
        }
    }
}
