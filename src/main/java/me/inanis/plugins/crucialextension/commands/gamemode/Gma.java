package me.inanis.plugins.crucialextension.commands.gamemode;

import me.inanis.plugins.crucialextension.CrucialExtension;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Gma implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                if (player.hasPermission("CrucialExtension.gamemode.adventure.self")) {
                    player.setGameMode(GameMode.ADVENTURE);
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                }
            } else if (player.hasPermission("CrucialExtension.gamemode.adventure.others")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    target.setGameMode(GameMode.ADVENTURE);
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "Player by that name could not be found");
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
            }
        } else {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.DARK_RED + "You can't change your gamemode");
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                target.setGameMode(GameMode.ADVENTURE);
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Player by that name could not be found");
            }
        }
        return true;
    }
}
