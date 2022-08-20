//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package myfirstmcplugin.myfirstmcplugin.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import myfirstmcplugin.myfirstmcplugin.MyFirstMCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BlockHandler implements Listener {
    Player player;

    public BlockHandler(MyFirstMCPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak_Lowest(BlockBreakEvent event) {
        event.setDropItems(false);
        this.player = event.getPlayer();
        BreakDirection direction = BlockHandler.BreakDirection.getFacingDirection(this.player);
        if(this.player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)) {
            ExplosiveEnchant(direction, event);
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
    public void ExplosiveEnchant(BreakDirection direction, BlockBreakEvent event) {
        List<Location> blocks = direction.generateSphere(event.getBlock(), 5, 3);
        int blocksDestroyed = DestroyBlocks(blocks, event.getPlayer());
        Bukkit.broadcastMessage("Explosive Proc'd: " + blocksDestroyed);
    }

    public int DestroyBlocks(List<Location> blocks, Player player) {
        Iterator var6 = blocks.iterator();
        int blocksDestroyed = 0;
        while (var6.hasNext()) {
            Location location = (Location) var6.next();
            Block block = location.getBlock();
            Material material = location.getBlock().getType();
            if (block.getType() != Material.BEDROCK && block.getType() != Material.AIR && !block.isLiquid()) {
                location.getBlock().setType(Material.AIR);
                player.getInventory().addItem(new ItemStack[]{new ItemStack(material, 1)});
                blocksDestroyed++;
            }
        }
        return blocksDestroyed;
    }
}