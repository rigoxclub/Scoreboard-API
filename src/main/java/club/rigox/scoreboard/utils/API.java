package club.rigox.scoreboard.utils;

import org.bukkit.entity.Player;

import static club.rigox.scoreboard.utils.Console.color;
import static club.rigox.scoreboard.utils.Console.parseField;

public class API {
    private Creator scoreboard;

    Creator.Row playerLine;
    Creator.Row ranksLine;
    Creator.Row creditsLine;
    Creator.Row votePartyLine;

    public void setScoreboard(Player player) {
        scoreboard = new Creator("&c&lRigox &7| &fVanilla");

        setNormalRows(player);

        scoreboard.finish();
        scoreboard.display(player);
    }

    public void setNormalRows(Player player) {
        scoreboard.addRow("");
        playerLine = scoreboard.addRow(parseField("&fPlayer: &b%player_name%", player));
        scoreboard.addRow("");
        ranksLine = scoreboard.addRow(parseField("&fRank: &e%luckperms_primary_group_name%", player));
        creditsLine = scoreboard.addRow(parseField("&fCredits: &e%cherry_credits%", player));
        scoreboard.addRow("");
        votePartyLine = scoreboard.addRow(parseField("&fVoteParty: &a6&7/30", player));
        scoreboard.addRow("");
        scoreboard.addRow(color("&crigox.club"));
    }

    public Creator.Row getPlayerLine() {
        return playerLine;
    }

    public Creator.Row getCreditsLine() {
        return creditsLine;
    }
}