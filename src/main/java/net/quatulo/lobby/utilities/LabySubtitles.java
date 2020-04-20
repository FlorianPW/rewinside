package net.quatulo.lobby.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.UUID;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;

public class LabySubtitles {

  public void setSubtitle(Player receiver, UUID subtitlePlayer, String value) {
    // List of all subtitles
    JsonArray array = new JsonArray();

    // Add subtitle
    JsonObject subtitle = new JsonObject();
    subtitle.addProperty("uuid", subtitlePlayer.toString());

    // Optional: Size of the subtitle
    subtitle.addProperty("size", 1.2d); // Range is 0.8 - 1.6 (1.6 is Minecraft default)

    // no value = remove the subtitle
    if (value != null) {
      subtitle.addProperty("value", value);
    }

    // You can set multible subtitles in one packet
    array.add(subtitle);

    // Send to LabyMod using the API
    LabyModPlugin.getInstance().sendServerMessage(receiver, "account_subtitle", array);

  }

}
