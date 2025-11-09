package pl.wildlabs.wildapi.functions.file;

public class File {
    public static boolean fileExist(String localFileName) {
        return BooleanFileExist.FileExist(localFileName);
    }

    public static boolean deleteFile(String path) {
        return DeleteFile.deleteFile(path);
    }

    public static boolean createFile(String path) {
        return CreateFile.createFile(path);
    }
}