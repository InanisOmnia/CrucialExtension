package me.inanis.plugins.crucialextension;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class VaultStorageManager {

    private final CrucialExtension plugin;

    public VaultStorageManager(CrucialExtension plugin) { this.plugin = plugin; }

    public Map<String, ItemStack[]> vaultshash = new HashMap<>();
    public FileConfiguration vaults;
    public File vaultFile;
    //--------------------------

    public void setup(){
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        // vault storage
        vaultFile = new File(plugin.getDataFolder(), "playerVaults.yml");
        if (!vaultFile.exists()) {
            try {
                vaultFile.createNewFile();
                Bukkit.getServer().getLogger().info(ChatColor.GREEN + "playerVaults.yml file has been created");
            } catch (IOException e){
                Bukkit.getServer().getLogger().info(ChatColor.DARK_RED + "Could not create kits.yml file");
            }
        }

        loadVaults();
    }

    // vaults
    public FileConfiguration getVaults() {
        return vaults;
    }

    public void saveVaults() {
        if (!vaultshash.isEmpty()) {
            try {

                for (Map.Entry<String, ItemStack[]> entry : vaultshash.entrySet()) {
                    vaults.set("data." + entry.getKey(), entry.getValue());
                }

                vaults.save(vaultFile);
                Bukkit.getServer().getLogger().info(ChatColor.GREEN + "playerVaults.yml file has been saved");
            } catch(IOException e) {
                Bukkit.getServer().getLogger().info(ChatColor.DARK_RED + "Could not save playerVaults.yml file");
            }
        } else {
            Bukkit.getServer().getLogger().info(ChatColor.YELLOW + "playerVaults hashmap is empty and has been ignored");
        }
    }

    public void reloadVaults() {
        vaultshash.clear();
        loadVaults();
    }

    public void loadVaults() {
        vaults = YamlConfiguration.loadConfiguration(vaultFile);
        if (vaults.contains("data")) {

            vaults.getConfigurationSection("data").getKeys(false).forEach(key -> {
                @SuppressWarnings("unchecked")
                ItemStack[] content = ((List<ItemStack>) vaults.get("data." + key)).toArray(new ItemStack[0]);
                vaultshash.put(key, content);
            });

            Bukkit.getServer().getLogger().info(ChatColor.GREEN + "playerVaults.yml file has been loaded");
        } else {
            Bukkit.getServer().getLogger().info(ChatColor.YELLOW + "playerVaults.yml file is empty and has been ignored");
        }
    }

}
