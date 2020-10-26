package me.inanis.plugins.crucialextension.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("CrucialExtension.fly")){
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage("You are no longer flying");
                } else {
                    player.setAllowFlight(true);
                    player.sendMessage("You are now flying");
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
            }
        } else {
            sender.sendMessage("Not possible mate, you're the console");
        }
        return true;
    }
}
