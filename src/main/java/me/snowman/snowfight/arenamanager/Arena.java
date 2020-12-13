package me.snowman.snowfight.arenamanager;

import com.sk89q.worldedit.internal.annotation.Selection;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//literally the arena object
public class Arena {
    private String name;
    private boolean enabled;
    private GameState state;
    private List<UUID> players = new ArrayList<>();
    private int neededPlayers;
    private Location redSpawn;
    private Location whiteSpawn;
    private Selection center;
    private Selection redBase;
    private Selection whiteBase;

    public enum GameState{
        WAITING, STARTING, INGAME, FINISHED
    }

    public Arena(String name, boolean enabled){
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public int getNeededPlayers() {
        return neededPlayers;
    }

    public Location getRedSpawn() {
        return redSpawn;
    }

    public void setRedSpawn(Location redSpawn) {
        this.redSpawn = redSpawn;
    }

    public Location getWhiteSpawn() {
        return whiteSpawn;
    }

    public void setWhiteSpawn(Location whiteSpawn){
        this.whiteSpawn = whiteSpawn;
    }

    public Selection getCenter() {
        return center;
    }

    public void setCenter(Selection center) {
        this.center = center;
    }

    public Selection getRedBase() {
        return redBase;
    }

    public void setRedBase(Selection redBase) {
        this.redBase = redBase;
    }

    public Selection getWhiteBase() {
        return whiteBase;
    }

    public void setWhiteBase(Selection whiteBase) {
        this.whiteBase = whiteBase;
    }

    public void setNeededPlayers(int neededPlayers) {
        this.neededPlayers = neededPlayers;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
