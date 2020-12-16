package me.snowman.snowfight.arenamanager;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import me.snowman.snowfight.managers.PluginManager;
import org.bukkit.entity.Player;

import java.util.*;

public class ArenaManager {
    private final ArenaFiles arenaFiles;
    private final GameManager gameManager;

    private List<Arena> arenas = new ArrayList<>();
    private Map<UUID, Arena> isEditing = new HashMap<>();

    public ArenaManager(ArenaFiles arenaFiles, GameManager gameManager){
        this.arenaFiles = arenaFiles;
        this.gameManager = gameManager;
    }


    //returns all the arenas
    public List<Arena> getArenas(){
        return arenas;
    }

    //returns a specified arena, return null if it doesnt exist
    public Arena getArena(String name){
        for(Arena arena: arenas){
            if(arena.getName().equalsIgnoreCase(name)){
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(Player player){
        for(Arena arena: arenas){
            if(arena.getPlayers().contains(player.getUniqueId())){
                return arena;
            }
        }
        return null;
    }

    //adds a player to an arena
    public void addPlayer(Player player, String arenaName){
        if(getArena(arenaName) == null) return;
         if(player == null) return;

         Arena arena = getArena(arenaName);
         if(arena.getPlayers().contains(player.getUniqueId())) return;
         arena.getPlayers().add(player.getUniqueId());
         gameManager.gameStart(arena, player);
    }

    //removes a player from the arena
    public void removePlayer(Player player){
        for(Arena arena: arenas){
            if(arena.getPlayers().contains(player.getUniqueId())){
                arena.getPlayers().remove(player.getUniqueId());
            }
        }
    }

    //creates an arena
    public void createArena(String name){
        for(Arena arena: arenas){
            if(arena.getName().equalsIgnoreCase(name)){
                //arena exists
                return;
            }
        }
        Arena arena = new Arena(name, false);
        arenas.add(arena);
        arenaFiles.saveArena(arena);
    }

    //loads the arenas from the arena file
    public void loadArenas(){
        for(String arenaName: arenaFiles.getArenas().getKeys(false)){
            Arena arena =  new Arena(arenaName, arenaFiles.getArena(arenaName).getBoolean("Enabled"));
            arena.setNeededPlayers(arenaFiles.getArena(arenaName).getInt("NeededPlayers"));
            arena.setMaxPlayers(arenaFiles.getArena(arenaName).getInt("MaxPlayers"));
            arena.setRedSpawn(arenaFiles.getArena(arenaName).getLocation("RedSpawn"));
            arena.setWhiteSpawn(arenaFiles.getArena(arenaName).getLocation("WhiteSpawn"));
            if(arenaFiles.getArena(arenaName).getString("RedArea") != null){
                arena.setRedArea(new CuboidRegion(deserialize(arenaFiles.getArena(arenaName).getString("RedArea.MinimumPoint")), deserialize(arenaFiles.getArena(arenaName).getString("RedArea.MaximumPoint"))));
            }
            if(arenaFiles.getArena(arenaName).getString("WhiteArea") != null){
                arena.setWhiteArea(new CuboidRegion(deserialize(arenaFiles.getArena(arenaName).getString("WhiteArea.MinimumPoint")), deserialize(arenaFiles.getArena(arenaName).getString("WhiteArea.MaximumPoint"))));
            }

            getArenas().add(arena);
        }
    }

    //deletes an arena
    public void deleteArena(String name){
        arenas.removeIf(arena -> arena.getName().equalsIgnoreCase(name));
        arenaFiles.getArenas().set(name, null);
        arenaFiles.saveArenas();
    }

    //checks is player is in setup mode
    public Arena isEditing(Player player){
        if(isEditing.containsKey(player.getUniqueId())){
            return isEditing.get(player.getUniqueId());
        }
        return null;
    }

    //adds the player to setup mode
    public void addEditing(Player player, Arena arena){
        isEditing.put(player.getUniqueId(), arena);
    }

    //removes the player from setup mode
    public void removeEditing(Player player){
        isEditing.remove(player.getUniqueId());
    }

    public BlockVector3 deserialize(String string){
        String[] coords = string.replace("(", "").replace(")", "").split(", ");
        return BlockVector3.at(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
    }


}
