package myfirstmcplugin.myfirstmcplugin;

import myfirstmcplugin.myfirstmcplugin.commands.ce;
import myfirstmcplugin.myfirstmcplugin.commands.storage;
import myfirstmcplugin.myfirstmcplugin.handlers.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MyFirstMCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting up LiamsFirstPlugin");
        //Handlers

        new BlockHandler(this);
        new PlayerJoinHandler(this);
        new InventoryHandler(this);

        //Commands

        this.getCommand("ce").setExecutor(new ce());
        this.getCommand("storage").setExecutor(new storage());

        //Other

        CustomEnchants.register();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting Down LiamsFirstPlugin");
    }
}
