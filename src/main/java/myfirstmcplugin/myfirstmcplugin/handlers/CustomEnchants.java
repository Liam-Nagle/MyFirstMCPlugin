package myfirstmcplugin.myfirstmcplugin.handlers;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;

import java.beans.EventHandler;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CustomEnchants {

   public static Map<String, EnchantHandler> enchants = new HashMap<String, EnchantHandler>();
    public static void register() {
        enchants.put("Explosive", new EnchantHandler("explosive", "Explosive", 1000));
        enchants.put("Jackhammer", new EnchantHandler("jackhammer", "Jackhammer", 1000));
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
