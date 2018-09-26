package me.beardedorc.orccraft.commands;

import me.beardedorc.orccraft.OrcCraft;
import me.beardedorc.orccraft.commands.utilities.SubCommand;
import me.beardedorc.orccraft.utilities.ItemManager;
import me.beardedorc.orccraft.utilities.MessageManager;
import org.bukkit.entity.Player;

public class CreateItemCommand extends SubCommand {

    private OrcCraft plugin = OrcCraft.getInstance();
    ItemManager itemManager;
    MessageManager messageManager;

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length < 2){
            messageManager.playerErrorMessage(player, "Please include the name of item to be stored as.  Use _ for spaces.");
            return;
        }

        String localName = args[1];
        itemManager.createItem(player, localName);
    }

    @Override
    public String name() {
        return plugin.commandManager.createitem;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}