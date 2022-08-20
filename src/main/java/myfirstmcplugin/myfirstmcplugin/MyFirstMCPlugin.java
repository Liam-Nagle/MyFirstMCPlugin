package myfirstmcplugin.myfirstmcplugin;

import myfirstmcplugin.myfirstmcplugin.handlers.BlockHandler;
import myfirstmcplugin.myfirstmcplugin.handlers.CustomEnchants;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyFirstMCPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting up LiamsFirstPlugin");
        new BlockHandler(this);
        new CustomEnchants("explosive", "Explosive", 1000);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting Down LiamsFirstPlugin");
    }
}
