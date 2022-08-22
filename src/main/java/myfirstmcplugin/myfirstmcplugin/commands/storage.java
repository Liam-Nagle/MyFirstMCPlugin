package myfirstmcplugin.myfirstmcplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class storage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("Delete")) {
                player.getInventory().setItem(9, new ItemStack(Material.AIR));
                Bukkit.broadcastMessage("Deleted Storage");
                return true;
            }
            return true;
        }
        return true;
    }
}
