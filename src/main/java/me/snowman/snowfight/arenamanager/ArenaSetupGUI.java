package me.snowman.snowfight.arenamanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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

    //this makes the gui
    public Inventory getSetup(Player player, Arena arena){
        Inventory inv = Bukkit.createInventory(null, 36, "title");
        arenaManager.addEditing(player, arena);
        inv.setItem(10, getNeededPlayers(player));
        inv.setItem(20, getRedSpawn(player));
        inv.setItem(12, getWhiteSpawn(player));
        inv.setItem(22, getCenter(player));
        return inv;
    }

    //items down below
    public ItemStack getNeededPlayers(Player player){
        Arena arena = arenaManager.isEditing(player);
        ItemMeta meta;
        if(arena.getNeededPlayers() == 0){
            neededPlayers = new ItemStack(Material.RED_CONCRETE);
            meta = neededPlayers.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Needed Players");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Not set"));
        }else{
            neededPlayers = new ItemStack(Material.GREEN_CONCRETE);
            meta = neededPlayers.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Needed Players");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Set (" + arena.getNeededPlayers() + ")"));
        }
        neededPlayers.setItemMeta(meta);
        return neededPlayers;
    }

    public ItemStack getRedSpawn(Player player){
        Arena arena = arenaManager.isEditing(player);
        ItemMeta meta;
        if(arena.getRedSpawn() == null){
            redSpawn = new ItemStack(Material.RED_CONCRETE);
            meta = redSpawn.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Red Spawn");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Not set"));
        }else{
            redSpawn = new ItemStack(Material.GREEN_CONCRETE);
            meta = redSpawn.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Red Spawn");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Set (" + arena.getRedSpawn().getBlockX() + "/" + arena.getRedSpawn().getBlockY() + "/" + arena.getRedSpawn().getBlockZ() + ")"));
        }
        redSpawn.setItemMeta(meta);
        return redSpawn;
    }

    public ItemStack getWhiteSpawn(Player player){
        Arena arena = arenaManager.isEditing(player);
        ItemMeta meta;
        if(arena.getWhiteSpawn() == null){
            whiteSpawn = new ItemStack(Material.RED_CONCRETE);
            meta = whiteSpawn.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "White Spawn");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Not set"));
        }else{
            whiteSpawn = new ItemStack(Material.GREEN_CONCRETE);
            meta = whiteSpawn.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "White Spawn");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Set (" + arena.getWhiteSpawn().getBlockX() + "/" + arena.getWhiteSpawn().getBlockY() + "/" + arena.getWhiteSpawn().getBlockZ() + ")"));
        }
        whiteSpawn.setItemMeta(meta);
        return whiteSpawn;
    }

    public ItemStack getCenter(Player player){
        Arena arena = arenaManager.isEditing(player);
        ItemMeta meta;
        if(arena.getCenter() == null){
            center = new ItemStack(Material.RED_CONCRETE);
            meta = center.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Center Region");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Not Set"));
        }else{
            center = new ItemStack(Material.GREEN_CONCRETE);
            meta = center.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Center Region");
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Set " + arena.getCenter().toString()));
        }
        center.setItemMeta(meta);
        return center;
    }

    private void getRedBase(){

    }

    private void getWhiteBase(){

    }
}
