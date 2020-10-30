package me.inanis.plugins.crucialextension.events;

import me.inanis.plugins.crucialextension.formatting.ConfigVarFormatting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerLeave implements Listener {

    private Plugin plugin;

    public PlayerLeave(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String leaveMessage = plugin.getConfig().getString("leave-message");
        e.setQuitMessage(ConfigVarFormatting.parsePlayer(leaveMessage, player.getDisplayName()));
    }
}
