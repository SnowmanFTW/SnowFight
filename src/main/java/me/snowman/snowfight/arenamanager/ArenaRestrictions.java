package me.snowman.snowfight.arenamanager;

import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ArenaRestrictions implements Listener {
    private final ArenaManager arenaManager;
    public ArenaRestrictions(ArenaManager arenaManager){
        this.arenaManager = arenaManager;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        int x = event.getBlock().getX();
        int y = event.getBlock().getY();
        int z = event.getBlock().getZ();
        if(arenaManager.getArena(player) == null) return;
        Arena arena = arenaManager.getArena(player);
        if(arena.getWhiteTeam().contains(player.getUniqueId()) && arena.getWhiteArea().contains(BlockVector3.at(x, y ,z))) return;
        if(arena.getRedTeam().contains(player.getUniqueId()) && arena.getRedArea().contains(BlockVector3.at(x, y ,z))) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(arenaManager.getArena(player) == null) return;
        event.setCancelled(true);
    }
}
