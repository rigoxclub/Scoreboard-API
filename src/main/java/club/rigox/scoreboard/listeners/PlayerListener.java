package club.rigox.scoreboard.listeners;

import club.rigox.scoreboard.ScoreboardAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * PlayerListener Class
 */
public class PlayerListener implements Listener {
    private final ScoreboardAPI scoreboardAPI;

    /**
     * @param plugin Instance for Class
     */
    public PlayerListener(ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
        scoreboardAPI.getServer().getPluginManager().registerEvents(this, scoreboardAPI);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        scoreboardAPI.getAPI().setScoreboard(player, "general");
    }
}
