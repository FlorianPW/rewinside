package net.quatulo.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

}
