package me.inanis.plugins.crucialextension.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                if (player.hasPermission("CrucialExtension.heal.self")){
                    player.setHealth(20);
                    player.sendMessage("Thou hast been healed!");
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                }
            } else {
                if (player.hasPermission("CrucialExtension.heal.others")){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target instanceof Player) {
                        target.setHealth(20);
                        target.sendMessage("Thou hast been healed");
                        player.sendMessage("Gave " + target.getDisplayName() + " full health");
                    } else {
                        player.sendMessage("Player by that name could not be found");
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                }
            }

        } else {
            if (args.length < 1) {
                sender.sendMessage("Not possible mate, you're the console");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target instanceof Player) {
                    target.setFoodLevel(20);
                    target.sendMessage("Thou hast been healed");
                    sender.sendMessage("Gave " + target.getDisplayName() + " full health");
                } else {
                    sender.sendMessage("Player by that name could not be found");
                }
            }

        }

        return true;
    }
}
