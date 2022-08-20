package myfirstmcplugin.myfirstmcplugin.handlers;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {
    public Enchantment enchant;

    public CustomEnchants(String namespace, String name, int maxLvl) {
        enchant = new EnchantHandler(namespace, name, maxLvl);
        register(enchant);
    }

    public static void register(Enchantment enchant) {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchant);
        if(!registered) {
            registerEnchantment(enchant);
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
