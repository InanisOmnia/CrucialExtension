package me.inanis.plugins.crucialextension.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Feed implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                if (player.hasPermission("CrucialExtension.feed.self")){
                    player.setFoodLevel(20);
                    player.sendMessage("Thou hast been saturated!");
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                }
            } else {
                if (player.hasPermission("CrucialExtension.feed.others")){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        target.setFoodLevel(20);
                        target.sendMessage("Thou hast been saturated");
                        player.sendMessage("Gave " + target.getDisplayName() + " full hunger");
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
                if (target != null) {
                    target.setFoodLevel(20);
                    target.sendMessage("Thou hast been saturated");
                    sender.sendMessage("Gave " + target.getDisplayName() + " full hunger");
                } else {
                    sender.sendMessage("Player by that name could not be found");
                }
            }

        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()){
                players.add(p.getName());
            }
            return players;
        }
        return null;
    }
}
