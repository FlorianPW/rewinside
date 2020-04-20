package net.quatulo.lobby.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class WarpManager {
    private String warpName;

    public WarpManager(String warpName) {
        this.warpName = warpName.toUpperCase();
    }

    public WarpManager setWarp(Location location) {
        new LobbyManager();
        LobbyManager.getFileBuilder().setValue("Warp." + this.warpName + ".World", location.getWorld().getName());
        new LobbyManager();
        LobbyManager.getFileBuilder().setValue("Warp." + this.warpName + ".X", Double.valueOf(location.getX()));
        new LobbyManager();
        LobbyManager.getFileBuilder().setValue("Warp." + this.warpName + ".Y", Double.valueOf(location.getY()));
        new LobbyManager();
        LobbyManager.getFileBuilder().setValue("Warp." + this.warpName + ".Z", Double.valueOf(location.getZ()));
        new LobbyManager();
        LobbyManager.getFileBuilder().setValue("Warp." + this.warpName + ".Yaw", Float.valueOf(location.getYaw()));
        new LobbyManager();
        LobbyManager.getFileBuilder().setValue("Warp." + this.warpName + ".Pitch", Float.valueOf(location.getPitch()));

        return this;
    }

    public Location getWarp() {
        new LobbyManager();
        if (LobbyManager.getFileBuilder().getString("Warp." + this.warpName) != null) {
            new LobbyManager();
            new LobbyManager();
            new LobbyManager();
            new LobbyManager();
            new LobbyManager();
            new LobbyManager();
            return new Location(Bukkit.getWorld(LobbyManager.getFileBuilder().getString("Warp." + this.warpName + ".World")), LobbyManager.getFileBuilder().getDouble("Warp." + this.warpName + ".X").doubleValue(), LobbyManager.getFileBuilder().getDouble("Warp." + this.warpName + ".Y").doubleValue(), LobbyManager.getFileBuilder().getDouble("Warp." + this.warpName + ".Z").doubleValue(), LobbyManager.getFileBuilder().getFloat("Warp." + this.warpName + ".Yaw").floatValue(),
                    LobbyManager.getFileBuilder().getFloat("Warp." + this.warpName + ".Pitch").floatValue());
        }
        return null;
    }
}
