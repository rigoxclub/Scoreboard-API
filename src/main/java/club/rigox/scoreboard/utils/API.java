package club.rigox.scoreboard.utils;

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

    public void setScoreBoard(Player p, String type) {

        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        Creator scoreboard = new Creator(randomString(8));

        scoreboard.setName(Objects.requireNonNull(plugin.getSetting().getString(type + ".title")));

        int i = plugin.getSetting().getStringList(type + ".body").size();
        for (String line : plugin.getSetting().getStringList(type + ".body")) {
            scoreboard.lines(i, parseField(line, p));
            i--;
        }
        p.setScoreboard(scoreboard.getScoreboard());
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