package net.quatulo.lobby.listener;

import net.labymod.serverapi.bukkit.event.LabyModPlayerJoinEvent;
import net.quatulo.lobby.Lobby;
import net.quatulo.lobby.utilities.LabySubtitles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class LabyMod implements Listener {

    @EventHandler
    public void onLabyJoin(LabyModPlayerJoinEvent event) {

        new BukkitRunnable() {
            @Override
            public void run() {
                Player client = event.getPlayer();
                for (Player clients : Bukkit.getOnlinePlayers()) {
                    new LabySubtitles().setSubtitle(clients, client.getUniqueId(), "§b§lLabbyMod User");
                }
            }
        }.runTaskLater(Lobby.getInstance(), 5);
    }
}
