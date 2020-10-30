package me.inanis.plugins.crucialextension.commands.chat;

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
import java.util.Arrays;
import java.util.List;

public class Whisper implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage(ChatColor.DARK_RED + "You need to provide a player and a message");
                return false;
            }
            if (!player.hasPermission("CrucialExtension.whisper")) {
                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.DARK_RED + "Player by that name could not be found");
                return true;
            }
            String[] splitMessage = Arrays.copyOfRange(args, 1, args.length);
            target.sendMessage(ChatColor.GRAY + "Whisper from " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + String.join(" ", splitMessage));
            player.sendMessage(ChatColor.GRAY + "Whisper to " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + String.join(" ", splitMessage));

        } else {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.DARK_RED + "You need to provide a player and a message");
                return false;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.DARK_RED + "Player by that name could not be found");
                return true;
            }
            String[] splitMessage = Arrays.copyOfRange(args, 1, args.length);
            target.sendMessage(ChatColor.GRAY + "Whisper from " + ChatColor.AQUA + "Console" + ChatColor.GRAY + ": " + ChatColor.WHITE + String.join(" ", splitMessage));
            sender.sendMessage(ChatColor.GRAY + "Whisper to " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + String.join(" ", splitMessage));
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
