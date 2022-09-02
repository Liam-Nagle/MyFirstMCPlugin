package myfirstmcplugin.myfirstmcplugin;

import myfirstmcplugin.myfirstmcplugin.CustomClasses.CustomEnchants;
import myfirstmcplugin.myfirstmcplugin.commands.ce;
import myfirstmcplugin.myfirstmcplugin.commands.storage;
import myfirstmcplugin.myfirstmcplugin.handlers.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyFirstMCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting up LiamsFirstPlugin");
        //Handlers

        new BlockHandler(this);
        new PlayerJoinHandler(this);
        new InventoryHandler(this);
        new PlayerDeathHandler(this);

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
