package me.snowman.snowfight.arenamanager;

import org.bukkit.entity.Player;

import java.util.*;

public class ArenaManager {
    private final ArenaFiles arenaFiles;

    private List<Arena> arenas = new ArrayList<>();
    private Map<UUID, Arena> isEditing = new HashMap<>();

    public ArenaManager(ArenaFiles arenaFiles){
        this.arenaFiles = arenaFiles;
    }


    public List<Arena> getArenas(){
        return arenas;
    }

    public Arena getArena(String name){
        for(Arena arena: arenas){
            if(arena.getName().equalsIgnoreCase(name)){
                return arena;
            }
        }
        return null;
    }

    public void addPlayer(Player player, String arenaName){
        if(getArena(arenaName) == null) return;
         if(player == null) return;

         Arena arena = getArena(arenaName);
         arena.getPlayers().add(player.getUniqueId());
    }

    public void removePlayer(Player player){
        for(Arena arena: arenas){
            if(arena.getPlayers().contains(player.getUniqueId())){
                arena.getPlayers().remove(player.getUniqueId());
            }
        }
    }

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

    public void loadArenas(){
        for(String arenaName: arenaFiles.getArenas().getKeys(false)){
            Arena arena =  new Arena(arenaName, arenaFiles.getArena(arenaName).getBoolean("Enabled"));
            arena.setNeededPlayers(arenaFiles.getArena(arenaName).getInt("NeededPlayers"));
            getArenas().add(arena);
        }
    }

    public void deleteArena(String name){
        arenas.removeIf(arena -> arena.getName().equalsIgnoreCase(name));
        arenaFiles.getArenas().set(name, null);
        arenaFiles.saveArenas();
    }

    public Arena isEditing(Player player){
        if(isEditing.containsKey(player.getUniqueId())){
            return isEditing.get(player.getUniqueId());
        }
        return null;
    }

    public void addEditing(Player player, Arena arena){
        isEditing.put(player.getUniqueId(), arena);
    }

    public void removeEditing(Player player){
        isEditing.remove(player.getUniqueId());
    }


}
