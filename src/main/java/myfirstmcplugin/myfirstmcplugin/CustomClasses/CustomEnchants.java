package myfirstmcplugin.myfirstmcplugin.CustomClasses;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CustomEnchants {

   public static Map<String, Enchantment> enchants = new HashMap<String, Enchantment>();
    public static void register() {
        enchants.put("Explosive", new PickaxeEnchants("explosive", "Explosive", 1000));
        enchants.put("Jackhammer", new PickaxeEnchants("jackhammer", "Jackhammer", 1000));
        enchants.put("Capacity", new StorageEnchants("capacity", "Capacity", 10000000));
        for (Enchantment enchant: enchants.values()) {
            boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchant);
            if(!registered) {
                registerEnchantment(enchant);
            }
        }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch(Exception e) {
            registered = false;
            e.printStackTrace();
        }
    }
}
