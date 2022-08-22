package myfirstmcplugin.myfirstmcplugin.handlers;

import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        if(!player.hasPlayedBefore()) {
            //Giving player chest inventory
            ItemStack item = new ItemStack(Material.CHEST);
            item.addUnsafeEnchantment(CustomEnchants.enchants.get("Capacity"), 1);
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Enchants");
            lore.add(ChatColor.GRAY + "Capacity" + " " + "1");
            meta.setLore(lore);
            meta.setDisplayName(ChatColor.GREEN + "Storage");
            item.setItemMeta(meta);

            player.getInventory().setItem(9, item);
        } else if (!player.getInventory().contains(Material.CHEST)) {
            ItemStack item = new ItemStack(Material.CHEST);
            item.addUnsafeEnchantment(CustomEnchants.enchants.get("Capacity"), 1);
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Enchants");
            lore.add(ChatColor.GRAY + "Capacity" + " " + "1");
            meta.setLore(lore);
            meta.setDisplayName(ChatColor.GREEN + "Storage");
            item.setItemMeta(meta);

            player.getInventory().setItem(9, item);
        } else if(player.getInventory().contains(Material.CHEST)) {
            int slot = player.getInventory().first(Material.CHEST);
            if(!(player.getInventory().getItem(slot).getItemMeta().getDisplayName() == "Storage")) {
                ItemStack item = new ItemStack(Material.CHEST);
                item.addUnsafeEnchantment(CustomEnchants.enchants.get("Capacity"), 1);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Enchants");
                lore.add(ChatColor.GRAY + "Capacity" + " " + "1");
                meta.setLore(lore);
                meta.setDisplayName(ChatColor.GREEN + "Storage");
                item.setItemMeta(meta);

                player.getInventory().setItem(9, item);
            }
        }
    }
}
