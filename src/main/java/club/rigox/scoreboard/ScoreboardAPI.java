package club.rigox.scoreboard;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static club.rigox.scoreboard.utils.Console.debug;
import static club.rigox.scoreboard.utils.Console.warn;

import java.io.File;
import java.io.IOException;

public final class ScoreboardAPI extends JavaPlugin {
    public static ScoreboardAPI instance;

    @Override
    public void onEnable() {
        instance = this;

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
