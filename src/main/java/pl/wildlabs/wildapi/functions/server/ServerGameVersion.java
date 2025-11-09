package pl.wildlabs.wildapi.functions.server;

import org.bukkit.Bukkit;

class ServerGameVersion {
    static String getServerVersion() {
        return Bukkit.getVersion();
    }

    static String[] getSplittedServerVersion() {
        return Bukkit.getVersion().split("\\.");
    }

    static int getIntServerVersion() {
        StringBuilder intVersion = new StringBuilder();

        String[] splittedVersion = getSplittedServerVersion();

        for(int i = 0; i < splittedVersion.length; i++) {
            intVersion.append(splittedVersion[i]);
        }

        return Integer.parseInt(intVersion.toString());
    }
}
