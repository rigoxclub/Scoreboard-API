package club.rigox.scoreboard.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static club.rigox.scoreboard.utils.Console.color;
import static club.rigox.scoreboard.utils.Console.warn;

/**
 * Creator Class
 */
public class Creator {

    private final Scoreboard bukkitScoreboard;
    private final Objective obj;
    private final List<Row> rowCache = new ArrayList<>();

    private boolean finished = false;

    String score_name;

    /**
     * Base for creating Scoreboards.
     * @param score_name Sets the title of the Scoreboard
     */
    public Creator(String score_name) {
        this.score_name = score_name;

        this.bukkitScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.obj = this.bukkitScoreboard.registerNewObjective(randomString(), "dummy", "test");

        this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.obj.setDisplayName(color(score_name));
    }

    private String randomString() {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 8; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    /**
     * Sets the scoreboard once it's marked as finished.
     * @param player Target to set the scoreboard on.
     */
    public void display (Player player) {
        player.setScoreboard(this.bukkitScoreboard);
    }

    /**
     * @param message Set scoreboard lines.
     */
    public void addRow(String message) {
        if (this.finished) {
            warn("Can't add rows since the scoreboard it's marked as finished.");
            return;
        }
        final Row row = new Row(this, message);
        this.rowCache.add(row);
    }

    /**
     * Marks the scoreboard as finished so
     * it won't be available to add more rows.
     */
    public void finish() {
        if (this.finished) {
            warn("Can't finish the scoreboard since it is already finished.");
            return;
        }

        this.finished = true;

        for (int i = rowCache.size() - 1; i >= 0; i--){
            final Row row = rowCache.get(i);
            final Team team = this.bukkitScoreboard.registerNewTeam("dummy.test." + (i+1));

            team.addEntry(ChatColor.values()[i] + "");

            this.obj.getScore(ChatColor.values()[i] + "").setScore(rowCache.size()-i);

            row.team = team;
            row.setMessage(row.message);
        }
    }

    /**
     * Works for modifying scoreboard lines
     * on other plugins instead of calling the
     * setScoreboard method.
     * This prevents scoreboard flickering.
     *
     * @param line Number to index on rowCache.
     * @return Scoreboard line to alter.
     */
    public Row getRow (int line) {
        return rowCache.get(line);
    }

    /**
     * Row Class
     */
    public class Row {
        private final Creator scoreboard;
        private Team team;
        private String message;

        /**
         * @param sb Scoreboard
         * @param message Message
         */
        public Row (Creator sb, String message) {
            this.scoreboard = sb;
            this.message = message;
        }

        /**
         * @param message Sets the message for a row
         */
        public void setMessage(String message) {
            this.message = message;

            if (scoreboard.finished) {
                final String[] parts = splitStringWithChatcolorInHalf (message);

                this.team.setPrefix(parts[0]);
                this.team.setSuffix(parts[1]);
            }
        }
    }

    private String[] splitStringWithChatcolorInHalf (String str) {
        final String[] strs = new String[2];

        ChatColor cc1 = ChatColor.WHITE, cc2 = null;
        Character lastChar = null;

        strs[0] = "";
        for (int i = 0; i <str.length() / 2; i++){
            final char c = str.charAt(i);
            if (lastChar != null) {
                final ChatColor cc = charsToChatColor(new char[] { lastChar, c });
                if (cc != null) {
                    if (cc.isFormat())
                        cc2 = cc;
                    else {
                        cc1 = cc;
                        cc2 = null;
                    }
                }
            }
            strs[0] += c;
            lastChar = c;
        }
        strs[1] = cc1 + "" + (cc2 != null ? cc2 : "") + str.substring(str.length()/2);
        return strs;
    }

    private ChatColor charsToChatColor (char[] chars) {
        for (ChatColor cc:ChatColor.values()) {
            final char[] ccChars = cc.toString().toCharArray();
            int same = 0;
            for (int i = 0; i < 2; i++){
                if (ccChars[i] == chars[i]) same++;
            }
            if(same == 2) return cc;
        }

        return null;
    }
}