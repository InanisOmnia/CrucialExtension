package me.inanis.plugins.crucialextension;

import me.inanis.plugins.crucialextension.commands.*;
import me.inanis.plugins.crucialextension.commands.admin.*;
import me.inanis.plugins.crucialextension.commands.chat.*;
import me.inanis.plugins.crucialextension.events.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class CrucialExtension extends JavaPlugin implements Listener {

    public ConfigManager cfgm;
    public VaultStorageManager vaultStorageManager;

    public ArrayList<Player> invisible_list = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting");

        // get config
        loadConfig();

        // setup the storage
        loadStorage();

        // register events and commands
        registerEvents();
        registerCommands();

        getLogger().info("Setup finished");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Stopping");

        saveStorage();

        getLogger().info("Stopped");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new InvClose(this), this);
    }

    private void registerCommands() {

        Objects.requireNonNull(getCommand("vault")).setExecutor(new Vault(this));

        // admin playing commands
        Objects.requireNonNull(getCommand("heal")).setExecutor(new Heal());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new Feed());
        Objects.requireNonNull(getCommand("god")).setExecutor(new God());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

        // admin mod commands
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new Vanish(this));

        // chat commands
        Objects.requireNonNull(getCommand("whisper")).setExecutor(new Whisper());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new Clearchat(this));
    }

    public void loadConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        cfgm = new ConfigManager(this);
        cfgm.setup();
    }

    public void loadStorage() {
        vaultStorageManager = new VaultStorageManager(this);
        vaultStorageManager.setup();
    }

    public void saveStorage() {
        vaultStorageManager.saveVaults();
    }
}
