package jp.klneg.dev.endsWhenOnePersonDies.Messanger;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.lobbyName;

public class PlayerTransfer {

    public static void start(Player player, Plugin plugin) {
         byte[] data = init();
         player.sendPluginMessage(plugin, "BungeeCord", data);
         return;
    }



    private static byte[] init() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(lobbyName);

        return out.toByteArray();
    }
}
