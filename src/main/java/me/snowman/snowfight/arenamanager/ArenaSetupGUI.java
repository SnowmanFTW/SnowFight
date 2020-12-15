package me.snowman.snowfight.arenamanager;

import me.snowman.snowfight.builders.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class ArenaSetupGUI {
    private final ArenaManager arenaManager;
    public ArenaSetupGUI(ArenaManager arenaManager){
        this.arenaManager = arenaManager;
    }
    ItemStack whiteSpawn;
    ItemStack redArea;
    ItemStack whiteArea;

    //this makes the gui
    public Inventory getSetup(Player player, Arena arena){
        Inventory inv = Bukkit.createInventory(null, 27, "title");
        arenaManager.addEditing(player, arena);
        inv.setItem(9, getNeededPlayers(arena));
        inv.setItem(11, getRedSpawn(arena));
        inv.setItem(13, getWhiteSpawn(arena));
        inv.setItem(15, getRedArea(arena));
        inv.setItem(17, getWhiteArea(arena));
        return inv;
    }

    //items down below
    public ItemStack getNeededPlayers(Arena arena){
        if(arena.getNeededPlayers() == 0){
            return new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.RED + "Needed Players").setLore(Collections.singletonList(ChatColor.GRAY + "Not set")).build();
        }
        return new ItemBuilder(Material.GREEN_CONCRETE).setName(ChatColor.GREEN + "Needed Players").setLore(Collections.singletonList(ChatColor.GRAY + "Set (" + arena.getNeededPlayers() + ")")).build();
    }

    public ItemStack getRedSpawn(Arena arena){
        if(arena.getRedSpawn() == null){
            return new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.RED + "Red Spawn").setLore(Collections.singletonList(ChatColor.GRAY + "Not set")).build();
        }
        return new ItemBuilder(Material.GREEN_CONCRETE).setName(ChatColor.GREEN + "Red Spawn").setLore(Collections.singletonList(ChatColor.GRAY + "Set (" +
                arena.getRedSpawn().getBlockX() + "/" + arena.getRedSpawn().getBlockY() + "/" + arena.getRedSpawn().getBlockZ() + ")")).build();
    }

    public ItemStack getWhiteSpawn(Arena arena){
        if(arena.getWhiteSpawn() == null){
            return new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.RED + "White Spawn").setLore(Collections.singletonList(ChatColor.GRAY + "Not set")).build();
        }else return new ItemBuilder(Material.GREEN_CONCRETE).setName(ChatColor.GREEN + "White Spawn").setLore(Collections.singletonList(ChatColor.GRAY + "Set (" +
                arena.getWhiteSpawn().getBlockX() + "/" + arena.getWhiteSpawn().getBlockY() + "/" + arena.getWhiteSpawn().getBlockZ() + ")")).build();
    }

    public ItemStack getRedArea(Arena arena){
        if(arena.getRedArea() == null){
            return new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.RED + "Red area region").setLore(Collections.singletonList(ChatColor.GRAY + "Not set")).build();
        }else return new ItemBuilder(Material.GREEN_CONCRETE).setName(ChatColor.GREEN + "Red area region").setLore(Collections.singletonList(ChatColor.GRAY + "Set " + arena.getRedArea().toString())).build();
    }

    public ItemStack getWhiteArea(Arena arena){
        if(arena.getWhiteArea() == null){
            return new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.RED + "White area region").setLore(Collections.singletonList(ChatColor.GRAY + "Not set")).build();
        }else return new ItemBuilder(Material.GREEN_CONCRETE).setName(ChatColor.GREEN + "White area region").setLore(Collections.singletonList(ChatColor.GRAY + "Set " + arena.getWhiteArea().toString())).build();
    }
}
