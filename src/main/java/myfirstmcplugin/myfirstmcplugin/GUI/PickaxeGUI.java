package myfirstmcplugin.myfirstmcplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PickaxeGUI {

    private final Inventory inv;

    public PickaxeGUI() {
        inv = Bukkit.createInventory(null, 45, "Storage"); //9,18,27,36,45,54

        initaliseItems();
    }

    private void initaliseItems() {
        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, createGuiItem(Material.GRAY_STAINED_GLASS));
        }
    }

    private ItemStack createGuiItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createGuiItem(Material material, String name) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;
    }

    private ItemStack createGuiItem(Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        return item;
    }


}
