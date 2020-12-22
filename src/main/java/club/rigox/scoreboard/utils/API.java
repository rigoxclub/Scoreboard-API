package club.rigox.scoreboard.utils;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.entity.Player;

import static club.rigox.scoreboard.utils.Console.parseField;

/**
 * API Class
 */
public class API {
    private final ScoreboardAPI scoreboardAPI;
    private Creator scoreboard;

    /**
     * @param plugin Instance of ScoreboardAPI for API.
     */
    public API (ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
    }

    /**
     * Sets the scoreboard to a specified player
     * with a specified type.
     *
     * @param player The player to set the scoreboard on.
     * @param type Set the scoreboard to read from the settings.yml.
     */
    public void setScoreboard(Player player, String type) {
        scoreboard = new Creator(scoreboardAPI.getSetting().getString(type + ".title"));

        for (String line : scoreboardAPI.getSetting().getStringList(type + ".body")) {
            scoreboard.addRow(parseField(line, player));
        }

        scoreboard.finish();
        scoreboard.display(player);
    }

    /**
     *
     * @param line Search for the scoreboard line you want to update.
     * @param str Message to set on the specified line.
     * @param player Specify player to parse for PlaceholderAPI.
     */
    public void setLineMessage(int line, String str, Player player) {
        scoreboard.getRow(line).setMessage(parseField(str, player));
    }
}