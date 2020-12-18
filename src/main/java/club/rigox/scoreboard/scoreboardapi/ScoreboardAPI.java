package club.rigox.scoreboard.scoreboardapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class ScoreboardAPI extends JavaPlugin {
    public static ScoreboardAPI instance;

    @Override
    public void onEnable() {
        instance = this;

    }
}
