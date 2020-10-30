package me.inanis.plugins.crucialextension.commands.kit;

import me.inanis.plugins.crucialextension.CrucialExtension;
import me.inanis.plugins.crucialextension.menus.IconMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Kit implements CommandExecutor, TabCompleter {

    public final CrucialExtension plugin;

    public Kit(CrucialExtension plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // /kit name (CrucialExtension.kits.self.name)
        // /kit name player (CrucialExtension.kits.others.name)
        // /kit add name (CrucialExtension.kits.add)
        // /kit delete name (CrucialExtension.kits.delete)
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                // open kits gui
                IconMenu menu = new IconMenu("Kits", 9, event -> {

                    // give different items based on event.getName()
                    event.getPlayer().sendMessage("You have chosen " + event.getName());

                    // make sure the inventory closes
                    event.setWillClose(true);
                    // and destroys itself to prevent multiple calls
                    event.setWillDestroy(true);
                }, plugin);

                menu.setOption(3, new ItemStack(Material.APPLE, 1), "Food", "The food is delicious");
                menu.setOption(4, new ItemStack(Material.IRON_SWORD, 1), "Weapon", "Weapons are for awesome people");
                menu.setOption(5, new ItemStack(Material.EMERALD, 1), "Money", "Money brings happiness");

                menu.open(player);
                return true;
            }
            if (args.length < 2) {
                // check if kit with name exists
                // check if player has access to that kit
                // give player the relevant items

                return true;
            }
            if (args.length < 3) {
                if (args[1].equalsIgnoreCase("add")) {
                    String kitName = args[2];
                    // check kitname doesn't already exist

                    // open gui in which the player can put in the the items

                } else {
                    // unknown command
                }

                return true;
            }

        } else {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.DARK_RED + "You need to provide a kit name and a player");
                return false;
            }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.DARK_RED + "You need to provide a kit name and a player");
                return false;
            }
            // do logic
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            // get list of all kits
            List<String> kits = new ArrayList<String>();

            return kits;
        }
        if (args.length == 2) {
            List<String> players = new ArrayList<String>();
            for(Player p : Bukkit.getOnlinePlayers()){
                players.add(p.getName());
            }
            return players;
        }
        return null;
    }
}
