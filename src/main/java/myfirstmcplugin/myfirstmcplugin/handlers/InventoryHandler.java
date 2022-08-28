package myfirstmcplugin.myfirstmcplugin.handlers;

import myfirstmcplugin.myfirstmcplugin.GUI.StorageGUI;
import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler implements Listener {

    public InventoryHandler(MyFirstMCPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        StorageGUI gui = new StorageGUI();
        //Stops player from moving certain items in their inventory
        int[] lockedSlots = {36, 9};

        if(event.getClickedInventory() == null || event.getCurrentItem().getItemMeta() == null) {
            return;
        }

        if(ArrayUtils.contains(lockedSlots, event.getRawSlot()) && event.isLeftClick()) {
            Bukkit.broadcastMessage("Locked Slots");
            event.setCancelled(true);
        }

        //Opens storageGUI if player right clicks storage.
        if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Storage") && event.isRightClick()) {
            Bukkit.broadcastMessage("Opens GUI");
            player.openInventory(gui.getInventory());
        }

        //Stops player from moving items within StorageGUI
        if(ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Storage")) {
            Bukkit.broadcastMessage("GUI Instance");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        //Stops player from moving certain items in their inventory
        int[] lockedSlots = {36, 9};
        if(event.getRawSlots().contains(lockedSlots)) {
            event.setCancelled(true);
        }

        //Stops player from moving items within StorageGUI
        if(event.getInventory().getHolder() instanceof StorageGUI) {
                event.setCancelled(true);
        }
    }
}
