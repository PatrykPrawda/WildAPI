package pl.wildlabs.wildapi.plugin;

public class Updater {
    void CheckVersion() {
        Thread thread = new Thread() {
            public void run() {
                String latest = IOUtils.getContent("https://pastebin.com/raw/apwnEiFt");
                if (latest == null || latest.isEmpty()) {
                    Logger.log("&f__________________________________");
                    Logger.log("&f");
                    Logger.log("              &aWild&f&lAPI");
                    Logger.log("&f");
                    Logger.log("&4 I can't find information on which");
                    Logger.log("&4      version is the latest");
                    Logger.log("&f");
                    Logger.log("&f__________________________________");
                }
                else if (latest.equalsIgnoreCase(WildAPI.plugin.getDescription().getVersion())) {
                    Logger.log("&f__________________________________");
                    Logger.log("&f");
                    Logger.log("              &aWild&f&lAPI");
                    Logger.log("&f");
                    Logger.log("&a You are using the latest version");
                    Logger.log("&f");
                    Logger.log("&f__________________________________");
                } else {
                    Updater.this.CheckVersionWithoutSpecialChars(WildAPI.plugin.getDescription().getVersion(), latest, latest);
                }
            }
        };
        thread.start();
    }

    void CheckVersionWithoutSpecialChars(String usedVersion, String latestVersion, String latest) {
        if (usedVersion.contains(".")) {
            usedVersion = usedVersion.replace(".", "");
            CheckVersionWithoutSpecialChars(usedVersion, latestVersion, latest);
        } else if (usedVersion.contains("_")) {
            usedVersion = usedVersion.replace("_", "");
            CheckVersionWithoutSpecialChars(usedVersion, latestVersion, latest);
        } else if (usedVersion.contains(" ")) {
            usedVersion = usedVersion.replace(" ", "");
            CheckVersionWithoutSpecialChars(usedVersion, latestVersion, latest);
        } else if (latestVersion.contains(".")) {
            latestVersion = latestVersion.replace(".", "");
            CheckVersionWithoutSpecialChars(usedVersion, latestVersion, latest);
        } else if (latestVersion.contains("_")) {
            latestVersion = latestVersion.replace("_", "");
            CheckVersionWithoutSpecialChars(usedVersion, latestVersion, latest);
        } else if (latestVersion.contains(" ")) {
            latestVersion = latestVersion.replace(" ", "");
            CheckVersionWithoutSpecialChars(latestVersion, latestVersion, latest);
        } else {
            try {
                if (Integer.parseInt(usedVersion) < Integer.parseInt(latestVersion)) {
                    Logger.log("&f__________________________________");
                    Logger.log("&f");
                    Logger.log("              &aWild&f&lAPI");
                    Logger.log("&f");
                    Logger.log("&6      I founded new version!");
                    Logger.log("&f");
                    Logger.log("&6           This version");
                    Logger.log("&7              " + WildAPI.plugin.getDescription().getVersion());
                    Logger.log("&f");
                    Logger.log("&6              Latest");
                    Logger.log("&7              " + latest);
                    Logger.log("&f");
                    Logger.log("&f__________________________________");
                } else if (Integer.parseInt(usedVersion) > Integer.parseInt(latestVersion)) {
                    WildAPI.beta = true;
                    Logger.log("&f__________________________________");
                    Logger.log("&f");
                    Logger.log("              &aWild&f&lAPI");
                    Logger.log("&f");
                    Logger.log("&e   I checked and I know you are");
                    Logger.log("&e      using the beta version");
                    Logger.log("&f");
                    Logger.log("&f__________________________________");
                }
            } catch (Exception e) {
                Logger.log("&f__________________________________");
                Logger.log("&f");
                Logger.log("              &aWild&f&lAPI");
                Logger.log("&f");
                Logger.log("&4                Error");
                Logger.log("&4 " + e.getMessage());
                Logger.log("&f");
                Logger.log("&f__________________________________");
            }
        }
    }
}
