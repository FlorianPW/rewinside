package net.quatulo.lobby.listener;

import net.quatulo.lobby.utilities.PrefixBuilder;
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
            client.sendMessage(new PrefixBuilder("§3Lobby").build() + "lobby_mlg_final_listener");
        }
    }
    @EventHandler
    public void onFoodLeveChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }
}
