package me.beardedorc.orccraft.utilities;

import me.beardedorc.orccraft.OrcCraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private OrcCraft plugin = OrcCraft.getInstance();
    private Map<String, ItemStack> customItemData = new HashMap<>();
    private ConfigManager configManager;
    private MessageManager messageManager;

    public void createItem(Player player, String name) {
        if (!(checkItemExistsMap(name) || checkItemExistsFile(name))) {
            ItemStack item = new ItemStack(player.getInventory().getItemInMainHand());
            saveItemDataToMap(name, item);
            saveItemDataToFile(name, item);
            messageManager.playerGoodMessage(player,name + " saved.");
        }
    }

    public void giveItem(Player player, Player targetPlayer, String name, int quantity){
        if (checkItemExistsMap(name)) {
            ItemStack item = itemStackBuilder(name, quantity);
            targetPlayer.getInventory().addItem(item);
            messageManager.playerGoodMessage(player, "Gave " + targetPlayer.getName() + " " + quantity + " " + item + ".");
            messageManager.playerGoodMessage(targetPlayer, "You received " + quantity + " " + item + ".");
        }
    }

    public void dropItem(Player player, Location location, String name, int quantity){

    }

    private Map<String, ItemStack> loadItemData() {
        customItemData.clear();
        for (String name :  configManager.getCustomItemsCFG().getConfigurationSection("Items").getKeys(false)){
            customItemData.put(name, configManager.getCustomItemsCFG().getItemStack(name));
        }
        return customItemData;
    }

    private boolean checkItemExistsFile(String name) {
        if (!(configManager.getCustomItemsCFG().getConfigurationSection("Items").contains(name))) {
            return false;
        }
        return true;
    }
    private boolean checkItemExistsMap(String name) {
        if (!(customItemData.containsKey(name))) {
            return false;
        }
        return true;
    }

    private ItemStack getItem(String name) {
        if (!(checkItemExistsMap(name))) {
            ItemStack item = new ItemStack(Material.AIR, 1);
            return item;
        } else {
            ItemStack item = customItemData.get(name);
            return item;
        }
    }

    private void saveItemDataToFile(String name, ItemStack item) {
        if (!(checkItemExistsFile(name))) {
            configManager.getCustomItemsCFG().set(name, item);
            configManager.saveCustomItems();
        } else {
            messageManager.consoleErrorMessage("This item already exists!");
        }
    }

    private void removeItemDataFromFile(String name, ItemStack item) {
        if (!(checkItemExistsFile(name))) {
            configManager.getCustomItemsCFG().set("Items." + name, null);
            configManager.saveCustomItems();
        } else {
            messageManager.consoleErrorMessage("This item already exists!");
        }
    }

    private void saveItemDataToMap(String name, ItemStack item) {
        if (!(checkItemExistsMap(name))) {
            customItemData.put(name, item);
        } else {
            messageManager.consoleErrorMessage("This item already exists!");
        }
    }

    private void removeItemDataFromMap(String name, ItemStack item) {
        if (!(checkItemExistsMap(name))) {
            customItemData.remove(name);
        } else {
            messageManager.consoleErrorMessage("This item already exists!");
        }
    }

    private ItemStack itemStackBuilder(String name, int quantity) {
        ItemStack item = getItem(name);
        item.setAmount(quantity);
        return item;
    }



}
