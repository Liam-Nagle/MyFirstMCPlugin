package myfirstmcplugin.myfirstmcplugin.commands;

import myfirstmcplugin.myfirstmcplugin.handlers.CustomEnchants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class storage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("Delete")) {
                player.getInventory().setItem(9, new ItemStack(Material.AIR));
                Bukkit.broadcastMessage("Deleted Storage");
                return true;
            }
            if(args[0].equalsIgnoreCase("Clear")) {
                ItemStack item = player.getInventory().getItem(9);
                ItemMeta meta =  item.getItemMeta();
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Enchants");
                lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "| " + ChatColor.WHITE + "Capacity " + "1");
                lore.add("\n");
                lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Blocks");
                meta.setLore(lore);
                meta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Storage");
                item.setItemMeta(meta);
                return true;
            }
            if(args[0].equalsIgnoreCase("Give")) {
                if(args.length > 1) {
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
                    player.getServer().getPlayer(args[1]).getInventory().setItem(9, item);
                }
            }
        }
        return true;
    }
}
