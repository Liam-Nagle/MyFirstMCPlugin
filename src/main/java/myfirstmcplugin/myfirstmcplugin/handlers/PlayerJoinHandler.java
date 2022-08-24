package myfirstmcplugin.myfirstmcplugin.handlers;

import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PlayerJoinHandler implements Listener {

    public PlayerJoinHandler(MyFirstMCPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getLogger().info("Player joining...");
        if(!player.hasPlayedBefore()) {

            player.getInventory().setItem(9, createStorage());
            Bukkit.getLogger().info("Player not joined.. Giving storage");
        } else if (!player.getInventory().contains(Material.CHEST)) {

            player.getInventory().setItem(9, createStorage());
            Bukkit.getLogger().info("Player has no chest item.. Giving storage");
        } else if(player.getInventory().contains(Material.CHEST)) {

            int slot = player.getInventory().first(Material.CHEST);
            if(!(player.getInventory().getItem(slot).getItemMeta().getDisplayName() == "Storage")) {
                player.getInventory().setItem(9, createStorage());
                Bukkit.getLogger().info("Player has no storage.. Giving Storage");
            }
        }
    }

    private ItemStack createStorage() {
        ItemStack item = new ItemStack(Material.CHEST);
        item.addUnsafeEnchantment(CustomEnchants.enchants.get("Capacity"), 1);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Enchants");
        lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "| " + ChatColor.WHITE + "Capacity " + "1");
        lore.add("\n");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Blocks");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Storage");
        item.setItemMeta(meta);
        return item;
    }
}
