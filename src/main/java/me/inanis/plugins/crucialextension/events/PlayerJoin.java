package me.inanis.plugins.crucialextension.events;

import me.inanis.plugins.crucialextension.CrucialExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final CrucialExtension plugin;

    public PlayerJoin(CrucialExtension plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // join message
        Player player = e.getPlayer();
        String joinMessage = plugin.getConfig().getString("join-message");
        if (joinMessage != null) {
            joinMessage = joinMessage.replaceAll("\\{PLAYER}", player.getDisplayName());
            e.setJoinMessage(joinMessage);
        } else {
            e.setJoinMessage("");
        }


        // hide vanish players from new joiner
        for (int i = 0; i < plugin.invisible_list.size(); i++){
            player.hidePlayer(plugin, plugin.invisible_list.get(i));
        }

    }
}
