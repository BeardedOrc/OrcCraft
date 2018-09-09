package me.beardedorc.orccraft.utilities;

import me.beardedorc.orccraft.OrcCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private OrcCraft plugin = OrcCraft.getInstance();

    public ConfigManager() {};

    // ---------  Files and Configs  --------------
    public FileConfiguration customItemsCFG;
    public File customItems;
    // --------------------------------------------

    public void setup() {
        if (!(plugin.getDataFolder().exists())) {
            plugin.getDataFolder().mkdir();
        }

        customItems = new File(plugin.getDataFolder(),"customItems.yml");

        if (!customItems.exists()) {
            try {
                customItems.createNewFile();
            }catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create customItems.yml file.");
            }
        }

        customItemsCFG = YamlConfiguration.loadConfiguration(customItems);
    }

    public  FileConfiguration getCustomItemsCFG() {
        return customItemsCFG;
    }

    public void saveCustomItems() {

        try {
            customItemsCFG.save(customItems);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "The custom items file could not be saved");
        }
    }

    public void reloadCustomItems() {
        customItemsCFG = YamlConfiguration.loadConfiguration(customItems);
    }

    public void loadDefaultConfig() {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }
}
