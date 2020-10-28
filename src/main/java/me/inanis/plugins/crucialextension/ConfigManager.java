package me.inanis.plugins.crucialextension;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final CrucialExtension plugin;

    public ConfigManager(CrucialExtension plugin) { this.plugin = plugin; }

    // Files and file configs
    public FileConfiguration kitConfig;
    public File kitConfigFile;


    //--------------------------

    public void setup(){
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        // Kit Config
        kitConfigFile = new File(plugin.getDataFolder(), "kits.yml");
        if (!kitConfigFile.exists()) {
            try {
                kitConfigFile.createNewFile();
                Bukkit.getServer().getLogger().info(ChatColor.GREEN + "kits.yml file has been created");
            } catch (IOException e){
                Bukkit.getServer().getLogger().info(ChatColor.DARK_RED + "Could not create kits.yml file");
            }
        }
        kitConfig = YamlConfiguration.loadConfiguration(kitConfigFile);
        Bukkit.getServer().getLogger().info(ChatColor.GREEN + "kits.yml file has been loaded");


    }

    // kits
    public FileConfiguration getKitConfig() {
        return kitConfig;
    }

    public void saveKitConfig() {
        try {
            kitConfig.save(kitConfigFile);
            Bukkit.getServer().getLogger().info(ChatColor.GREEN + "kits.yml file has been saved");
        } catch(IOException e) {
            Bukkit.getServer().getLogger().info(ChatColor.DARK_RED + "Could not save kits.yml file");
        }
    }

    public void reloadKitConfig() {
        kitConfig = YamlConfiguration.loadConfiguration(kitConfigFile);
        Bukkit.getServer().getLogger().info(ChatColor.GREEN + "kits.yml file has been reloaded");
    }

}
