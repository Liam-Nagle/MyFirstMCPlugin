package myfirstmcplugin.myfirstmcplugin.handlers;

import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeathHandler implements Listener {

    public PlayerDeathHandler(MyFirstMCPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    List<ItemStack> keepDrops = new ArrayList<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        keepDrops.clear();
        List<ItemStack> drops = event.getDrops();
        List<ItemStack> dropsToRemove = new ArrayList<>();
        for(int i = 0; i < drops.size(); i++) {
            Bukkit.getLogger().warning(drops.get(i).toString());
            if(drops.get(i).getItemMeta().hasCustomModelData()) {
                if(drops.get(i).getItemMeta().getCustomModelData() == 1002 || drops.get(i).getItemMeta().getCustomModelData() == 1001) {
                    Bukkit.broadcastMessage("Check...");
                    keepDrops.add(drops.get(i));
                    dropsToRemove.add(drops.get(i));
                }
            }
        }
        event.getDrops().removeAll(dropsToRemove);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        for (ItemStack item : keepDrops) {
            if(item.getItemMeta().getCustomModelData() == 1001) {
                Bukkit.broadcastMessage("Adding Pickaxe");
                event.getPlayer().getInventory().setItem(0, item);
            }
            if(item.getItemMeta().getCustomModelData() == 1002) {
                Bukkit.broadcastMessage("Adding Storage");
                event.getPlayer().getInventory().setItem(9, item);
            }
        }
    }
}
