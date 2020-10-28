package me.inanis.plugins.crucialextension.events;

import me.inanis.plugins.crucialextension.CrucialExtension;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvClose implements Listener {

    private final CrucialExtension plugin;

    public InvClose(CrucialExtension plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void invClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("Your Vault")) {
            plugin.vaultStorageManager.vaultshash.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
        }
    }
}
