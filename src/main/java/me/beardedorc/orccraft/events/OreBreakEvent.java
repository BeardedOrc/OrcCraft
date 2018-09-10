package me.beardedorc.orccraft.events;

import me.beardedorc.orccraft.OrcCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OreBreakEvent implements Listener {

    private OrcCraft plugin = OrcCraft.getInstance();
    private List<String> worlds = new ArrayList<String>();
    public List<String> protectedOre = loadProtectedOre();



        public List<String>loadProtectedOre() {
        List<String> protectedOre = new ArrayList<String>();
        for (String materialName : plugin.getConfig().getConfigurationSection("ore_protection.protected_ore_blocks").getKeys(false)) {
            protectedOre.add(materialName);
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Added " + protectedOre + "to " + ChatColor.AQUA + "Protected Ores.");
        return protectedOre;
    }

    @EventHandler
    public void onBreakProtectedOre(BlockBreakEvent event) {

        List<World> worlds = new ArrayList<World>();
        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        String matName = event.getBlock().getType().toString();

     //   player.sendMessage("The protected ores are " + protectedOre);

        if (protectedOre.contains(material.toString())) {
      //      player.sendMessage("you broke a " + matName);

            // set destroyed block to air
            block.setType(Material.AIR);

            Boolean alwaysEnabled = Boolean.valueOf(plugin.getConfig().getBoolean("ore_protection.protected_ore_blocks." + matName + ".always_drops.enabled"));
            Boolean randomEnabled = Boolean.valueOf(plugin.getConfig().getBoolean("ore_protection.protected_ore_blocks." + matName + ".random_drops.enabled"));
            Boolean rareEnabled = Boolean.valueOf(plugin.getConfig().getBoolean("ore_protection.protected_ore_blocks." + matName + ".rare_drops.enabled"));

            // set 100% droprate items
            if (alwaysEnabled.equals(true)) {
                for (String drop : plugin.getConfig().getStringList("ore_protection.protected_ore_blocks." + matName + ".always_drops.drops")) {
                    String[] array = drop.split(",");
                    String item = array[0];
                    int quantity = Integer.valueOf(array[1]);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.getMaterial(item), quantity));
                    player.sendMessage("dropped " + item);
                }
            }


            // set random drops.  will drop one of this list
            if (randomEnabled.equals(true)) {
                List<String> droparray = new ArrayList<String>();
                for (String drop : plugin.getConfig().getStringList("ore_protection.protected_ore_blocks." + matName + ".random_drops.drops")) {
                    droparray.add(drop);
                }
                Random random = new Random();
                int i = random.nextInt(droparray.size());
                String dropItem = droparray.get(i);
                String[] array = dropItem.split(",");
                String item = array[0];
                int quantity = Integer.valueOf(array[1]);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.getMaterial(item), quantity));
                player.sendMessage("dropped " + item);
            }

            // set rare drop rate item
            if (rareEnabled.equals(true)) {
                List<String> droparray = new ArrayList<String>();
                for (String drop : plugin.getConfig().getStringList("ore_protection.protected_ore_blocks." + matName + ".rare_drops.drops")) {
                    droparray.add(drop);
                }
                Random random = new Random();

                int i = random.nextInt(droparray.size());
                int c = random.nextInt(100);
                String dropItem = droparray.get(i);
                String[] array = dropItem.split(",");
                String item = array[0];
                int quantity = Integer.valueOf(array[1]);
                int chance = Integer.valueOf(array[2]);
                if (c <= chance) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.getMaterial(item), quantity));
                    player.sendMessage("dropped " + item);
                }

            }
        }
    }
}
