package me.beardedorc.orccraft.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

    private String prefix = ChatColor.BLUE + "OrcCraft: ";
    private String debugprefix = ChatColor.GOLD + "OrcCraft Debug: ";

    public void consoleDebug(String message) {
        Bukkit.getConsoleSender().sendMessage(debugprefix + ChatColor.DARK_PURPLE + message);
    }

    public void consoleErrorMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + message);
    }

    public void consoleWarnMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + message);
    }

    public void consoleGoodMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + message);
    }

    public void playerErrorMessage(Player player, String message) {
        player.sendMessage(prefix + ChatColor.RED + message);
    }

    public void playerWarnMessage(Player player, String message) {
        player.sendMessage(prefix + ChatColor.YELLOW + message);
    }

    public void playerGoodMessage(Player player, String message) {
        player.sendMessage(prefix + ChatColor.GREEN + message);
    }

}
