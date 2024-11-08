package jp.klneg.dev.endsWhenOnePersonDies.Util;

import org.bukkit.entity.Player;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.plugin;

public class PlayerDeathEventCountSaver {

    public static void add(Player player) {
        if (!plugin.getConfig().isInt("PlayerDCount." + player.getUniqueId().toString() + ".DeathEventCount")) {
            plugin.getConfig().set("PlayerDCount." + player.getUniqueId().toString() + ".DeathEventCount", 1);
        } else {
            plugin.getConfig().set("PlayerDCount." + player.getUniqueId().toString() + ".DeathEventCount", plugin.getConfig().getInt("PlayerDCount." + player.getUniqueId().toString() + ".DeathEventCount") + 1);
        }
        return;
    }

    public static int get(Player player) {
        if (!plugin.getConfig().isInt("PlayerDCount." + player.getUniqueId().toString() + ".DeathEventCount")) {
            return 0;
        } else {
            return plugin.getConfig().getInt("PlayerDCount." + player.getUniqueId().toString() + ".DeathEventCount");
        }
    }


}
