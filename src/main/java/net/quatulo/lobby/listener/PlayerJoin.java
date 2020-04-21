package net.quatulo.lobby.listener;

import net.quatulo.lobby.utilities.ItemBuilder;
import net.quatulo.lobby.utilities.LabySubtitles;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private void setInventory(Player client) {

        client.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK,1,10).setDisplayName("§6Spieler §8➜ §aAngezeigt").create());
        client.getInventory().setItem(2, new ItemBuilder(Material.FISHING_ROD,1,0).setDisplayName("§cEnterhaken").setUnbreakable(true).create());
        client.getInventory().setItem(4, new ItemBuilder(Material.GOLD_RECORD,1,0).setDisplayName("§6§lTeleporter").create());
        client.getInventory().setItem(6, new ItemBuilder(Material.NETHER_STAR,1,0).setDisplayName("§bSwitcher").create());
        client.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM,1,3).setOwner(client.getName()).setDisplayName("§aFreunde").create());

        if(client.hasPermission("core.vip")) {
            client.getInventory().setItem(22, new ItemBuilder(Material.NAME_TAG,1,0).setDisplayName("§5Nick §8➜ §aAktivieren").create());
            client.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerjoin(PlayerJoinEvent event) {
        Player client = event.getPlayer();
        setInventory(client);
        client.setHealthScale(20);
        client.setHealth(20);

        for (Player clients : Bukkit.getOnlinePlayers()) {
            new LabySubtitles().setSubtitle(clients, client.getUniqueId(), "§cKein tag gesetzt.");
        }

        if(client.hasPermission("core.team")) {
            client.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS,1,0).setColor(Color.RED).setDisplayName("§cTeam").create());
        }
    }
}
