package club.rigox.scoreboard;

import club.rigox.scoreboard.listeners.PlayerListener;
import club.rigox.scoreboard.utils.API;
import club.rigox.scoreboard.utils.Creator;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static club.rigox.scoreboard.utils.Console.*;

public final class ScoreboardAPI extends JavaPlugin {
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

    public FileConfiguration getSetting() {
        return setting;
    }

    public API getAPI() {
        return API;
    }

    public FileConfiguration createSetting() {
        File configFile = new File(getDataFolder(), "settings.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource( "settings.yml", false);
            debug("settings.yml has been created!");
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

    public void loadHooks() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            warn("Could not find PlaceholderAPI! This plugin is required.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        info("Successfully hooked with PlaceholderAPI!");
    }

    public void registerListeners() {
        new PlayerListener(this);
        info("Listeners has been registered!");
    }
}
