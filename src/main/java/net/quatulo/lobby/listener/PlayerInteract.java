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
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener {
    private void setInventory(Player client) {
        client.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).setDisplayName("§6Spieler §8➜ §aAngezeigt").setNBTString("LOBBY_INTERACT_EVENT", "playerhider").create());

        client.getInventory().setItem(2, new ItemBuilder(Material.FISHING_ROD, 1, 0).setDisplayName("§cEnterhaken").setUnbreakable(true).create());

        client.getInventory().setItem(4, new ItemBuilder(Material.GOLD_RECORD, 1, 0).setDisplayName("§6§lTeleporter").setNBTString("LOBBY_INTERACT_EVENT", "navigator").create());

        client.getInventory().setItem(6, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§bSwitcher").create());
        client.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, 3).setOwner(client.getName()).setDisplayName("§aProfil").setNBTString("LOBBY_INTERACT_EVENT", "profile").create());

        if (client.hasPermission("core.vip")) {

            client.getInventory().setItem(22, new ItemBuilder(Material.NAME_TAG, 1, 0).setDisplayName("§5Nick §8➜ §aAktivieren").setNBTString("LOBBY_INVENTORY_EVENT", "nick_tool").create());
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
                            for (Player all : Bukkit.getOnlinePlayers()) {
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
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all != client) {
                                    client.showPlayer(all);
                                }
                            }

                        }
                        break;

                    case "profile":
                        Inventory profil = Bukkit.createInventory(null, 3 * 9, "§aProfil");
                        for (int i = 0; i < 27; i++) {
                            profil.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).setDisplayName(" ").create());
                        }
                        profil.setItem(11, new ItemBuilder(Material.SKULL_ITEM, 1, 3).setOwner(client.getName()).setDisplayName("§bFreunde").setNBTString("LOBBY_INTERACT_EVENT", "FREUNDE_PROFILE").create());
                        profil.setItem(13, new ItemBuilder(Material.STICK, 1, 0).setDisplayName("§bExtras").setNBTString("LOBBY_INTERACT_EVENT", "EXTRAS_PROFILE").create());
                        profil.setItem(15, new ItemBuilder(Material.BARRIER, 1, 0).setDisplayName("§cSoon..").create());

                        client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                        client.openInventory(profil);
                        break;

                    case "navigator":

                        Inventory navi = Bukkit.createInventory(null, 4 * 9, "§6Teleporter");
                        for (int i = 0; i < 36; i++) {
                            navi.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).setDisplayName(" ").create());

                        }
                            navi.setItem(4, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§rSpawn").setNBTString("LOBBY_INVENTORY_EVENT", "WARP_SPAWN").create());
                            navi.setItem(21, new ItemBuilder(Material.BED, 1, 0).setDisplayName("§cBedWars").setNBTString("LLOBBY_INVENTORY_EVENT", "WARP_BEDWARS").create());
                            navi.setItem(19, new ItemBuilder(Material.SANDSTONE, 1, 0).setDisplayName("§aBuildFFA").setNBTString("LOBBY_INVENTORY_EVENT", "WARP_BUILDFFA").create());
                            navi.setItem(22, new ItemBuilder(Material.WOOD_PICKAXE, 1, 0).setDisplayName("§3MLGRush").setNBTString("LOBBY_INVENTORY_EVENT", "WARP_MLGRUSH").create());
                            navi.setItem(23, new ItemBuilder(Material.DIAMOND_SWORD, 1, 0).setDisplayName("§bArena").setNBTString("LOBBY_INVENTORY_EVENT", "WARP_ARENA").create());
                            navi.setItem(25, new ItemBuilder(Material.GOLD_NUGGET, 1, 0).setDisplayName("§6Tägliche Belohnung").setNBTString("LOBBY_INVENTORY_EVENT", "WARP_REWARD").create());
                            client.openInventory(navi);
                            client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            break;

                }
                return;
            }

        }

    }
    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player client = event.getPlayer();

        if(event.equals(Action.RIGHT_CLICK_AIR)) {
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerArmorStandManipulateEvent event) {
        Player client = event.getPlayer();
        if(!LobbyManager.getBuildlist().contains(client)) {
            event.setCancelled(true);
        }
    }
}
