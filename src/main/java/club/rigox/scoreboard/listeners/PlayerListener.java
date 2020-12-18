package club.rigox.scoreboard.listeners;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Player implements Listener {
    private ScoreboardAPI scoreboardAPI;

    public Player (ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = (Player) e.getPlayer();
    }
}
