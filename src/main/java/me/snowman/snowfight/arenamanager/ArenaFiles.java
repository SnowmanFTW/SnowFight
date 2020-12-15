package me.snowman.snowfight.arenamanager;

import me.snowman.snowfight.SnowFight;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class ArenaFiles {

    private File arenas;
    private FileConfiguration config;
    private final SnowFight snowFight;;

    public ArenaFiles(SnowFight snowFight){
        this.snowFight = snowFight;
    }

    //makes the plugin folder + arenas.yml file in case it doesnt exist
    public void setupArenas(){
        if(!snowFight.getDataFolder().exists()){
            snowFight.getDataFolder().mkdir();
        }
        arenas = new File(snowFight.getDataFolder(), "arenas.yml");

        if(!arenas.exists()){
            try{
                arenas.createNewFile();
                config = YamlConfiguration.loadConfiguration(arenas);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(config == null){
            config = YamlConfiguration.loadConfiguration(arenas);
        }
    }

    //gets the arena file
    public FileConfiguration getArenas(){
        return config;
    }

    //gets an arena section in the file
    public ConfigurationSection getArena(String name){
        if(arenas == null){
            arenas = new File(snowFight.getDataFolder(), "arenas.yml");
            config = YamlConfiguration.loadConfiguration(arenas);
        }
        return config.getConfigurationSection(name);
    }

    //saves an arena to the file
    public void saveArena(Arena arena){
        getArenas().set(arena.getName() + ".Enabled", arena.isEnabled());
        getArenas().set(arena.getName() + ".NeededPlayers", arena.getNeededPlayers());
        getArenas().set(arena.getName() + ".RedSpawn", arena.getRedSpawn());
        getArenas().set(arena.getName() + ".WhiteSpawn", arena.getWhiteSpawn());
        if(arena.getRedArea() != null) {
            getArenas().set(arena.getName() + ".RedArea.MinimumPoint", arena.getRedArea().getMinimumPoint().toString());
            getArenas().set(arena.getName() + ".RedArea.MaximumPoint", arena.getRedArea().getMaximumPoint().toString());
        }
        if(arena.getWhiteArea() != null) {
            getArenas().set(arena.getName() + ".WhiteArea.MinimumPoint", arena.getWhiteArea().getMinimumPoint().toString());
            getArenas().set(arena.getName() + ".WhiteArea.MaximumPoint", arena.getWhiteArea().getMaximumPoint().toString());
        }
        saveArenas();
    }

    //save the file
    public void saveArenas(){
        try{
            config.save(arenas);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
