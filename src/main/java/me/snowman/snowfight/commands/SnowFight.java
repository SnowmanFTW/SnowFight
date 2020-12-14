package me.snowman.snowfight.commands;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.SessionOwner;
import com.sk89q.worldedit.world.World;
import me.snowman.snowfight.arenamanager.Arena;
import me.snowman.snowfight.arenamanager.ArenaManager;
import me.snowman.snowfight.arenamanager.ArenaSetupGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Vector;

public class SnowFight implements CommandExecutor {
    private final ArenaManager arenaManager;
    private final ArenaSetupGUI arenaSetupGUI;

    public SnowFight(ArenaManager arenaManager, ArenaSetupGUI arenaSetupGUI){
        this.arenaManager = arenaManager;
        this.arenaSetupGUI = arenaSetupGUI;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            //show help
            return true;
        }
        if(!(sender instanceof Player)){
            //show console help
            return true;
        }
        Player player = (Player) sender;
        switch(args[0]){
            case "create":
                if(args.length < 2){
                    //show help
                    break;
                }
                arenaManager.createArena(args[1]);
                break;
            case "setup":
                if(args.length < 2){
                    //show help
                    break;
                }
                if(arenaManager.getArena(args[1]) == null){
                    for(Arena arena: arenaManager.getArenas()){
                        player.sendMessage(arena.getName());
                    }
                    break;
                }
                player.openInventory(arenaSetupGUI.getSetup(player, arenaManager.getArena(args[1])));
                break;
            case "delete":
                if(args.length < 2){
                    //show help
                    break;
                }
                if(arenaManager.getArena(args[1]) == null){
                    for(Arena arena: arenaManager.getArenas()){
                        player.sendMessage(arena.getName());
                    }
                    break;
                }
                arenaManager.deleteArena(args[1]);
                break;
            case "list":
                for(Arena arena: arenaManager.getArenas()){
                    player.sendMessage(arena.getName());
                }
                break;
            case "join":
                if(args.length < 2){
                    //show help
                    break;
                }
                if(arenaManager.getArena(args[1]) == null){
                    for(Arena arena: arenaManager.getArenas()){
                        player.sendMessage(arena.getName());
                    }
                    break;
                }
                arenaManager.addPlayer(player, args[1]);
                break;
//            case "selectiontest":
//                try {
//                    //Region region = worldEditPlugin.getSession(player).getSelection(BukkitAdapter.adapt(player.getWorld()));
//                    //Region region2 = new CuboidRegion(BlockVector3.at(), BlockVector3.at())
//                    //player.sendMessage(region.toString());
//                } catch (IncompleteRegionException e) {
//                    e.printStackTrace();
//                }
//                break;
        }
        return true;
    }
}
