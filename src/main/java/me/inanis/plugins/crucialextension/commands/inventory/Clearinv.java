package me.inanis.plugins.crucialextension.commands.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Clearinv implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (!player.hasPermission("CrucialExtension.inventory.clear.self")) {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                    return false;
                }
                player.getInventory().clear();
                return false;
            }
            if (args.length == 1) {
                if (!player.hasPermission("CrucialExtension.inventory.clear.others")) {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                    return false;
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.DARK_RED + "Player by that name could not be found");
                    return false;
                }
                if (target.hasPermission("crucialExtension.inventory.clear.immune")) {
                    player.sendMessage(ChatColor.DARK_RED + "This person is immune to inventory clears");
                    return false;
                }
                target.getInventory().clear();
                // TODO: get this as config message
                player.sendMessage("Cleared " + target.getDisplayName() + "'s inventory");
                // TODO: get this as config message
                target.sendMessage("Your inventory was cleared by " + player.getDisplayName());
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot clear your own inventory");
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.DARK_RED + "Player by that name could not be found");
                    return false;
                }
                if (target.hasPermission("crucialExtension.inventory.clear.immune")) {
                    sender.sendMessage(ChatColor.DARK_RED + "This person is immune to inventory clears");
                    return false;
                }
                target.getInventory().clear();
                // TODO: get this as config message
                sender.sendMessage("Cleared " + target.getDisplayName() + "'s inventory");
                // TODO: get this as config message
                target.sendMessage("Your inventory was cleared by CONSOLE");
            }
        }
        return true;
    }
}
