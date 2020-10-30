package me.inanis.plugins.crucialextension.events;

import me.inanis.plugins.crucialextension.CrucialExtension;
import me.inanis.plugins.crucialextension.formatting.ConfigVarFormatting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    private CrucialExtension plugin;

    public PlayerMove(CrucialExtension plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!player.hasPermission("testPlugin.move")){
            String moveMessage = plugin.getConfig().getString("no-move-message");
            player.sendMessage(ConfigVarFormatting.parsePlayer(moveMessage, player.getDisplayName()));
            e.setCancelled(true);
        }
    }
}
