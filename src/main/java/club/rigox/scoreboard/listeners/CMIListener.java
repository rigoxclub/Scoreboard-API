package club.rigox.scoreboard.listeners;

import club.rigox.scoreboard.ScoreboardAPI;
import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static club.rigox.scoreboard.utils.Console.debug;

public class CMIListener implements Listener {
    private final ScoreboardAPI scoreboardAPI;

    public CMIListener(ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
        scoreboardAPI.getServer().getPluginManager().registerEvents(this, scoreboardAPI);
    }

    @EventHandler
    public void onUserBalanceChange(CMIUserBalanceChangeEvent e) {
        debug(String.format("Player %s scoreboard has been updated by CMIUserBalanceChange Event.", e.getUser().getPlayer()));
        scoreboardAPI.getAPI().setLineMessage(4, "&fCredits: &e%cherry_credits%", e.getUser().getPlayer());
    }
}
