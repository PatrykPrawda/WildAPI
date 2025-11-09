package pl.wildlabs.wildapi.functions.server;

public class Server {
    public static String getServerGameVersion() {
        return ServerGameVersion.getServerVersion();
    }

    public static String[] getSplittedServerGameVersion() {
        return ServerGameVersion.getSplittedServerVersion();
    }

    public static int getIntServerGameVersion() {
        return ServerGameVersion.getIntServerVersion();
    }
}
