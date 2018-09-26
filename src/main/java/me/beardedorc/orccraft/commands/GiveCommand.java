package me.beardedorc.orccraft.commands;

import me.beardedorc.orccraft.OrcCraft;
import me.beardedorc.orccraft.commands.utilities.SubCommand;
import me.beardedorc.orccraft.utilities.ItemManager;
import me.beardedorc.orccraft.utilities.MessageManager;
import me.beardedorc.orccraft.utilities.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GiveCommand extends SubCommand {
    private OrcCraft plugin = OrcCraft.getInstance();
    MessageManager messageManager;
    ItemManager itemManager;
    PlayerManager playerManager;

    @Override
    public void onCommand(Player player, String[] args) {
        int quantity;

        if ((args.length < 3) || (args.length > 4)) {
            messageManager.playerErrorMessage(player,"Please include the name of the person to give the item to, the quantity, and the internal item name");
            return;
        }

        if (!playerManager.playerExist(args[1])) {
            messageManager.playerErrorMessage(player,"Please enter a valid player name");
            return;
        }

        Player targetName = Bukkit.getPlayerExact(args[1]);

        if (args.length == 3) {
            String itemName = args[2];
            itemManager.giveItem(player, targetName, itemName, 1);
            player.sendMessage("Gave " + player.getName() + " " + itemName + ".");
            return;
        }

        if (args.length == 4) {
            if (!isInteger(args[2])) {
                player.sendMessage(ChatColor.RED + "The quantity MUST be an integer. example: /orccraft give " + player.getName() + ChatColor.YELLOW + " 1 " + ChatColor.RED + args[3]);
                return;
            } else {
                quantity = Integer.valueOf(args[2]);
            }
            String itemName = args[3];
            itemManager.giveItem(player, targetName, itemName, quantity);
        }
    }

    @Override
    public String name() {
        return plugin.commandManager.give;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }

    }
}