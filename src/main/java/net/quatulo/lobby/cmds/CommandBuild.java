package net.quatulo.lobby.cmds;

import net.quatulo.lobby.utilities.ItemBuilder;
import net.quatulo.lobby.utilities.LobbyManager;
import net.quatulo.lobby.utilities.PrefixBuilder;
import net.quatulo.lobby.utilities.WarpManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBuild implements CommandExecutor {

    private void setInventory(Player client) {

        client.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, 10).setDisplayName("§6Spieler §8➜ §aAngezeigt").create());
        client.getInventory().setItem(2, new ItemBuilder(Material.FISHING_ROD, 1, 0).setDisplayName("§cEnterhaken").setUnbreakable(true).create());
        client.getInventory().setItem(4, new ItemBuilder(Material.GOLD_RECORD, 1, 0).setDisplayName("§6§lTeleporter").create());
        client.getInventory().setItem(6, new ItemBuilder(Material.NETHER_STAR, 1, 0).setDisplayName("§bSwitcher").create());
        client.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, 3).setOwner(client.getName()).setDisplayName("§aFreunde").create());

        if (client.hasPermission("lobby.nick.item")) {
            client.getInventory().setItem(22, new ItemBuilder(Material.NAME_TAG, 1, 0).setDisplayName("§5Nick §8➜ §aAktivieren").create());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player client = (Player) sender;
            if (client.hasPermission("core.lobby.admin") || client.hasPermission("core.lobby.build")) {

                switch (args.length) {
                    case 0:
                        client.sendMessage(new PrefixBuilder("§3Lobby").build() + "falsche Verwendung!");
                        break;
                    case 1:
                        if (args[0].equalsIgnoreCase("on")) {
                            if (!LobbyManager.getBuildlist().contains(client)) {
                                new LobbyManager().getBuildlist().add(client);
                                client.sendMessage(new PrefixBuilder("§3Lobby").build() + "Du kannst nun bauen.");
                                client.getInventory().clear();
                                client.setGameMode(GameMode.CREATIVE);
                            }
                        } else if (args[0].equalsIgnoreCase("off")) {
                            if (!LobbyManager.getBuildlist().contains(client)) {
                                new LobbyManager().getBuildlist().remove(client);
                                client.sendMessage(new PrefixBuilder("§3Lobby").build() + "Du kannst nun nicht mehr bauen.");
                                client.getInventory().clear();
                                setInventory(client);
                                client.teleport(new WarpManager("spawn").getWarp());
                                client.setGameMode(GameMode.SURVIVAL);
                                break;
                            }
                        }
                }
            }
        }

        return false;
    }
}
