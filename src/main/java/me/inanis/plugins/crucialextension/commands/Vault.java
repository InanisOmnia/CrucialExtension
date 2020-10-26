package me.inanis.plugins.crucialextension.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class Vault implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("CrucialExtension.vault")) {
                Inventory vault = Bukkit.createInventory(player, 9, "Vault");


                player.openInventory(vault);

            } else {
                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
            }
        } else {
            sender.sendMessage("The console cannot perform this command");
        }
        return false;
    }
}
