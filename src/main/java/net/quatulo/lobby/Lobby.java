package net.quatulo.lobby;

import net.quatulo.lobby.cmds.CommandAdmin;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

    private static Lobby instance;

    private final String PREFIX = "";

    @Override
    public void onEnable() {
        init();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void init() {
        getCommand("admin").setExecutor(new CommandAdmin());
        getCommand("build").setExecutor(new CommandAdmin());
    }

    public static Lobby getInstance() {
        return instance;
    }

    public String getPREFIX() {
        return PREFIX;
    }
}
