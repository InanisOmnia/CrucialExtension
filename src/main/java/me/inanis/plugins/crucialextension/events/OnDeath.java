package me.inanis.plugins.crucialextension.events;

import me.inanis.plugins.crucialextension.formatting.ConfigVarFormatting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class OnDeath implements Listener {

    private Plugin plugin;

    public OnDeath(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();

        String playerMessage = plugin.getConfig().getString("player-death-message");
        player.sendMessage(ConfigVarFormatting.parsePlayer(playerMessage, player.getDisplayName()));

        String globalMessage = plugin.getConfig().getString("global-death-message");
        player.sendMessage(ConfigVarFormatting.parsePlayer(globalMessage, player.getDisplayName()));
    }
}
