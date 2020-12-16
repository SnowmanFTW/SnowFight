package me.snowman.snowfight.arenamanager;

import me.snowman.snowfight.SnowFight;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class GameManager {
    private final SnowFight snowFight;
    public GameManager(SnowFight snowFight){
        this.snowFight = snowFight;
    }

    //methods for when the game starts, mid game, end etc.
    public void gameStart(Arena arena, Player player){
        countdownStart(arena);
        for(UUID uuid: arena.getPlayers()){
            Player players = Bukkit.getPlayer(uuid);
            players.sendMessage(player.getName() + " joined the game. (" + arena.getPlayers().size() + "/" + arena.getMaxPlayers() + ")");
        }
    }

    public void countdownStart(Arena arena){
        new BukkitRunnable(){
            @Override
            public void run() {
                if(arena.getPlayers().size() < arena.getNeededPlayers()){
                    cancel();
                    arena.setCountdown(10);
                    return;
                }
                if(arena.getCountdown() == 0){
                    for(UUID uuid: arena.getPlayers()){
                        Player player = getServer().getPlayer(uuid);
                        player.sendTitle( "Start", "", 5, 10, 5);
                    }
                    arena.setCountdown(10);
                    setTeams(arena);
                    tpTeams(arena);
                    cancel();
                    return;
                }
                for(UUID uuid: arena.getPlayers()){
                    Player player = getServer().getPlayer(uuid);
                    player.sendTitle(arena.getCountdown() + "", "", 5, 10, 5);
                }
                arena.setCountdown(arena.getCountdown() - 1);
            }
        }.runTaskTimerAsynchronously(snowFight, 0, 20);
    }

    private void setTeams(Arena arena){
        for(int i = 0; i < arena.getPlayers().size(); i++){
            if(i % 2 == 0){
                arena.getRedTeam().add(arena.getPlayers().get(i));
            }else arena.getWhiteTeam().add(arena.getPlayers().get(i));
        }
    }

    private void tpTeams(Arena arena){
        for(UUID uuid: arena.getRedTeam()){
            Player player = getServer().getPlayer(uuid);
            player.teleport(arena.getRedSpawn());
        }
        for(UUID uuid: arena.getWhiteTeam()){
            Player player = getServer().getPlayer(uuid);
            player.teleport(arena.getWhiteSpawn());
        }
    }
}
