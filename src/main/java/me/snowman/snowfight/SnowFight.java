package me.snowman.snowfight;

import me.snowman.snowfight.arenamanager.ArenaFiles;
import me.snowman.snowfight.arenamanager.ArenaManager;
import me.snowman.snowfight.arenamanager.ArenaSetupGUI;
import me.snowman.snowfight.managers.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SnowFight extends JavaPlugin {



    //main lol
    public void onEnable(){
        final ArenaFiles arenaFiles = new ArenaFiles(this);
        final ArenaManager arenaManager = new ArenaManager(arenaFiles);
        final ArenaSetupGUI arenaSetupGUI = new ArenaSetupGUI(arenaManager);
        final PluginManager pluginManager = new me.snowman.snowfight.managers.PluginManager(this, arenaManager, arenaSetupGUI, arenaFiles);

        arenaFiles.setupArenas();
        arenaManager.loadArenas();
        pluginManager.registerCommands();
        pluginManager.registerEvents();
    }

    public void onDisable(){
    }
}
