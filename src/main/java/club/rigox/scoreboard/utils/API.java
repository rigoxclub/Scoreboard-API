package club.rigox.scoreboard.utils;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.entity.Player;

import static club.rigox.scoreboard.utils.Console.parseField;

public class API {
    private final ScoreboardAPI scoreboardAPI;
    private Creator scoreboard;

    public API (ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
    }

    public void setScoreboard(Player player, String type) {
        scoreboard = new Creator(scoreboardAPI.getSetting().getString(type + ".title"));

        for (String line : scoreboardAPI.getSetting().getStringList(type + ".body")) {
            scoreboard.addRow(parseField(line, player));
        }

        scoreboard.finish();
        scoreboard.display(player);
    }

}