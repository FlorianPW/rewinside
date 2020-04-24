package net.quatulo.lobby.utilities;

import lombok.Getter;
import net.quatulo.lobby.Lobby;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

@Getter
public class ArmorstandBuilder {

    private Lobby instance;
    private Location location;
    private ArmorStand armorStand;

    public ArmorstandBuilder(Location location) {
        this.instance = Lobby.getInstance();
        this.location = location;
        this.armorStand = (ArmorStand) getLocation().getWorld().spawnEntity(getLocation(), EntityType.ARMOR_STAND);
    }

    public void remove() {
        if (getArmorStand() != null) {
            getArmorStand().remove();
        }
    }

}
