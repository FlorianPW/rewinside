package net.quatulo.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeavesDecay implements Listener {

    @EventHandler
    public void onleaves(LeavesDecayEvent event) {
        event.setCancelled(true);
    }
}
