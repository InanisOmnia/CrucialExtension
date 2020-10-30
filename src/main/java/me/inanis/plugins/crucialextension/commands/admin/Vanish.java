package me.inanis.plugins.crucialextension.commands.admin;

import me.inanis.plugins.crucialextension.CrucialExtension;
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

public class Vanish implements CommandExecutor, TabCompleter {

    private final CrucialExtension plugin;

    public Vanish(CrucialExtension plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                if (player.hasPermission("CrucialExtension.vanish.self")) {
                    if (plugin.invisible_list.contains(player)) {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            people.showPlayer(plugin, player);
                        }
                        plugin.invisible_list.remove(player);
                        player.sendMessage("You are now visible to other players on the server.");
                    } else if (!plugin.invisible_list.contains(player)) {
                        for (Player people : Bukkit.getOnlinePlayers()) {
                            people.hidePlayer(plugin, player);
                        }
                        plugin.invisible_list.add(player);
                        player.sendMessage("You are now invisible!");
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                }
            } else {
                if (player.hasPermission("CrucialExtension.vanish.others")) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (plugin.invisible_list.contains(target)) {
                            for (Player people : Bukkit.getOnlinePlayers()) {
                                people.showPlayer(plugin, target);
                            }
                            plugin.invisible_list.remove(target);
                            target.sendMessage("You are now visible to other players on the server.");
                            player.sendMessage("You made " + target.getDisplayName() + " visible to other players on the server.");
                        } else if (!plugin.invisible_list.contains(target)) {
                            for (Player people : Bukkit.getOnlinePlayers()) {
                                people.hidePlayer(plugin, target);
                            }
                            plugin.invisible_list.add(target);
                            target.sendMessage("You are now invisible!");
                            player.sendMessage("You made " + target.getDisplayName() + " invisible to other players on the server.");
                        }
                    } else {
                        player.sendMessage("Player by that name could not be found");
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                }
            }

        } else {
            if (args.length > 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (plugin.invisible_list.contains(target)) {
                            for (Player people : Bukkit.getOnlinePlayers()) {
                                people.showPlayer(plugin, target);
                            }
                            plugin.invisible_list.remove(target);
                            target.sendMessage("You are now visible to other players on the server.");
                            sender.sendMessage("You made " + target.getDisplayName() + " visible to other players on the server.");
                        } else if (!plugin.invisible_list.contains(target)) {
                            for (Player people : Bukkit.getOnlinePlayers()) {
                                people.hidePlayer(plugin, target);
                            }
                            plugin.invisible_list.add(target);
                            target.sendMessage("You are now invisible!");
                            sender.sendMessage("You made " + target.getDisplayName() + " invisible to other players on the server.");
                        }
                    } else {
                        sender.sendMessage("Player by that name could not be found");
                    }
            } else {
                sender.sendMessage("You can't really hide... you're the console");
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
