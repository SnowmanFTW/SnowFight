package me.snowman.snowfight.arenamanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GameManager {

    //methods for when the game starts, mid game, end etc.
    public void gameStart(Arena arena, Player player){
        if(arena.getNeededPlayers() == arena.getPlayers().size()){

        }
        for(UUID uuid: arena.getPlayers()){
            Player players = Bukkit.getPlayer(uuid);
            players.sendMessage(player.getName() + " joined the game. (" + arena.getPlayers().size() + "/" + arena.getMaxPlayers() + ")");
        }
    }

    public void countdownStart(Arena arena){

    }
}
