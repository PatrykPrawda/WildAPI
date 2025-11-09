package pl.wildlabs.wildapi.functions.translation;

import pl.wildlabs.wildapi.functions.annotation.NotImplementedYet;

public class Translation {
    @NotImplementedYet
    public static String translateIdToName(String idOrName, TranslateTo translateTo) {
        return null;
    }

    public static boolean useIds() {
        return UseIds.useIds();
    }

    public enum TranslateTo {
        NAME,
        ID
    }
}
