package me.snowman.snowfight.arenamanager;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import me.snowman.snowfight.SnowFight;
import me.snowman.snowfight.managers.PluginManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class ArenaSetup implements Listener {
    private final ArenaManager arenaManager;
    private final ArenaSetupGUI arenaSetupGUI;
    private final SnowFight snowFight;
    private final ArenaFiles arenaFiles;
    private final PluginManager pluginManager;
    public ArenaSetup(ArenaManager arenaManager, ArenaSetupGUI arenaSetupGUI, SnowFight snowFight, ArenaFiles arenaFiles, PluginManager pluginManager){
        this.arenaManager = arenaManager;
        this.arenaSetupGUI = arenaSetupGUI;
        this.snowFight = snowFight;
        this.arenaFiles = arenaFiles;
        this.pluginManager = pluginManager;
    }

    private final Map<UUID, String> chatInput = new HashMap<>();

    //sets input for chat from the gui
    @EventHandler
    public void onGUISetup(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Arena arena = arenaManager.isEditing(player);
        if(arena != null) event.setCancelled(true);
        if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) return;
        if(event.getCurrentItem().equals(arenaSetupGUI.getNeededPlayers(player))){
            chatInput.put(player.getUniqueId(), "np");
        }else if(event.getCurrentItem().equals(arenaSetupGUI.getRedSpawn(player))){
            chatInput.put(player.getUniqueId(), "rs");
        }else if(event.getCurrentItem().equals(arenaSetupGUI.getWhiteSpawn(player))){
            chatInput.put(player.getUniqueId(), "ws");
        }else if(event.getCurrentItem().equals(arenaSetupGUI.getCenter(player))){
            chatInput.put(player.getUniqueId(), "c");
        }
        player.closeInventory();
        arenaManager.addEditing(player, arena);
    }

    //removes player from setup mode
    @EventHandler
    public void onGUIExit(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(arenaManager.isEditing(player) != null) arenaManager.removeEditing(player);
    }

    //gets chat input to set the arena info
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IncompleteRegionException {
        Player player = event.getPlayer();

        if(!chatInput.containsKey(player.getUniqueId())) return;
        event.setCancelled(true);
        switch(chatInput.get(player.getUniqueId())){
            case "np":
                arenaManager.isEditing(player).setNeededPlayers(Integer.parseInt(event.getMessage()));
                arenaFiles.saveArena(arenaManager.isEditing(player));
                break;
            case "rs":
                arenaManager.isEditing(player).setRedSpawn(player.getLocation());
                arenaFiles.saveArena(arenaManager.isEditing(player));
                break;
            case "ws":
                arenaManager.isEditing(player).setWhiteSpawn(player.getLocation());
                arenaFiles.saveArena(arenaManager.isEditing(player));
                break;
            case "c":
                Region region = pluginManager.getWorldEdit().getSession(player).getSelection(BukkitAdapter.adapt(player.getWorld()));
                arenaManager.isEditing(player).setCenter(region);
                arenaFiles.saveArena(arenaManager.isEditing(player));
                break;
        }
        chatInput.remove(player.getUniqueId());
        getServer().getScheduler().runTask(snowFight, () -> {
            player.openInventory(arenaSetupGUI.getSetup(player, arenaManager.isEditing(player)));
        });
    }
}


