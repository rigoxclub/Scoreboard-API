package club.rigox.scoreboard.listeners;

import club.rigox.scoreboard.ScoreboardAPI;
import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CMIListener implements Listener {
    private final ScoreboardAPI scoreboardAPI;

    public CMIListener(ScoreboardAPI plugin) {
        this.scoreboardAPI = plugin;
        scoreboardAPI.getServer().getPluginManager().registerEvents(this, scoreboardAPI);
    }

    @EventHandler
    public void onUserBalanceChange(CMIUserBalanceChangeEvent e) {
        scoreboardAPI.getAPI().setLineMessage(4, "&fCredits: &e%vault_eco_balance_formatted%", e.getUser().getPlayer());
    }
}
