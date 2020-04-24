package net.quatulo.lobby.listener;

import com.avaje.ebean.validation.Email;
import net.quatulo.lobby.NBTItem;
import net.quatulo.lobby.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Inventoryclick implements Listener {

    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Player client = (Player) event.getWhoClicked();
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        NBTItem item = new NBTItem(event.getCurrentItem());
        if(item.hasKey("LOBBY_INTERACT_EVENT")) {
            if(item.getString("LOBBY_INTERACT_EVENT").equalsIgnoreCase("nick")) {
                item = new NBTItem(new ItemBuilder(Material.NAME_TAG, 1, 0).setDisplayName("§5Nick §8➜ §a✔").create());
                item.setString("LOBBY_INTERACT_EVENT", "unnick");
                client.getInventory().setItem(22, item.getItem());
            }
        }
    }
}
