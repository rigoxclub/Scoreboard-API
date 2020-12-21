package club.rigox.scoreboard.utils;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static club.rigox.scoreboard.utils.Console.debug;

public class API {
    private final ScoreboardAPI scoreboardAPI;
    private Creator scoreboard;

    public API (ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
    }

    public void setScoreboard(Player player) {
        scoreboard = new Creator("&c&lRigox &7| &fVanilla");

        for (String line : scoreboardAPI.getSetting().getStringList("general.body")) {
            scoreboard.addRow(line);
        }

        scoreboard.finish();
        scoreboard.display(player);
    }


//    I SHOULDN'T DO THIS.  I SHOULDN'T DO THIS.  I SHOULDN'T DO THIS.  I SHOULDN'T DO THIS.  I SHOULDN'T DO THIS.
//    public void setNormalRows(Player player) {
//        scoreboard.addRow("");
//        playerLine = scoreboard.addRow(parseField("&fPlayer: &b%player_name%", player));
//        scoreboard.addRow("");
//        ranksLine = scoreboard.addRow(parseField("&fRank: &e%luckperms_primary_group_name%", player));
//        creditsLine = scoreboard.addRow(parseField("&fCredits: &e%cherry_credits%", player));
//        scoreboard.addRow("");
//        votePartyLine = scoreboard.addRow(parseField("&fVoteParty: &a6&7/30", player));
//        scoreboard.addRow("");
//        scoreboard.addRow(color("&crigox.club"));
//    }

    //    Creator.Row playerLine;
//    Creator.Row ranksLine;
//    Creator.Row creditsLine;
//    Creator.Row votePartyLine;
//    public Creator.Row getPlayerLine() {
//        return playerLine;
//    }
//
//    public Creator.Row getCreditsLine() {
//        return creditsLine;
//    }
}