package net.quatulo.lobby.listener;

import com.avaje.ebean.validation.Email;
import net.quatulo.lobby.Lobby;
import net.quatulo.lobby.NBTItem;
import net.quatulo.lobby.utilities.ItemBuilder;
import net.quatulo.lobby.utilities.LobbyManager;
import net.quatulo.lobby.utilities.PrefixBuilder;
import net.quatulo.lobby.utilities.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class Inventoryclick implements Listener {

    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Player client = (Player) event.getWhoClicked();
        if (!LobbyManager.getBuildlist().contains(client)) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        NBTItem item = new NBTItem(event.getCurrentItem());
        if(item.hasKey("LOBBY_INVENTORY_EVENT")) {
            switch (item.getString("LOBBY_INVENTORY_EVENT")) {
                case "nick":
                    System.out.println("NICK_TOOL: Scanned");
                case "WARP_SPAWN":
                    client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                    client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                    client.playEffect(client.getLocation(), Effect.SPLASH, 1000);
                    client.closeInventory();
                    Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                        public void run() {
                            client.teleport(new WarpManager("Spawn").getWarp());

                            client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                        }
                    }, 15L);
                    client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun am §rSpawn");
                    client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);

                    break;
                case "WARP_BEDWARS":

                    client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                    client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                    client.playEffect(client.getLocation(), Effect.SPLASH, 1000);
                    client.closeInventory();
                    Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                        public void run() {
                            client.teleport(new WarpManager("bedwars").getWarp());

                            client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                        }
                    }, 15L);
                    client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §cBedWars");
                    client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);

                    break;
                case "WARP_MLGRUSH":

                    client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                    client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                    client.playEffect(client.getLocation(), Effect.SPLASH, 1000);
                    client.closeInventory();
                    Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                        public void run() {
                            client.teleport(new WarpManager("mlgrush").getWarp());

                            client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                        }
                    }, 15L);
                    client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §3MLGRush");
                    client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);

                    break;

                case "WARP_BUILDFFA":

                    client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                    client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                    client.playEffect(client.getLocation(), Effect.SPLASH, 1000);
                    client.closeInventory();
                    Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                        public void run() {
                            client.teleport(new WarpManager("bedwars").getWarp());

                            client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                        }
                    }, 15L);
                    client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §aBuildFFA");
                    client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);

                    break;

                case "WARP_ARENA":

                    client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                    client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                    client.playEffect(client.getLocation(), Effect.SPLASH, 1000);
                    client.closeInventory();
                    Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                        public void run() {
                            client.teleport(new WarpManager("arena").getWarp());

                            client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                        }
                    }, 15L);
                    client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §bArena");
                    client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);

                    break;

            }
        }
    }
}
