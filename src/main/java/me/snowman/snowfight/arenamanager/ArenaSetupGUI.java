package me.snowman.snowfight.arenamanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArenaSetupGUI {
    private final ArenaManager arenaManager;
    public ArenaSetupGUI(ArenaManager arenaManager){
        this.arenaManager = arenaManager;
    }
    ItemStack neededPlayers;
    ItemStack redSpawn;
    ItemStack whiteSpawn;
    ItemStack center;
    ItemStack redBase;
    ItemStack whiteBase;

    public Inventory getSetup(Player player, Arena arena){
        Inventory inv = Bukkit.createInventory(null, 36, "title");
        arenaManager.addEditing(player, arena);
        inv.setItem(10, getNeededPlayers(player));
        inv.setItem(20, getRedSpawn(player));
        inv.setItem(12, getWhiteSpawn(player));
        inv.setItem(22, getCenter(player));
        return inv;
    }

    public ItemStack getNeededPlayers(Player player){
        Arena arena = arenaManager.isEditing(player);
        ItemMeta meta;
        if(arena.getNeededPlayers() == 0){
            neededPlayers = new ItemStack(Material.RED_CONCRETE);
            meta = neededPlayers.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Not set");
        }else{
            neededPlayers = new ItemStack(Material.GREEN_CONCRETE);
            meta = neededPlayers.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Set (" + arena.getNeededPlayers() + ")");
        }
        neededPlayers.setItemMeta(meta);
        return neededPlayers;
    }

    public ItemStack getRedSpawn(Player player){
        Arena arena = arenaManager.isEditing(player);
        if(arena.getRedSpawn() == null){
            redSpawn = new ItemStack(Material.RED_CONCRETE);
        }else redSpawn = new ItemStack(Material.GREEN_CONCRETE);
        return redSpawn;
    }

    public ItemStack getWhiteSpawn(Player player){
        Arena arena = arenaManager.isEditing(player);
        if(arena.getWhiteSpawn() == null){
            whiteSpawn = new ItemStack(Material.RED_CONCRETE);
        }else whiteSpawn = new ItemStack(Material.GREEN_CONCRETE);
        return whiteSpawn;
    }

    public ItemStack getCenter(Player player){
        Arena arena = arenaManager.isEditing(player);
        if(arena.getCenter() == null){
            center = new ItemStack(Material.RED_CONCRETE);
        }else center = new ItemStack(Material.GREEN_CONCRETE);
        return center;
    }

    private void getRedBase(){

    }

    private void getWhiteBase(){

    }
}
