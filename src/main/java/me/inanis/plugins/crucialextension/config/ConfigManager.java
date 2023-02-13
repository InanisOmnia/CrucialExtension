package me.inanis.plugins.crucialextension.config;

import me.inanis.plugins.crucialextension.CrucialExtension;

public class ConfigManager {

    private CrucialExtension plugin;

    public ConfigManager(CrucialExtension plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
    }

    public void save() {
        plugin.saveConfig();
    }
}
