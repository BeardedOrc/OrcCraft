package me.beardedorc.orccraft.CustomItems;

import me.beardedorc.orccraft.OrcCraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

    private OrcCraft plugin = OrcCraft.getInstance();

    public  ItemStack createItem(String name, int quantity){

        String itemDisplayName[] = (this.plugin.configManager.getCustomItemsCFG().getString("name." + name + ".displayName").split(","));
        Material itemMaterial = Material.valueOf(this.plugin.configManager.getCustomItemsCFG().getString("name." + name + "material").toUpperCase());
        Boolean itemUnbreakableFlag = this.plugin.configManager.getCustomItemsCFG().getBoolean("name." + name + "unbreakable");
        Boolean itemHideAttributesFlag = this.plugin.configManager.getCustomItemsCFG().getBoolean("name." + name + "hide_attributes");
        Boolean itemHideEnchantsFlag = this.plugin.configManager.getCustomItemsCFG().getBoolean("name." + name + "hide_enchants");
        Boolean itemHideEffectsFlag = this.plugin.configManager.getCustomItemsCFG().getBoolean("name." + name + "hide_effects");

        ItemStack item = new ItemStack(itemMaterial, quantity);

        // create meta
        ItemMeta meta = item.getItemMeta();

        // set display name
        ChatColor DisplayNameColor = ChatColor.valueOf(itemDisplayName[0].toUpperCase());
        String DisplayName = itemDisplayName[1];
        meta.setDisplayName(DisplayNameColor + DisplayName);

        // set lore
/*        if (!(this.plugin.configManager.getCustomItemsCFG().getStringList("name." + name + "lore")).isEmpty()) {
            ArrayList<String> lore = new ArrayList<String>();
            for (String l : this.plugin.configManager.getCustomItemsCFG().getStringList("name." + name + "lore")) {
                String loreArray[] = l.split(",");
                ChatColor loreColor = ChatColor.valueOf(loreArray[0]);
                String addLore = loreColor + loreArray[1];
                lore.add(addLore);
            }
            meta.setLore(lore);
        }

        // set Item Flags
        if (itemUnbreakableFlag.equals(true)) {
            meta.setUnbreakable(itemUnbreakableFlag);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }

        if (itemHideAttributesFlag.equals(true)) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        if (itemHideEnchantsFlag.equals(true)) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (itemHideEffectsFlag.equals(true)) {
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        }

        //set enchants
        if (!(this.plugin.configManager.getCustomItemsCFG().getStringList("name." + name + "enchants")).isEmpty()) {
            for (String e : this.plugin.configManager.getCustomItemsCFG().getStringList("name." + name + "lore")) {
                String[] array = e.split(",");
                String enchantment_name = array[0].toUpperCase();
                Enchantment enchantment = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchantment_name));
                int level = Integer.valueOf(array[1]);
                boolean level_restriction = Boolean.valueOf(array[2]);
                meta.addEnchant(enchantment, level, level_restriction);
            }
        } */

        return item;
    }
}