package club.rigox.scoreboard;

import club.rigox.scoreboard.listeners.CMIListener;
import club.rigox.scoreboard.listeners.PlayerListener;
import club.rigox.scoreboard.utils.API;
import com.Zrips.CMI.CMI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static club.rigox.scoreboard.utils.Console.*;

/**
 * ScoreboardAPI Class
 */
public final class ScoreboardAPI extends JavaPlugin {
    /**
     * Plugin instance.
     */
    public static ScoreboardAPI instance;

    private FileConfiguration setting;

    private API API;

    @Override
    public void onEnable() {
        instance = this;

        this.API = new API(this);
        this.setting = createSetting();

        loadHooks();
        registerListeners();

        info("ScoreboardAPI has been enabled!");
    }

    /**
     * Gets the scoreboard config.
     * @return settings.yml configuration
     */
    public FileConfiguration getSetting() {
        return setting;
    }

    /**
     * Gets the API class to call on other class/plugin.
     * @return API Class
     */
    public API getAPI() {
        return API;
    }

    /**
     * Simple FileConfiguration system for settings.yml
     * @return settings.yml creation/load
     */
    public FileConfiguration createSetting() {
        File configFile = new File(getDataFolder(), "settings.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource( "settings.yml", false);
        }

        FileConfiguration cfg = new YamlConfiguration();
        try {
            cfg.load(configFile);
            info("settings.yml has been loaded!");
        } catch (IOException | InvalidConfigurationException e) {
            warn(String.format("A error occurred while copying the settings.yml to the plugin data folder. Error: %s", e));
            e.printStackTrace();
        }
        return cfg;
    }

    /**
     * Method to load Hooks required by the plugin.
     */
    public void loadHooks() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            warn("Could not find PlaceholderAPI! This plugin is required.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        info("Successfully hooked with PlaceholderAPI!");

        if (getServer().getPluginManager().getPlugin("CMI") == null || getServer().getPluginManager().getPlugin("CMIEInjector") == null) {
            warn("Could not find CMI or CMIInjector! Please add it.");
            return;
        }
        info("Successfully hooked with CMI!");
    }

    /**
     * Method to register Listeners available in the plugin.
     */
    public void registerListeners() {
        new PlayerListener(this);
        new CMIListener(this);
        info("Listeners has been registered!");
    }
}
