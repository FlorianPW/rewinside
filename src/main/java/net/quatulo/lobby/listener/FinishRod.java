package net.quatulo.lobby.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class FinishRod implements Listener {

    @EventHandler
    public void handle(PlayerFishEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
            Player player = e.getPlayer();
            Fish fish = e.getHook();
            if (((e.getState().equals(PlayerFishEvent.State.IN_GROUND)) || (e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) || (e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) && (e.getPlayer().getWorld().getBlockAt(fish.getLocation().getBlockX(), fish.getLocation().getBlockY() - 1, fish.getLocation().getBlockZ()).getType() != Material.AIR)) {
                if (e.getPlayer().getWorld().getBlockAt(fish.getLocation().getBlockX(), fish.getLocation().getBlockY() - 1, fish.getLocation().getBlockZ()).getType() != Material.STATIONARY_WATER) {
                    Location lc = player.getLocation();
                    Location to = e.getHook().getLocation();
                    lc.setY(lc.getY() + 0.5D);
                    player.teleport(lc);
                    double d = to.distance(lc);
                    Vector v = player.getVelocity();
                    v.setX((1.0D + 0.07D * d) * (to.getX() - lc.getX()) / d);
                    v.setY((1.0D + 0.03D * d) * (to.getY() - lc.getY()) / d - 0.5D * -0.08D * d);
                    v.setZ((1.0D + 0.07D * d) * (to.getZ() - lc.getZ()) / d);
                    player.setVelocity(v);

                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 1F);
                }
            }
        }
    }
}
