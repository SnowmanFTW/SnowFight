package me.snowman.snowfight.arenamanager;

import me.snowman.snowfight.SnowFight;
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
    public ArenaSetup(ArenaManager arenaManager, ArenaSetupGUI arenaSetupGUI, SnowFight snowFight, ArenaFiles arenaFiles){
        this.arenaManager = arenaManager;
        this.arenaSetupGUI = arenaSetupGUI;
        this.snowFight = snowFight;
        this.arenaFiles = arenaFiles;
    }

    private final Map<UUID, String> chatInput = new HashMap<>();

    @EventHandler
    public void onGUISetup(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Arena arena = arenaManager.isEditing(player);
        if(arena != null) event.setCancelled(true);
        if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) return;
        if(event.getCurrentItem().equals(arenaSetupGUI.getNeededPlayers(player))){
            chatInput.put(player.getUniqueId(), "np");
            player.closeInventory();
            arenaManager.addEditing(player, arena);
        }
    }

    @EventHandler
    public void onGUIExit(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(arenaManager.isEditing(player) != null) arenaManager.removeEditing(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        if(!chatInput.containsKey(player.getUniqueId())) return;
        if(chatInput.get(player.getUniqueId()).equalsIgnoreCase("np")){
            event.setCancelled(true);
            chatInput.remove(player.getUniqueId());
            arenaManager.isEditing(player).setNeededPlayers(Integer.parseInt(event.getMessage()));
            arenaFiles.getArena(arenaManager.isEditing(player).getName()).set("NeededPlayers", event.getMessage());
            arenaFiles.saveArena(arenaManager.isEditing(player));
            getServer().getScheduler().runTask(snowFight, () -> {
                player.openInventory(arenaSetupGUI.getSetup(player, arenaManager.isEditing(player)));
            });
        }
    }
}


