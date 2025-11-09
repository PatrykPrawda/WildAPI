package pl.wildlabs.wildapi.functions.yaml;

class GetPath {
    static String getFullPath(String fileName) {
        return "plugins_config/" + fileName + ".yml";
    }

    static String getFullDirectoryPath(String fileName) {
        String path = "plugins_config/" + fileName + ".yml";

        String[] splittedPath = path.split("/");

        StringBuilder output = new StringBuilder();

        for(int i = 0; i < splittedPath.length; i++) {
            if(i == splittedPath.length - 1) {
                return output.toString();
            }

            output.append(splittedPath[i]);
        }

        return output.toString();
    }
}
