package club.rigox.scoreboard.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class API {
    private final API plugin;


    public API(API plugin) {
        this.plugin = plugin;
    }

    public ScoreboardCreator setScoreBoard(Player p, String type, boolean health) {

        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        ScoreboardCreator scoreboard = new ScoreboardCreator(randomString(8), health);

        scoreboard.setName(Objects.requireNonNull(plugin.getScoreboard().getString(type + ".title")));

        int i = plugin.getScoreboard().getStringList(type + ".body").size();
        for (String line : plugin.getScoreboard().getStringList(type + ".body")) {
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