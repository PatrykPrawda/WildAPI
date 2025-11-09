package pl.wildlabs.wildapi.functions.network;

import java.util.Objects;
import java.util.Scanner;
import pl.wildlabs.wildapi.plugin.IOUtils;
import pl.wildlabs.wildapi.plugin.Logger;
import pl.wildlabs.wildapi.plugin.WildAPI;
import pl.wildlabs.wildapi.functions.yaml.Yaml;
import pl.wildlabs.wildapi.functions.experimental.Functions;
import org.bukkit.Bukkit;

public class Network {

    public static String getStringFromUrl(String url) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.network.get_text_from_url")) {
            if (url != null) {
                if (!url.isEmpty())
                    return IOUtils.getContent(url);
                return null;
            }
            return null;
        }
        return "§cThis function is disabled.";
    }

    public static String findStringUsingUrl(String url, String mustContains) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.network.get_text_from_url")) {
            Scanner scanner = new Scanner(Objects.<String>requireNonNull(getStringFromUrl(url)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(mustContains))
                    return Functions.removeTabsAndSpaces(line.replace(mustContains, ""));
            }
            scanner.close();
            return null;
        }
        return "§cThis function is disabled.";
    }

    public static void downloadJarFile(String url, String localFileName) {
        if (new Yaml(WildAPI.configFile).getBooleanValue(WildAPI.configTag + ".enable.network.downloading_jars")) {
            if (url != null && localFileName != null && !url.isEmpty() && !localFileName.isEmpty()) {
                localFileName = WildAPI.plugin.getDataFolder().getParentFile() + "/" + localFileName + ".jar";
                Bukkit.getConsoleSender().sendMessage(WildAPI.configTag + " - " + url + ", " + localFileName);
                Download.DownloadFile(url, localFileName);
            }
        } else {
            Logger.log("&cThis function is disabled.");
        }
    }
}
