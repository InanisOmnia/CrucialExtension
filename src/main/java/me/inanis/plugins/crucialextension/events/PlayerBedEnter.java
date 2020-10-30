package me.inanis.plugins.crucialextension.events;

import me.inanis.plugins.crucialextension.formatting.ConfigVarFormatting;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;

public class PlayerBedEnter implements Listener {

    private final Plugin plugin;

    public PlayerBedEnter(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        if (plugin.getConfig().getBoolean("one-player-sleep")) {
            if (plugin.getServer().getWorld("world").getTime() >= 13000) {
                String playerSleepMessage = plugin.getConfig().getString("one-player-sleep-message");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage(ConfigVarFormatting.parsePlayer(playerSleepMessage, player.getDisplayName()));
                }
                for (World world : plugin.getServer().getWorlds()) {
                    world.setTime(0L);
                }
                e.setCancelled(true);
            }
        }
    }
}
