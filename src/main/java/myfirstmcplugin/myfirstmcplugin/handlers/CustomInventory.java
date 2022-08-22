package myfirstmcplugin.myfirstmcplugin.handlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CustomInventory {

    private int capacity;
    private int maxCapacity;
    private HashMap<Material, Integer> items;

    public CustomInventory(int capacity, int maxCapacity, HashMap<Material, Integer> items) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        for (Map.Entry<Material, Integer> entry : items.entrySet()) {
            Material key = entry.getKey();
            Integer value = entry.getValue();
            this.items.put(key,value);
        }
    }

    public CustomInventory(int capacity, int maxCapacity) {
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setItems(HashMap<Material, Integer> items) {
        for (Map.Entry<Material, Integer> entry : items.entrySet()) {
            Material key = entry.getKey();
            Integer value = entry.getValue();
            this.items.clear();
            this.items.put(key,value);
        }
    }

    public HashMap<Material, Integer> getItems() {
        return items;
    }

    public void addItems(HashMap<Material, Integer> items) {
        for (Map.Entry<Material, Integer> entry : items.entrySet()) {
            Material key = entry.getKey();
            Integer value = entry.getValue();
            this.items.put(key,value);
        }
    }

    public void removeItems(ArrayList<Material> items) {
        for (Material item: items) {
            this.items.remove(item);
        }
    }

    public Integer getItemCount(Material material) {
        return items.get(material);
    }

    public void setItemCount(Material material, Integer amount) {
        items.put(material, items.get(material) + amount);
    }
}
