package pl.wildlabs.wildapi.plugin;

import java.util.Arrays;

import pl.wildlabs.wildapi.functions.display.Display;
import pl.wildlabs.wildapi.functions.yaml.Yaml;
import org.bukkit.plugin.java.JavaPlugin;
import pl.wildlabs.wildapi.plugin.commands.Commands;
import pl.wildlabs.wildapi.java.Tuple2;
import pl.wildlabs.wildapi.plugin.events.EntityDamageByEntity;

public class WildAPI extends JavaPlugin {
    public static WildAPI plugin;
    static Resources resources;

    static String tag = "&f[ &aWild&f&lAPI &f] ";

    public static String configTag = "WildAPI";
    public static String configFile = configTag + "/config";

    static boolean beta = false;
    static boolean downloadedNewVersion = false;
    static String prefix_debug = "&8[&c&lDEBUG&8] &7";

    static boolean debug_enabled = false;

    private static final Tuple2<String, String>[] yamlConfigs = new Tuple2[] {
            new Tuple2<String, String>("WildAPI.enable.updater.check",                      "true"),
            new Tuple2<String, String>("WildAPI.enable.updater.update",                     "true"),
            new Tuple2<String, String>("WildAPI.enable.network.downloading_jars",           "true"),
            new Tuple2<String, String>("WildAPI.enable.network.get_text_from_url",          "true"),
            new Tuple2<String, String>("WildAPI.enable.display.action_bars",                "true"),
            new Tuple2<String, String>("WildAPI.enable.display.boss_bars",                  "true"),
            new Tuple2<String, String>("WildAPI.enable.display.title_and_subtitle",         "true"),
            new Tuple2<String, String>("WildAPI.enable.display.holograms",                  "true"),
            new Tuple2<String, String>("WildAPI.enable.options.debugging",                  "false"),
            new Tuple2<String, String>("WildAPI.options.holograms.max-holograms-per-chunk", "10")
    };

    private static final Tuple2<String, Boolean>[] pluginResources = new Tuple2[] {
            new Tuple2<String, Boolean>("lang/en.yml", true)
    };

    public void onLoad() {
        plugin = this;
    }

    public void onEnable() {
        if (plugin == null) plugin = this;

        Logger.log("Plugin is set: " + plugin);

        Logger.log(tag + "&aEnabling API...");

        //resources.checkResources();

        Yaml configYaml = new Yaml(configFile);

        if (!configYaml.isExist()) {
            configYaml.createFile();

            Arrays.stream(yamlConfigs).forEach(yamlConfig -> configYaml.setValue(yamlConfig.first, yamlConfig.second));

        } else {

            for(Tuple2<String, String> yamlConfig : yamlConfigs) {
                if(configYaml.getStringValue(yamlConfig.first) == null) configYaml.setValue(yamlConfig.first, yamlConfig.second);
            }

        }
        if (plugin != null) {
            Display.loadAllHolograms(plugin);
            debug_enabled = configYaml.getBooleanValue("WildAPI.enable.options.debugging");
        }

        Enchants enchants = new Enchants();

        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(enchants), this);

        getCommand("wildapi").setExecutor(new Commands());

        Updater updater = new Updater();
        updater.CheckVersion();
    }

    public void onDisable() {
        if (plugin != null) {
            Display.removeAllBossBars(plugin);
            Display.removeAllHolograms(plugin);
        }
        Logger.log(tag + "&7Disabling API...");
        Logger.log(tag + "&7API disabled.");
    }
}
