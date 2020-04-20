package net.quatulo.lobby.cmds;

import net.quatulo.lobby.utilities.PrefixBuilder;
import net.quatulo.lobby.utilities.WarpManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player client = (Player) sender;
            if (client.hasPermission("core.lobby.admin")) {

                switch (args.length) {
                    case 0:
                        client.sendMessage(new PrefixBuilder("§6Quatulo").build() + "falsche Verwenung!");
                        break;
                    case 1:
                        new WarpManager(args[0]).setWarp(client.getLocation());
                        client.sendMessage(new PrefixBuilder("§6Quatulo").build() + "Du hast nun eine neue Spawnlocation gesetzt.");
                        client.playSound(client.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
                        break;
                }
            } else {
                client.sendMessage(new PrefixBuilder("§6Quatulo").build() + "Du hast keine Rechte dazu.");
            }
        }

        return true;
    }
}
