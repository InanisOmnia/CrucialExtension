package me.inanis.plugins.crucialextension.storage;

import me.inanis.plugins.crucialextension.CrucialExtension;
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

    public Map<String, ItemStack[]> vaults = new HashMap<>();
    public FileConfiguration vaultCfg;
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
                plugin.getLogger().info(ChatColor.GREEN + "playerVaults.yml file has been created");
            } catch (IOException e){
                plugin.getLogger().info(ChatColor.DARK_RED + "Could not create kits.yml file");
            }
        }

        loadVaults();
    }

    // vaults
    public FileConfiguration getVaultCfg() {
        return vaultCfg;
    }

    public Map<String, ItemStack[]> getVaults() {
        return vaults;
    }

    public void saveVaults() {
        if (!vaults.isEmpty()) {
            try {

                for (Map.Entry<String, ItemStack[]> entry : vaults.entrySet()) {
                    vaultCfg.set("data." + entry.getKey(), entry.getValue());
                }

                vaultCfg.save(vaultFile);
                plugin.getLogger().info(ChatColor.GREEN + "playerVaults.yml file has been saved");
            } catch(IOException e) {
                plugin.getLogger().info(ChatColor.DARK_RED + "Could not save playerVaults.yml file");
            }
        } else {
            plugin.getLogger().info(ChatColor.YELLOW + "playerVaults hashmap is empty and has been ignored");
        }
    }

    public void loadVaults() {
        vaultCfg = YamlConfiguration.loadConfiguration(vaultFile);
        if (vaultCfg.contains("data")) {

            vaultCfg.getConfigurationSection("data").getKeys(false).forEach(key -> {
                @SuppressWarnings("unchecked")
                ItemStack[] content = ((List<ItemStack>) vaultCfg.get("data." + key)).toArray(new ItemStack[0]);
                vaults.put(key, content);
            });

            plugin.getLogger().info(ChatColor.GREEN + "playerVaults.yml file has been loaded");
        } else {
            plugin.getLogger().info(ChatColor.YELLOW + "playerVaults.yml file is empty and has been ignored");
        }
    }

    public void reloadVaults() {
        vaults.clear();
        loadVaults();
    }

}
