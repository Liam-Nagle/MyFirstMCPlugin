//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package myfirstmcplugin.myfirstmcplugin.handlers;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class BlockHandler implements Listener {
    Player player;

    public BlockHandler(MyFirstMCPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak_Lowest(BlockBreakEvent event) {
        event.setDropItems(false);
        player = event.getPlayer();
        BreakDirection direction = BlockHandler.BreakDirection.getFacingDirection(player);
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        int enchantLevel = player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(CustomEnchants.enchants.get("Explosive"));
        double enchantChance = (double)enchantLevel/(double)20000; //this makes enchants 0.5% per 100 lvls. (20000)
        DestroyBlocks(event.getBlock().getLocation(), player);
        if(isBetween(randomNum, 0, (int)(enchantChance*10000))) {
            if(CheckEnchant("Explosive", event)) {
                if(enchantLevel >= 1000) {
                    ExplosiveEnchant(direction,event, 20, 20 );
                } else if (enchantLevel >= 500) {
                    ExplosiveEnchant(direction,event, 10, 10 );
                } else if (enchantLevel >= 100) {
                    ExplosiveEnchant(direction,event, 5, 5);
                } else if (enchantLevel == 0) {
                    return;
                }
            }
        }
        if(isBetween(randomNum, 501, (int)(enchantChance*10000)+501)) {
            Bukkit.broadcastMessage("JACKHAMMER PROC");
        }
    }

    public static enum BreakDirection {
        NORTH(0, 0, -1),
        SOUTH(0, 0, 1),
        EAST(1, 0, 0),
        WEST(-1, 0, 0),
        UP(0, 1, 0),
        DOWN(0, -1, 0);

        private Vector addition;
        private boolean isX;
        private boolean isY;
        private boolean isZ;

        private BreakDirection(int x, int y, int z) {
            this.addition = new Vector(x, y, z);
            this.isX = x != 0;
            this.isY = y != 0;
            this.isZ = z != 0;
        }

        public static BreakDirection getFacingDirection(Player player) {
            Vector dir = player.getLocation().getDirection().normalize();
            double x = dir.getX();
            double y = dir.getY();
            double z = dir.getZ();
            if (Math.abs(y) > 0.5) {
                return y > 0.0 ? UP : DOWN;
            } else if (Math.abs(x) > 0.5) {
                return x > 0.0 ? EAST : WEST;
            } else {
                return z > 0.0 ? SOUTH : NORTH;
            }
        }

        public List<Location> generateSphere(Block start, int radius, int radiusDown) {
            List<Location> result = new ArrayList<Location>();
            int bX = start.getX();
            int bY = start.getY();
            int bZ = start.getZ();

            for (int x = bX - radius; x <= bX + radius; ++x) {
                for (int y = bY - radiusDown; y <= bY + radiusDown; ++y) {
                    for (int z = bZ - radius; z <= bZ + radius; ++z) {
                        double distance = (double) (((bX - x) * (bX - x)) + ((bZ - z) * (bZ - z)) + ((bY - y) * (bY - y)));
                        if (distance < (double) (radius * radius) && !(false && distance < (double) ((radius - 1) * (radius - 1)))) {
                            Location l = new Location(start.getWorld(), x, y, z);
                            result.add(l);
                        }
                    }
                }
            }
            return result;
        }
    }
    public void ExplosiveEnchant(BreakDirection direction, BlockBreakEvent event, int radius, int radiusDown) {
        List<Location> blocks = direction.generateSphere(event.getBlock(), radius, radiusDown);
        int blocksDestroyed = DestroyBlocks(blocks, event.getPlayer());
        Bukkit.broadcastMessage("Explosive Proc'd: " + blocksDestroyed);
    }

    public int DestroyBlocks(List<Location> blocks, Player player) {
        Iterator var6 = blocks.iterator();
        int blocksDestroyed = 0;
        HashMap<Material, Integer> items = new HashMap<>();
        while (var6.hasNext()) {
            Location location = (Location) var6.next();
            Block block = location.getBlock();
            Material material = location.getBlock().getType();
            if (block.getType() != Material.BEDROCK && block.getType() != Material.AIR && !block.isLiquid()) {
                location.getBlock().setType(Material.AIR);
                items.merge(material, 1, Integer::sum);
                blocksDestroyed++;
            }
        }
        UpdateStorageBlocks(player.getInventory().getItem(9), items);
        return blocksDestroyed;
    }

    public int DestroyBlocks(Location location, Player player) {
        int blocksDestroyed = 0;
        HashMap<Material, Integer> items = new HashMap<>();
        Block block = location.getBlock();
        Material material = location.getBlock().getType();
            if (block.getType() != Material.BEDROCK && block.getType() != Material.AIR && !block.isLiquid()) {
                location.getBlock().setType(Material.AIR);
                items.merge(material, 1, Integer::sum);
                blocksDestroyed++;
            }
        UpdateStorageBlocks(player.getInventory().getItem(9), items);
        return blocksDestroyed;
    }

    public boolean CheckEnchant(String enchant, BlockBreakEvent event) {
        if(player.getInventory().getItemInMainHand() == null)
            return false; //Checks if player has item in main hand
        if(!player.getInventory().getItemInMainHand().hasItemMeta())
            return false; //If item in main hand doesn't have item meta
        if(!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.enchants.get(enchant)))
            return false; //If the player doesn't have the enchant
        //if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
            //return false; //If player is in Creative or Spectator
        //if(player.getInventory().firstEmpty() == -1)
        //    return false; //If player inventory is full so no need to send blocks
        if(event.getBlock().getState() instanceof Container)
            return false; //If the player is in a container. Chest etc
        return true;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    
    private void UpdateStorageBlocks(ItemStack storage, HashMap<Material, Integer> items) {
        ItemMeta meta = storage.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        HashMap<Material, Integer> oldItems = new HashMap<>();
        if(meta.hasLore())
            for(String l : meta.getLore())
                lore.add(l);
        int totalValue = 0;
        int maxCapacity = storage.getItemMeta().getEnchantLevel(CustomEnchants.enchants.get("Capacity"));
        for (Map.Entry<Material, Integer> entry : items.entrySet()) {
            Material key = entry.getKey();
            String updatedKey = key.name().replaceAll("_", " ").substring(0,1).toUpperCase() + key.name().replaceAll("_", " ").substring(1).toLowerCase();
            Integer value = entry.getValue();
            for(int i = 0; i<lore.size();i++) {
                if(lore.get(i).contains(updatedKey)) {
                    oldItems.put(key, Integer.parseInt(lore.get(i).replaceAll("[^0-9?!\\\\.]","")));
                    lore.remove(i);
                }
            }
            Integer updatedValue = oldItems.getOrDefault(key, 0) + value;
            updatedKey = key.name().replaceAll("_", " ").substring(0,1).toUpperCase() + key.name().replaceAll("_", " ").substring(1).toLowerCase();
            lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "| " + ChatColor.WHITE + updatedKey + " " + String.valueOf(updatedValue));
            meta.setLore(lore);
            storage.setItemMeta(meta);
        }
    }
}