package club.rigox.scoreboard;

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

    @Override
    public void onEnable() {
        instance = this;

        this.setting = createSetting();
        info("ScoreboardAPI has been enabled!");
    }

    public FileConfiguration getSetting() {
        return setting;
    }

    public FileConfiguration createSetting() {
        File configFile = new File(getDataFolder(), "settings.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource( "settings.yml", false);
            debug("settings.yml");
        }

        FileConfiguration cfg = new YamlConfiguration();
        try {
            cfg.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            warn(String.format("A error occurred while copying the settings.yml to the plugin data folder. Error: %s", e));
            e.printStackTrace();
        }
        return cfg;
    }
}
