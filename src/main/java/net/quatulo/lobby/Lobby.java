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
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Lobby extends JavaPlugin {

    public static ArmorstandBuilder bedwars;
    private static Lobby instance;
    @Override
    public void onEnable() {
        instance = this;
        sendConsoleMessage();
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

    private void sendConsoleMessage() {
        System.out.println(" _        _______  ______   ______            _______           _______ _________ _______  _______ ");
        System.out.println("( \\      (  ___  )(  ___ \\ (  ___ \\ |\\     /|(  ____ \\|\\     /|(  ____ \\\\__   __/(  ____ \\(       )");
        System.out.println("| (      | (   ) || (   ) )| (   ) )( \\   / )| (    \\/( \\   / )| (    \\/   ) (   | (    \\/| () () |");
        System.out.println("| |      | |   | || (__/ / | (__/ /  \\ (_) / | (_____  \\ (_) / | (_____    | |   | (__    | || || |");
        System.out.println("| |      | |   | ||  __ (  |  __ (    \\   /  (_____  )  \\   /  (_____  )   | |   |  __)   | |(_)| |");
        System.out.println("| |      | |   | || (  \\ \\ | (  \\ \\    ) (         ) |   ) (         ) |   | |   | (      | |   | |");
        System.out.println("| (____/\\| (___) || )___) )| )___) )   | |   /\\____) |   | |   /\\____) |   | |   | (____/\\| )   ( |");
        System.out.println("(_______/(_______)|/ \\___/ |/ \\___/    \\_/   \\_______)   \\_/   \\_______)   )_(   (_______/|/     \\|");
        System.out.println("Coded by Quatulo Network Development Team");
    }

    private void setupScheduler() {
        BukkitScheduler scheduler = Bukkit.getScheduler();

//        scheduler.runTaskTimerAsynchronously(this, ScoreboardService::update, 0L, 4L);
        scheduler.runTaskTimerAsynchronously(this, () -> {
            for (Entity entity : Bukkit.getWorld("world").getEntities()) {
                if (entity instanceof Monster || entity instanceof Animals || entity instanceof Item) {
                    entity.remove();
                }
            }
        }, 10L, 10L);

    }

}
