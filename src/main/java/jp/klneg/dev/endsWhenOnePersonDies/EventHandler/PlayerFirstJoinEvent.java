package jp.klneg.dev.endsWhenOnePersonDies.EventHandler;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class PlayerFirstJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Set<String> Tags = player.getScoreboardTags();

        if (Tags.contains("AlreadyJoined")) {
            return;
        } else {
            player.addScoreboardTag("AlreadyJoined");
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
        }

    }
}
