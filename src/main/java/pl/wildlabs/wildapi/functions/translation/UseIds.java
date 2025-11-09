package pl.wildlabs.wildapi.functions.translation;

import pl.wildlabs.wildapi.functions.server.Server;

class UseIds {
    static boolean useIds() {
        String[] splittedVersion = Server.getSplittedServerGameVersion();

        if(splittedVersion.length < 2) {
            return true;
        }

        if(Integer.parseInt(splittedVersion[0]) > 1 || Integer.parseInt(splittedVersion[1]) > 12) {
            return false;
        } else {
            return true;
        }
    }
}
