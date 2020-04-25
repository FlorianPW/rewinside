package net.quatulo.lobby.listener;

import net.quatulo.lobby.IHologram;
import net.quatulo.lobby.utilities.PrefixBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ClientListeners implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player client = event.getPlayer();
        if(event.isSneaking() == true) {

            IHologram hologram = new IHologram(client.getLocation(), "§8§m---------------", "§7", "§aWillkommen auf §6§lQuatulo", "§7", "§8§m---------------");
            hologram.showPlayerTemp(client,3);

        }
    }
    @EventHandler
    public void onFoodLeveChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }
}
