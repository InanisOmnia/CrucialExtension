package me.inanis.plugins.crucialextension;

import me.inanis.plugins.crucialextension.commands.admin.*;
import me.inanis.plugins.crucialextension.commands.chat.*;
import me.inanis.plugins.crucialextension.commands.gamemode.*;
import me.inanis.plugins.crucialextension.commands.inventory.Clearinv;
import me.inanis.plugins.crucialextension.commands.inventory.Vault;
import me.inanis.plugins.crucialextension.commands.kit.Kit;
import me.inanis.plugins.crucialextension.config.ConfigManager;
import me.inanis.plugins.crucialextension.config.JsonConfig;
import me.inanis.plugins.crucialextension.config.KitConfigManager;
import me.inanis.plugins.crucialextension.events.*;
import me.inanis.plugins.crucialextension.storage.VaultStorageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class CrucialExtension extends JavaPlugin implements Listener {

    public ConfigManager cfgm;
    public KitConfigManager kitConfigManager;
    public VaultStorageManager vaultStorageManager;

    public JsonConfig jsonCfgExample;

    public ArrayList<Player> invisible_list = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting");

        // get config
        loadCConfig();

        // setup the storage
        loadCStorage();

        // register events and commands
        registerEvents();
        registerCommands();

        getLogger().info("Setup finished");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Stopping");

        saveCStorage();

        saveCConfig();

        getLogger().info("Stopped");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnDeath(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new InvClose(this), this);
        getServer().getPluginManager().registerEvents(new PlayerBedEnter(this), this);
    }

    private void registerCommands() {

        // Inventory
        Objects.requireNonNull(getCommand("vault")).setExecutor(new Vault(this));
        Objects.requireNonNull(getCommand("clearinv")).setExecutor(new Clearinv());

        // admin playing commands
        Objects.requireNonNull(getCommand("heal")).setExecutor(new Heal());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new Feed());
        Objects.requireNonNull(getCommand("god")).setExecutor(new God());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

        // mod commands
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new Vanish(this));

        // chat commands
        Objects.requireNonNull(getCommand("whisper")).setExecutor(new Whisper());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new Clearchat(this));

        // kit commands
        Objects.requireNonNull(getCommand("kit")).setExecutor(new Kit(this));

        // gamemode commands
        Objects.requireNonNull(getCommand("gmsp")).setExecutor(new Gmsp());
        Objects.requireNonNull(getCommand("gms")).setExecutor(new Gms());
        Objects.requireNonNull(getCommand("gmc")).setExecutor(new Gmc());
        Objects.requireNonNull(getCommand("gma")).setExecutor(new Gma());
    }

    public void loadCConfig() {
        cfgm = new ConfigManager(this);
        cfgm.setup();

        kitConfigManager = new KitConfigManager(this, "kits");
        kitConfigManager.setup();
        kitConfigManager.loadKits();

//        jsonCfgExample = new JsonConfig(this, "test");
//        jsonCfgExample.setup();
//        jsonCfgExample.load();
    }

    public void saveCConfig(){
        cfgm.save();
//        jsonCfgExample.save();

        kitConfigManager.saveKits();
    }

    public void loadCStorage() {
        vaultStorageManager = new VaultStorageManager(this);
        vaultStorageManager.setup();
    }

    public void saveCStorage() {
        vaultStorageManager.saveVaults();
    }
}
