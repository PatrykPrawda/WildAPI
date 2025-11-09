package pl.wildlabs.wildapi.functions.display;

import javax.annotation.Nullable;
import pl.wildlabs.wildapi.plugin.Logger;
import org.bukkit.entity.Player;

class TitleAndSubtitle {
    static void DisplayTitle(Player player, String Title, @Nullable String SubTitle, int FadeIn, int Stay, int FadeOut) {
        if (Title != null && Title.contains("&"))
            Title = Title.replace("&", "§");
        if (SubTitle != null && SubTitle.contains("&"))
            SubTitle = SubTitle.replace("&", "§");
        player.sendTitle(Title, SubTitle, FadeIn, Stay, FadeOut);
        Logger.debugLog("Displaying title and subtitle for " + player.getDisplayName());
    }
}