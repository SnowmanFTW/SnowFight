package me.snowman.snowfight.managers;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import me.snowman.snowfight.SnowFight;
import me.snowman.snowfight.arenamanager.*;
import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getServer;

public class PluginManager {
    private final SnowFight snowFight;
    private final ArenaManager arenaManager;
    private final ArenaSetupGUI arenaSetupGUI;
    private final ArenaFiles arenaFiles;

    public PluginManager(SnowFight snowFight, ArenaManager arenaManager, ArenaSetupGUI arenaSetupGUI, ArenaFiles arenaFiles){
        this.snowFight = snowFight;
        this.arenaManager = arenaManager;
        this.arenaSetupGUI = arenaSetupGUI;
        this.arenaFiles = arenaFiles;
    }

    //various plugin methods
    public void registerCommands(){
        snowFight.getCommand("snowfight").setExecutor(new me.snowman.snowfight.commands.SnowFight(arenaManager, arenaSetupGUI));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new ArenaSetup(arenaManager, arenaSetupGUI, snowFight, arenaFiles, this), snowFight);
        getServer().getPluginManager().registerEvents(new ArenaRestrictions(arenaManager), snowFight);
    }

    public WorldEditPlugin getWorldEdit(){
        return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }
}
