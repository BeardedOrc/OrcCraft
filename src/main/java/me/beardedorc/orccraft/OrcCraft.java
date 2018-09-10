package me.beardedorc.orccraft;

import me.beardedorc.orccraft.commands.utilities.CommandManager;
import me.beardedorc.orccraft.events.OreBreakEvent;
import me.beardedorc.orccraft.utilities.ConfigManager;
import me.beardedorc.orccraft.utilities.MysqlManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OrcCraft extends JavaPlugin {

    private  static  OrcCraft instance;
    public CommandManager commandManager;
    private MysqlManager mysqlManager;
    public ConfigManager configManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);
        loadManagers();loadEventsManager();


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
    }

    private  void loadConfigManager() {
        configManager = new ConfigManager();
        configManager.setup();
        configManager.loadDefaultConfig();
        configManager.saveCustomItems();
        configManager.reloadCustomItems();
    }

    private void loadEventsManager() {
        getServer().getPluginManager().registerEvents(new OreBreakEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
