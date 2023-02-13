package me.inanis.plugins.crucialextension.config;

import me.inanis.plugins.crucialextension.CrucialExtension;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class JsonConfig {

    File f;
    CrucialExtension plugin;
    JSONObject content;

    public JsonConfig(CrucialExtension plugin, String name) {
        this.plugin = plugin;
        f = new File(plugin.getDataFolder(), name + ".json");
    }

    public void setup(){
        if (!f.exists()) {
            try {
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.append("{}");
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject getContent(){
        return content;
    }

    public void load() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(f)) {
            Object obj = jsonParser.parse(reader);
            content = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (FileWriter file = new FileWriter(f)) {
            file.write(content.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
