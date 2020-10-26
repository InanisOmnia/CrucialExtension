package me.inanis.plugins.crucialextension;

import me.inanis.plugins.crucialextension.commands.*;
import me.inanis.plugins.crucialextension.events.OnDeath;
import me.inanis.plugins.crucialextension.events.PlayerJoin;
import me.inanis.plugins.crucialextension.events.PlayerLeave;
import me.inanis.plugins.crucialextension.events.PlayerMove;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CrucialExtension extends JavaPlugin implements Listener {

    public ArrayList<Player> invisible_list = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Test Plugin is starting");

        // get config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // register events and commands
        registerEvents();
        registerCommands();

        getLogger().info("Setup finished");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Test Plugin has stopped");
    }


    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
    }

    private void registerCommands() {

        getCommand("vault").setExecutor(new Vault());

        // admin playing commands
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("god").setExecutor(new God());
        getCommand("fly").setExecutor(new Fly());

        // adming mod commands
        getCommand("vanish").setExecutor(new Vanish(this));
    }
}
