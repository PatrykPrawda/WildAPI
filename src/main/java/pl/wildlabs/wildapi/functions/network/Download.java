package pl.wildlabs.wildapi.functions.network;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import pl.wildlabs.wildapi.plugin.Logger;

class Download {
    static void DownloadFile(String Url, String LocalFileName) {
        Logger.debugLog("Downloading file as " + LocalFileName + " from " + Url);
        try(BufferedInputStream in = new BufferedInputStream((new URL(Url)).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(LocalFileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException iOException) {}
    }
}