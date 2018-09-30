package me.beardedorc.orccraft.utilities;

import me.beardedorc.orccraft.OrcCraft;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    private OrcCraft plugin = OrcCraft.getInstance();

    public void createNewItem (Player player, String name) {
        if (!checkItemExists(name)) {
            addItemToMap(player, name);
            saveItemDataToFile(player, name);
        } else {
            plugin.messageManager.playerWarnMessage(player, "An item already exists with this name.");
            plugin.messageManager.playerWarnMessage(player, "Please rename your item, or remove the old item.");
        }
    }

    public void removeItem(Player player, String name) {
        if (!(checkItemExists(name))) {
            plugin.customItemMap.remove(name);
            ItemStack item = new ItemStack(Material.AIR);
            plugin.configManager.getCustomItemsCFG(). set(name, item);
            plugin.configManager.saveCustomItems();
            plugin.messageManager.playerWarnMessage(player, "Successfully removed " + name + ".");
        }
    }

    public void giveItem(Player player, Player targetPlayer, String name, int quantity){
        if (checkItemExists(name)) {
            ItemStack item = itemStackBuilder(name, quantity);
            targetPlayer.getInventory().addItem(item);
            plugin.messageManager.playerGoodMessage(player, "Gave " + targetPlayer.getName() + " " + quantity + " " + item.getItemMeta().getDisplayName() + ".");
            plugin.messageManager.playerGoodMessage(targetPlayer, "You received " + quantity + " " + item.getItemMeta().getDisplayName() + ".");
        }
    }

    public void dropItem(Block block, String name, int quantity){
        if (checkItemExists(name)) {
            ItemStack item = itemStackBuilder(name, quantity);
            block.getWorld().dropItemNaturally(block.getLocation(), item);
        }
    }

    public boolean checkItemExists (String name) {
        if (plugin.customItemMap.containsKey(name)) {
            return true;
        } else {
            return false;
        }
    }

    private void addItemToMap (Player player, String name) {
        ItemStack item = player.getInventory().getItemInMainHand();
        plugin.customItemMap.put(name, item);
        plugin.messageManager.playerGoodMessage(player, "Successfully created " + name + ".");
    }

    private void saveItemDataToFile(Player player, String name) {
        ItemStack item = player.getInventory().getItemInMainHand();
        plugin.configManager.getCustomItemsCFG().set(name, item);
        plugin.configManager.saveCustomItems();
    }

    private ItemStack itemStackBuilder(String name, int quantity) {
        ItemStack item = getItem(name);
        item.setAmount(quantity);
        return item;
    }

    private ItemStack getItem(String name) {
        ItemStack item = plugin.customItemMap.get(name);
        return item;
    }

}