package net.quatulo.lobby;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import net.quatulo.lobby.cmds.CommandAdmin;
import net.quatulo.lobby.cmds.CommandBuild;
import net.quatulo.lobby.listener.*;
import net.quatulo.lobby.utilities.ArmorstandBuilder;
import net.quatulo.lobby.utilities.ItemBuilder;
import net.quatulo.lobby.utilities.PrefixBuilder;
import net.quatulo.lobby.utilities.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

    public static ArmorstandBuilder bedwars;
    private static Lobby instance;
    @Override
    public void onEnable() {
        instance = this;
        register();

    }

    @Override
    public void onDisable() {
        if(bedwars != null) { bedwars.remove(); }
        super.onDisable();
    }

    private void register() {
        getCommand("create").setExecutor(new CommandAdmin());
        getCommand("edit").setExecutor(new CommandBuild());

        // registered listener
        getServer().getPluginManager().registerEvents(new Block(),this);
        getServer().getPluginManager().registerEvents(new ClientListeners(),this);
        getServer().getPluginManager().registerEvents(new FinishRod(),this);
        getServer().getPluginManager().registerEvents(new LeavesDecay(),this);
        getServer().getPluginManager().registerEvents(new PlayerDamage(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new LabyMod(),this);
        getServer().getPluginManager().registerEvents(new Inventoryclick(),this);
        getServer().getPluginManager().registerEvents(new WeatherClear(),this);
    }

    public static Lobby getInstance() {
        return instance;
    }
}
