package club.rigox.scoreboard.listeners;

import club.rigox.scoreboard.ScoreboardAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.entity.Player;

import static club.rigox.scoreboard.utils.Console.color;

public class LuckPermsListener {
    private final ScoreboardAPI scoreboardAPI;
    private final LuckPerms luckPerms;

    public LuckPermsListener(ScoreboardAPI plugin, LuckPerms luckPerms) {
        this.scoreboardAPI = plugin;
        this.luckPerms = luckPerms;
    }

    public void register() {
        EventBus eventBus = this.luckPerms.getEventBus();
        eventBus.subscribe(this.scoreboardAPI, NodeAddEvent.class, this::onNodeAdd);
        eventBus.subscribe(this.scoreboardAPI, NodeRemoveEvent.class, this::onNodeRemove);
    }

    private void onNodeAdd(NodeAddEvent e) {
        if (!e.isUser()) {
            return;
        }

        User target = (User) e.getTarget();
        Node node = e.getNode();

        scoreboardAPI.getServer().getScheduler().runTask(scoreboardAPI, () -> {
            Player player = scoreboardAPI.getServer().getPlayer(target.getUniqueId());
            if (player == null) {
                return; // Player not online.
            }

            if (node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(color(String.format("&8&l* &f¡Se te otorgó el rango &b%s&f!", groupName)));
                scoreboardAPI.getAPI().setLineMessage(3, "&fRango: &e%luckperms_primary_group_name%", player);
            }
        });
    }

    private void onNodeRemove(NodeRemoveEvent e) {
        if (!e.isUser()) {
            return;
        }

        User target = (User) e.getTarget();
        Node node = e.getNode();

        scoreboardAPI.getServer().getScheduler().runTask(scoreboardAPI, () -> {
            Player player = scoreboardAPI.getServer().getPlayer(target.getUniqueId());
            if (player == null) {
                return; // Player not online.
            }

            if (node instanceof InheritanceNode) {
                String groupName = ((InheritanceNode) node).getGroupName();
                player.sendMessage(color(String.format("&8&l* &fEl rango &b%s &fse te removió.", groupName)));
                scoreboardAPI.getAPI().setLineMessage(3, "&fRango: &e%luckperms_primary_group_name%", player);
            }
        });
    }

}
