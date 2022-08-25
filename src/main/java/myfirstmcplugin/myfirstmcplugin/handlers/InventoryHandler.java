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
        int[] lockedSlots = {0, 9};
        if(ArrayUtils.contains(lockedSlots, event.getRawSlot()) && event.isLeftClick()) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't move this item");
        }

        if(event.getInventory().getHolder() instanceof StorageGUI) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();

            // verify current item is not null
            if (clickedItem == null || clickedItem.getType().isAir()) return;

            Player p = (Player) event.getWhoClicked();

            // Using slots click is a best option for your inventory click's
            p.sendMessage("You clicked at slot " + event.getRawSlot());
        }

        if(event.getCurrentItem().getItemMeta().getDisplayName() == "Storage" && event.isRightClick()); {
            StorageGUI gui = new StorageGUI();
            player.openInventory(gui.getInventory());
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

        if(event.getInventory().getHolder() instanceof StorageGUI) {
                event.setCancelled(true);
        }
    }
}
