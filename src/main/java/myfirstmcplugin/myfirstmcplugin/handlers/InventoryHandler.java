package myfirstmcplugin.myfirstmcplugin.handlers;

import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryHandler implements Listener {

    public InventoryHandler(MyFirstMCPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        int[] lockedSlots = {0, 9};
        if(ArrayUtils.contains(lockedSlots, event.getRawSlot())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't move this item");
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        int[] lockedSlots = {0, 9};
        if(event.getRawSlots().contains(lockedSlots)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't move this item");
        }
    }
}
