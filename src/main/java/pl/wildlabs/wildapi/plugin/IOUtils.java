package pl.wildlabs.wildapi.plugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class IOUtils {
    public static String getContent(String s) {
        String body = null;
        try {
            URL url = new URL(s);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = (encoding == null) ? "UTF-8" : encoding;
            body = toString(in, encoding);
        } catch (Exception e) {
            Logger.log("&f__________________________________");
            Logger.log("&f");
            Logger.log("              &cIO&8&lUtils");
            Logger.log("&f");
            Logger.log("&4        Error while loading:");
            Logger.log("&4 " + e.getMessage());
            Logger.log("&f");
            Logger.log("&f__________________________________");
        }
        return body;
    }

    public static void downloadResource(String url, String localFileName) {
        File path = new File(localFileName);

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(path)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            System.out.println("Download completed successfully!");
        } catch (IOException e) {
            System.err.println("Error downloading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String toString(InputStream in, String encoding) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return baos.toString(encoding);
    }
}
