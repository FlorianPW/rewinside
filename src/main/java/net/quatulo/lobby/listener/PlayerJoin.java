package net.quatulo.lobby.listener;

import de.slikey.effectlib.util.ParticleEffect;
import net.quatulo.lobby.IHologram;
import net.quatulo.lobby.Lobby;
import net.quatulo.lobby.NBTItem;
import net.quatulo.lobby.utilities.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoin implements Listener {

    private void setInventory(Player client) {
        NBTItem item = new NBTItem(new ItemBuilder(Material.INK_SACK, 1, 10).setDisplayName("§6Spieler §8➜ §aAngezeigt").create());
        item.setString("LOBBY_INTERACT_EVENT", "playerhider");
        client.getInventory().setItem(0, item.getItem());

        client.getInventory().setItem(2, new ItemBuilder(Material.FISHING_ROD, 1, 0).setDisplayName("§cEnterhaken").setUnbreakable(true).create());

        item = new NBTItem(new ItemBuilder(Material.GOLD_RECORD, 1, 0).setDisplayName("§6§lTeleporter").create());
        item.setString("LOBBY_INTERACT_EVENT", "navigator");
        client.getInventory().setItem(4, item.getItem());

        client.getInventory().setItem(6, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§bSwitcher").create());
        client.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, 3).setOwner(client.getName()).setDisplayName("§aProfil").setNBTString("LOBBY_INTERACT_EVENT", "profile").create());

        if (client.hasPermission("core.vip")) {

            item = new NBTItem(new ItemBuilder(Material.NAME_TAG, 1, 0).setDisplayName("§5Nick §8➜ §aAktivieren").create());
            item.setString("LOBBY_INTERACT_EVENT", "nick");
            client.getInventory().setItem(22, item.getItem());
        }
    }

    @EventHandler
    public void onPlayerjoin(PlayerJoinEvent event) {
        Player client = event.getPlayer();
        client.getInventory().clear();
        client.teleport(new WarpManager("spawn").getWarp());
        setInventory(client);
        client.setHealthScale(20);
        client.setGameMode(GameMode.ADVENTURE);
        client.setHealth(20);
        new ScoreboardService(client).setScoreboard();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player hideOthers : new LobbyManager().getHidderlist()) {
                    hideOthers.hidePlayer(client);
                }
            }
        }.runTaskLater(Lobby.getInstance(), 10);

        if (client.hasPermission("core.team")) {
            client.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setColor(Color.RED).setDisplayName("§cTeam").create());
        }

        if(client.hasPermission("core.lobby.fly")) {
            client.setAllowFlight(true);
        }


        Location location = new Location(Bukkit.getWorld("leer1"), 732.0, 134.0, 721.0);
        IHologram hologram = new IHologram(location,
                "§6§lDein Ranking§7:",
                new PrefixBuilder("§cBedWars").build() + "#1",
                new PrefixBuilder("§3MLGRush").build() + "#2",
                new PrefixBuilder("§bArena").build() + "#3",
                new PrefixBuilder("§aBuildFFA").build() + "#4");
        hologram.showPlayer(client);
    }
}
