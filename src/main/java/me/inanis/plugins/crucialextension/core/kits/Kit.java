package me.inanis.plugins.crucialextension.core.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

public class Kit {

    private ItemStack[] invContents;
    private String name;
    private Material displayItem;

    public Kit(ItemStack[] invContents, String name, Material displayItem) {
        this.invContents = invContents;
        this.name = name;
        this.displayItem = displayItem;
    }

    public Kit(JSONObject obj) {
        name = (String) obj.get("name");
        invContents = (ItemStack[]) obj.get("invContents");
        displayItem = (Material) obj.get("displayItem");
    }


    public ItemStack[] getInvContents() {
        return invContents;
    }

    public void setInvContents(ItemStack[] invContents) {
        this.invContents = invContents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(Material displayItem) {
        this.displayItem = displayItem;
    }
}
