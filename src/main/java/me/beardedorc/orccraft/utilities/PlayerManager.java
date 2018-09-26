package me.beardedorc.orccraft.utilities;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayerManager {

    public boolean playerExist(String playerName) {
        if (Bukkit.getServer().getPlayerExact(playerName) != null) {
            return true;
        }
        return false;
    }

    public boolean isPlayerOnline(String playerName) {
        if (Bukkit.getServer().getOnlinePlayers().contains(playerName)) {
            return true;
        }
        return false;
    }

    public boolean isPlayerOffline(String playerName) {
        for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
            if (playerName.equals(player.getName())) {
                return true;
            }
        }
        return false;
    }


}

