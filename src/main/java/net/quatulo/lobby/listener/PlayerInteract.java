package net.quatulo.lobby.listener;

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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener {
    private void setInventory(Player client) {
        NBTItem item = new NBTItem(new ItemBuilder(Material.INK_SACK, 1, 10).setDisplayName("§6Spieler §8➜ §aAngezeigt").create());
        item.setString("LOBBY_INTERACT_EVENT", "playerhider");
        client.getInventory().setItem(0, item.getItem());

        client.getInventory().setItem(2, new ItemBuilder(Material.FISHING_ROD, 1, 0).setDisplayName("§cEnterhaken").setUnbreakable(true).create());

        item = new NBTItem(new ItemBuilder(Material.GOLD_RECORD, 1, 0).setDisplayName("§6§lTeleporter").create());
        item.setString("LOBBY_INTERACT_EVENT", "navigator");
        client.getInventory().setItem(4, item.getItem());

        client.getInventory().setItem(6, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§bSwitcher").create());
        client.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, 3).setOwner(client.getName()).setDisplayName("§aFreunde").create());

        if (client.hasPermission("core.vip")) {

            item = new NBTItem(new ItemBuilder(Material.NAME_TAG, 1, 0).setDisplayName("§5Nick §8➜ §aAktivieren").create());
            item.setString("LOBBY_INTERACT_EVENT", "nick");
            client.getInventory().setItem(22, item.getItem());
            client.setAllowFlight(true);
        }
    }

    @EventHandler
    public void oninteract(PlayerInteractEvent event) {
        Player client = event.getPlayer();
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
            return;
        }

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            NBTItem item = new NBTItem(event.getItem());
            if (item.hasKey("LOBBY_INTERACT_EVENT")) {
                switch (item.getString("LOBBY_INTERACT_EVENT")) {
                    case "playerhider":
                        if (!LobbyManager.getHidderlist().contains(client)) {
                            new LobbyManager().getHidderlist().add(client);
                            NBTItem nbtItem = new NBTItem(new ItemBuilder(Material.INK_SACK, 1, 1).setDisplayName("§6Spieler §8➜ §cVersteckt").create());
                            nbtItem.setString("LOBBY_INTERACT_EVENT", "playerhider");
                            client.getInventory().setItem(0, nbtItem.getItem());
                            client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                if (all != client) {
                                    client.hidePlayer(all);
                                }
                            }
                        } else {
                            new LobbyManager().getHidderlist().remove(client);
                            NBTItem nbtItem = new NBTItem(new ItemBuilder(Material.INK_SACK, 1, 10).setDisplayName("§6Spieler §8➜ §aAngezeigt").create());
                            nbtItem.setString("LOBBY_INTERACT_EVENT", "playerhider");
                            client.getInventory().setItem(0, nbtItem.getItem());
                            client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                if (all != client) {
                                    client.showPlayer(all);
                                }
                            }

                        }
                        break;
                    case "navigator":
                        client.getInventory().clear();

                        client.getInventory().setItem(0, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§rSpawn").setNBTString("LOBBY_INTERACT_EVENT", "WARP_SPAWN").create());
                        client.getInventory().setItem(1, new ItemBuilder(Material.BED, 1, 0).setDisplayName("§cBedWars").setNBTString("LOBBY_INTERACT_EVENT", "WARP_BEDWARS").create());
                        client.getInventory().setItem(2, new ItemBuilder(Material.SANDSTONE, 1, 0).setDisplayName("§aBuildFFA").setNBTString("LOBBY_INTERACT_EVENT","WARP_BUILDFFA").create());
                        client.getInventory().setItem(3, new ItemBuilder(Material.WOOD_PICKAXE, 1, 0).setDisplayName("§3MLGRush").setNBTString("LOBBY_INTERACT_EVENT","WARP_MLGRUSH").create());
                        client.getInventory().setItem(4, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).setDisplayName("§bArena").setNBTString("LOBBY_INTERACT_EVENT","WARP_BUILDFFA").create());

                        //back to default inv
                        NBTItem nbtItem = new NBTItem(new ItemBuilder(Material.SLIME_BALL, 1, 0).setDisplayName("§4Zurück").create());
                        nbtItem.setString("LOBBY_INTERACT_EVENT", "backtodefault");
                        client.getInventory().setItem(8, nbtItem.getItem());
                        break;
                    case "backtodefault":
                        client.getInventory().clear();
                        setInventory(client);
                        break;
                    case "WARP_SPAWN":
                        client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                        client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                        client.playEffect(client.getLocation(), Effect.SPLASH, 1000);

                        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                            public void run() {
                                client.teleport(new WarpManager("Spawn").getWarp());

                                client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                            }
                        }, 15L);
                        client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun am §rSpawn");
                        client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);
                        client.getInventory().clear();
                        setInventory(client);
                        break;
                    case "WARP_BEDWARS":

                        client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                        client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                        client.playEffect(client.getLocation(), Effect.SPLASH, 1000);

                        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                            public void run() {
                                client.teleport(new WarpManager("bedwars").getWarp());

                                client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                            }
                        }, 15L);
                        client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §cBedWars");
                        client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);
                        client.getInventory().clear();
                        setInventory(client);
                        break;

                    case "WARP_MLGRUSH":

                        client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                        client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                        client.playEffect(client.getLocation(), Effect.SPLASH, 1000);

                        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                            public void run() {
                                client.teleport(new WarpManager("mlgrush").getWarp());

                                client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                            }
                        }, 15L);
                        client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §3MLGRush");
                        client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);
                        client.getInventory().clear();
                        setInventory(client);
                        break;

                    case "WARP_BUILDFFA":

                        client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                        client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                        client.playEffect(client.getLocation(), Effect.SPLASH, 1000);

                        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                            public void run() {
                                client.teleport(new WarpManager("bedwars").getWarp());

                                client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                            }
                        }, 15L);
                        client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §aBuildFFA");
                        client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);
                        client.getInventory().clear();
                        setInventory(client);
                        break;

                    case "WARP_ARENA":

                        client.setVelocity(new Vector(0.0D, 1.0D, 0.0D));
                        client.playSound(client.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                        client.playEffect(client.getLocation(), Effect.SPLASH, 1000);

                        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
                            public void run() {
                                client.teleport(new WarpManager("arena").getWarp());

                                client.playSound(client.getLocation(), Sound.GLASS, 1.0F, 1.0F);
                            }
                        }, 15L);
                        client.sendTitle(new PrefixBuilder("§3Lobby").build(),"§aDu bist nun bei §bArena");
                        client.playSound(client.getLocation(),Sound.CHICKEN_EGG_POP,1,1);
                        client.getInventory().clear();
                        setInventory(client);
                        break;

                }
                return;
            }
/*
            if (client.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spieler §8➜ §aAngezeigt")) {
                if (!LobbyManager.getHidderlist().contains(client)) {
                    new LobbyManager().getHidderlist().add(client);
                    client.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 1).setDisplayName("§6Spieler §8➜ §cVersteckt").create());
                    client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                } else {
                    new LobbyManager().getHidderlist().remove(client);
                    client.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 1).setDisplayName("§6Spieler §8➜ §aAngezeigt").create());
                    client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);

                }
            }
            if (client.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lTeleporter")) {
                client.getInventory().clear();

                client.getInventory().setItem(0, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§rSpawn").create());
                client.getInventory().setItem(1, new ItemBuilder(Material.BED, 1, 0).setDisplayName("§cBedWars").create());
                client.getInventory().setItem(2, new ItemBuilder(Material.SANDSTONE, 1, 0).setDisplayName("§aBuildFFA").create());
                client.getInventory().setItem(3, new ItemBuilder(Material.WOOD_PICKAXE, 1, 0).setDisplayName("§3MLGRush").create());
                client.getInventory().setItem(4, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).setDisplayName("§bArena").create());

                //back to default inv
                client.getInventory().setItem(8, new ItemBuilder(Material.SLIME_BALL, 1, 0).setDisplayName("§4Zurück").create());

                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Zurück")) {
                    client.getInventory().clear();
                    setInventory(client);
                }
            }
        */
        }

    }
}
