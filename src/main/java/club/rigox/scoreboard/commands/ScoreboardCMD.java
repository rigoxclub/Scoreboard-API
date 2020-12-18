package club.rigox.scoreboard.commands;

import club.rigox.scoreboard.ScoreboardAPI;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static club.rigox.scoreboard.utils.Console.color;
import static club.rigox.scoreboard.utils.Console.debug;

@CommandAlias("scoreboard")
public class ScoreboardCMD extends BaseCommand {
    private final ScoreboardAPI scoreboardAPI;

    public ScoreboardCMD (ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
    }

    @Default
    @HelpCommand
    public void helpCmd(CommandSender sender) {
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&b&lScoreboard API"));
        sender.sendMessage(color("&7&oCommand Help"));
        sender.sendMessage(color("&7&m------------------------------------------------"));
        sender.sendMessage(color("&8&l* &b/scoreboard reload &f- Reload scoreboard configuration."));
        sender.sendMessage(color("&7&m------------------------------------------------"));
    }

    @Subcommand("reload|r")
    @CommandPermission("scoreboard.reload")
    public void reloadCmd(CommandSender sender) {

        // TODO KO RELOAD NDOIKOI KO PUERQUESA QUE LOCO
        // TODO KO RELOAD NDOIKOI KO PUERQUESA QUE LOCO
        // PYAROOOOOOOOOOOOOOOOOOOO
        // TODO KO RELOAD NDOIKOI KO PUERQUESA QUE LOCO
        // PYAROOOOOOOOOOOOOOOOOOOO
        // TODO KO RELOAD NDOIKOI KO PUERQUESA QUE LOCO
        // PYAROOOOOOOOOOOOOOOOOOOO

//        scoreboardAPI.reloadSetting();
        scoreboardAPI.createSetting();

        for (Player player : scoreboardAPI.getServer().getOnlinePlayers()) {
            scoreboardAPI.getScoreboard().setScoreBoard(player, "general");
            debug(String.format("Ok boomer, %s.", player.getName()));
        }

        sender.sendMessage(color("&aScoreboard settings has been reloaded!"));
    }
}
