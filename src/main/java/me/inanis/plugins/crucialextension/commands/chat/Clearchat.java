package me.inanis.plugins.crucialextension.commands.chat;

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

import java.util.List;

public class Clearchat implements CommandExecutor, TabCompleter {

    private final CrucialExtension plugin;

    public Clearchat(CrucialExtension plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("CrucialExtension.chat.clear")) {
                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
                return true;
            }
        }
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("CrucialExtension.chat.clear.bypassClear")) {
                all.sendMessage(sender.getName() + " cleared the chat");
            } else {
                clearChat(sender, all);
            }
        }
        return true;
    }

    private void clearChat(CommandSender sender, Player player) {
        String clearMessage = plugin.getConfig().getString("chat-clear-message");
        if (clearMessage == null) {
            clearMessage = "Chat cleared by {PLAYER}";
        }
        if (sender instanceof Player) {
            clearMessage = clearMessage.replaceAll("\\{PLAYER}", player.getDisplayName());
        } else {
            clearMessage = clearMessage.replaceAll("\\{PLAYER}", "Console");
        }

        int fillerLength = (clearMessage.length() - 8) / 2;
        String filler = ChatColor.GOLD + "|" + "-".repeat(fillerLength) + "+====+" + "-".repeat(fillerLength) + "|";
        for (int i = 0; i < 150; i++) {
            player.sendMessage("");
        }
        player.sendMessage(filler);
        player.sendMessage("    " + clearMessage);
        player.sendMessage(filler);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
