package net.quatulo.lobby.listener;

import net.quatulo.lobby.utilities.LobbyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Block  implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player client = event.getPlayer();
        if(!LobbyManager.getBuildlist().contains(client)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player client = event.getPlayer();
        if(!LobbyManager.getBuildlist().contains(client)) {
            event.setCancelled(true);
        }
    }
}
