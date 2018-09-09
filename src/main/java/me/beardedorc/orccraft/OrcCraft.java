package me.beardedorc.orccraft;

import me.beardedorc.orccraft.commands.utilities.CommandManager;
import me.beardedorc.orccraft.utilities.MysqlManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class OrcCraft extends JavaPlugin {

    private  static  OrcCraft instance;
    public CommandManager commandManager;
    public MysqlManager mysqlManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);
        loadConfig();
        commandManager = new CommandManager();
        commandManager.setup();
        mysqlManager = new MysqlManager();
        mysqlManager.mysqlSetup();


    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static OrcCraft getInstance() {
        return instance;
    }

    private  static void setInstance(OrcCraft instance) {
        OrcCraft.instance = instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
