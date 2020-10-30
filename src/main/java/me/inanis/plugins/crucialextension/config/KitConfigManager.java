package me.inanis.plugins.crucialextension.config;

import me.inanis.plugins.crucialextension.CrucialExtension;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class KitConfigManager {

    private final CrucialExtension plugin;

    public KitConfigManager(CrucialExtension plugin) { this.plugin = plugin; }

    public Map<String, ItemStack[]> kits = new HashMap<>();
    public FileConfiguration kitCfg;
    public File kitFile;
    //--------------------------

    public void setup(){
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        // vault storage
        kitFile = new File(plugin.getDataFolder(), "kits.yml");
        if (!kitFile.exists()) {
            try {
                kitFile.createNewFile();
                plugin.getLogger().info(ChatColor.GREEN + "kits.yml file has been created");
            } catch (IOException e){
                plugin.getLogger().info(ChatColor.DARK_RED + "Could not create kits.yml file");
            }
        }

        loadKits();
    }

    // vaults
    public FileConfiguration getKitCfg() {
        return kitCfg;
    }

    public Map<String, ItemStack[]> getKits() {
        return kits;
    }

    public void saveKits() {
        if (!kits.isEmpty()) {
            try {

                for (Map.Entry<String, ItemStack[]> entry : kits.entrySet()) {
                    kitCfg.set("data." + entry.getKey(), entry.getValue());
                }

                kitCfg.save(kitFile);
                plugin.getLogger().info(ChatColor.GREEN + "kits.yml file has been saved");
            } catch(IOException e) {
                plugin.getLogger().info(ChatColor.DARK_RED + "Could not save kits.yml file");
            }
        } else {
            plugin.getLogger().info(ChatColor.YELLOW + "playerVaults hashmap is empty and has been ignored");
        }
    }

    public void loadKits() {
        kitCfg = YamlConfiguration.loadConfiguration(kitFile);
        if (kitCfg.contains("data")) {

            kitCfg.getConfigurationSection("data").getKeys(false).forEach(key -> {
                @SuppressWarnings("unchecked")
                ItemStack[] content = ((List<ItemStack>) kitCfg.get("data." + key)).toArray(new ItemStack[0]);
                kits.put(key, content);
            });

            plugin.getLogger().info(ChatColor.GREEN + "kits.yml file has been loaded");
        } else {
            plugin.getLogger().info(ChatColor.YELLOW + "kits.yml file is empty and has been ignored");
        }
    }

    public void reloadVaults() {
        kits.clear();
        loadKits();
    }

}
