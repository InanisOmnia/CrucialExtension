package me.inanis.plugins.crucialextension.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.inanis.plugins.crucialextension.CrucialExtension;
import me.inanis.plugins.crucialextension.core.kits.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KitConfigManager extends JsonConfig {

    public Map<String, Kit> kits = new HashMap<>();


    public KitConfigManager(CrucialExtension plugin, String fileName) {
        super(plugin, fileName);

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(Material.STICK, 5));
        ItemStack[] isArray = items.toArray(new ItemStack[items.size()]);

        kits.put("one", new Kit(isArray, "stickOne", Material.STICK));
    }

    public void loadKits() {
        load();

        JSONObject content = getContent();
        JSONArray data = (JSONArray) content.get("data");
        for (int i=0; i<data.size(); i++) {
            JSONObject obj = (JSONObject) data.get(i);
            kits.put((String) obj.get("name"), new Kit(obj));
        }
    }

    public void saveKits() {
        ObjectMapper mapper = new ObjectMapper();
        kits.forEach((k, v) -> {
            try {
                String json = mapper.writeValueAsString(v);
                plugin.getLogger().info(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }


}