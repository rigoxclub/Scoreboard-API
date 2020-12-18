package club.rigox.scoreboard.listeners;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static club.rigox.scoreboard.utils.Console.debug;

public class PlayerListener implements Listener {
    private final ScoreboardAPI scoreboardAPI;

    public PlayerListener(ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
        scoreboardAPI.getServer().getPluginManager().registerEvents(this, scoreboardAPI);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        debug(String.format("Scoreboard for %s set on join!", player));
        scoreboardAPI.getScoreboard().setScoreBoard(player, "general", true);
    }
}
