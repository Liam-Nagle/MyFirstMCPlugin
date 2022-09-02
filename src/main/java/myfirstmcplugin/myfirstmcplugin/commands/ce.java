package myfirstmcplugin.myfirstmcplugin.commands;

import myfirstmcplugin.myfirstmcplugin.exceptions.EnchantLevelException;
import myfirstmcplugin.myfirstmcplugin.CustomClasses.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ce implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      try {
          String enchantName = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
          int enchantLvl = Integer.valueOf(args[1]);
          if(sender instanceof Player) {
              Player player = (Player) sender;
              List<String> lore = new ArrayList<String>();
              ItemStack item = player.getInventory().getItemInMainHand();
              if(item.getType().equals(Material.DIAMOND_PICKAXE)) {
                  ItemMeta meta = item.getItemMeta();
                  if(meta.hasLore())
                      for(String l : meta.getLore())
                          lore.add(l);
                  if(CustomEnchants.enchants.get(enchantName).getMaxLevel() < enchantLvl) {
                      throw new EnchantLevelException();
                  }
                  if(meta.hasEnchant(CustomEnchants.enchants.get(enchantName))) {
                      if((meta.getEnchantLevel(CustomEnchants.enchants.get(enchantName))) == enchantLvl) {
                          return true;
                      } else {
                          item.removeEnchantment(CustomEnchants.enchants.get(enchantName));
                          item.addUnsafeEnchantment(CustomEnchants.enchants.get(enchantName), enchantLvl);
                          meta = item.getItemMeta();
                          for(int i = 0; i<lore.size();i++) {
                              if(lore.get(i).contains(enchantName)) {
                                  lore.remove(i);
                              }
                          }
                          lore.add(ChatColor.GRAY + enchantName + " " + enchantLvl);
                          meta.setLore(lore);
                          item.setItemMeta(meta);
                      }
                  } else {
                      item.addUnsafeEnchantment(CustomEnchants.enchants.get(enchantName), enchantLvl);
                      meta = item.getItemMeta();
                      lore.add(ChatColor.GRAY + enchantName + " " + enchantLvl);
                      meta.setLore(lore);
                      item.setItemMeta(meta);
                  }
                  return true;
              }
          }
          return true;
      } catch(IndexOutOfBoundsException e) {
          sender.sendMessage(ChatColor.RED + "Incorrect arguments please follow the below:");
          return false;
      } catch (EnchantLevelException e) {
          sender.sendMessage(ChatColor.RED + "Enchant Level above the maximum");
      }
      return true;
  }
}
