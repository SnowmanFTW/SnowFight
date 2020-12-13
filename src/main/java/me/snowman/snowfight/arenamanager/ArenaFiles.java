package me.snowman.snowfight.arenamanager;

import me.snowman.snowfight.SnowFight;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ArenaFiles {

    private File arenas;
    private FileConfiguration config;
    private final SnowFight snowFight;;

    public ArenaFiles(SnowFight snowFight){
        this.snowFight = snowFight;
    }


    public void setupArenas(){
        if(!snowFight.getDataFolder().exists()){
            snowFight.getDataFolder().mkdir();
        }
        arenas = new File(snowFight.getDataFolder(), "arenas.yml");

        if(!arenas.exists()){
            try{
                arenas.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(arenas);
    }

    public FileConfiguration getArenas(){
        if(arenas == null){
            arenas = new File(snowFight.getDataFolder(), "arenas.yml");
            config = YamlConfiguration.loadConfiguration(arenas);
        }
        return config;
    }

    public ConfigurationSection getArena(String name){
        if(arenas == null){
            arenas = new File(snowFight.getDataFolder(), "arenas.yml");
            config = YamlConfiguration.loadConfiguration(arenas);
        }
        return config.getConfigurationSection(name);
    }

    public void saveArena(Arena arena){
        getArenas().set(arena.getName() + ".Enabled", arena.isEnabled());
        getArenas().set(arena.getName() + ".NeededPlayers", arena.getNeededPlayers());
        getArenas().set(arena.getName() + ".RedSpawn", arena.getRedSpawn());
        getArenas().set(arena.getName() + ".WhiteSpawn", arena.getWhiteSpawn());
        getArenas().set(arena.getName() + ".Center", arena.getCenter());
        getArenas().set(arena.getName() + ".RedBase", arena.getRedBase());
        getArenas().set(arena.getName() + ".WhiteBase", arena.getWhiteBase());
        saveArenas();
    }

    public void saveArenas(){
        try{
            config.save(arenas);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
