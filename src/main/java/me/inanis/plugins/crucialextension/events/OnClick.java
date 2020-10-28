package me.inanis.plugins.crucialextension.events;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnClick implements Listener {

    public void clickEvent(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("vault")) {

        }
    }
}
