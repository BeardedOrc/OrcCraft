package me.beardedorc.orccraft;

import me.beardedorc.orccraft.commands.CommandManager;
import me.beardedorc.orccraft.events.OreBreakEvent;
import me.beardedorc.orccraft.utilities.ConfigManager;
import me.beardedorc.orccraft.utilities.ItemManager;
import me.beardedorc.orccraft.utilities.MessageManager;
import me.beardedorc.orccraft.utilities.MysqlManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class OrcCraft extends JavaPlugin {

    private  static  OrcCraft instance;

    public CommandManager commandManager;
    private MysqlManager mysqlManager;
    public ConfigManager configManager;
    public ItemManager itemManager = new ItemManager();
    public MessageManager messageManager = new MessageManager();

    public HashMap<String, ItemStack> customItemMap;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);
        loadManagers();loadEventsManager();
        this.customItemMap = new HashMap<>();
        loadItemData();


    }

    public static OrcCraft getInstance() {
        return instance;
    }

    private  static void setInstance(OrcCraft instance) {
        OrcCraft.instance = instance;
    }


    private void loadManagers() {
        loadConfigManager();
        commandManager = new CommandManager();
        commandManager.setup();
        mysqlManager = new MysqlManager();
        mysqlManager.mysqlSetup();
 //       itemManager = new ItemManager();
 //       messageManager = new MessageManager();

    }

    private  void loadConfigManager() {
        configManager = new ConfigManager();
        configManager.setup();
        configManager.loadDefaultConfig();
    //    configManager.saveCustomItems();
    //    configManager.reloadCustomItems();
    }

    private void loadEventsManager() {
        getServer().getPluginManager().registerEvents(new OreBreakEvent(), this);
    }

    private Map<String, ItemStack> loadItemData() {
        customItemMap.clear();
        for (String name :  configManager.getCustomItemsCFG().getConfigurationSection("").getKeys(false)){
            ItemStack item = configManager.getCustomItemsCFG().getItemStack(name);
              if (item.getType()!= Material.AIR)
            customItemMap.put(name, item);
        }
        return customItemMap;
    }

    @Override
    public void onDisable() {
        this.customItemMap.clear();
        // Plugin shutdown logic
    }
}
