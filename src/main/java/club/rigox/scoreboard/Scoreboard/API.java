package club.rigox.scoreboard.Scoreboard;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.util.Objects;

import static club.rigox.scoreboard.utils.Console.parseField;

public class API {
    private final ScoreboardAPI plugin;


    public API(ScoreboardAPI plugin) {
        this.plugin = plugin;
    }

    public ScoreboardCreator setScoreBoard(Player p, String type, boolean health) {

        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        ScoreboardCreator scoreboard = new ScoreboardCreator(randomString(8), health);

        scoreboard.setName(Objects.requireNonNull(plugin.getSetting().getString(type + ".title")));

        int i = plugin.getSetting().getStringList(type + ".body").size();
        for (String line : plugin.getSetting().getStringList(type + ".body")) {
            scoreboard.lines(i, parseField(line, p));
            i--;
        }

        p.setScoreboard(scoreboard.getScoreboard());

        return scoreboard;
    }

    private String randomString(int length) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}