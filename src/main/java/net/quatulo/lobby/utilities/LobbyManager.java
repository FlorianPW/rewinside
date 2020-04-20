package net.quatulo.lobby.utilities;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyManager {

    private static FileBuilder fileBuilder = new FileBuilder("plugins//Lobby//spawns.yml");

    private static List<Player> buildlist = new ArrayList<>();


    public static List<Player> getBuildlist() {
        return buildlist;
    }

    public static FileBuilder getFileBuilder() {
        return fileBuilder;
    }
}
