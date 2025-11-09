package pl.wildlabs.wildapi.functions.translation;

class GetTranslation {
    static String getNameById(String idOrName, Translation.TranslateTo translateTo) {
        String id = null;
        String subId = null;

        if(idOrName.contains(":")) {
            id = idOrName.split(":")[0];
            subId = idOrName.split(":")[1];
        } else {
            id = idOrName;
        }

        if(id == null) {
            return "AIR";
        }

        // Pobieranie nazwy po ID
        return "AIR";
    }
}
