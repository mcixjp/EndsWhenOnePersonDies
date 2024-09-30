package jp.klneg.dev.endsWhenOnePersonDies.EventHandler;

import jp.klneg.dev.endsWhenOnePersonDies.Scheduler.ServerRestartScheduler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.deathCount;

public class PlayerDeathEventListener implements Listener {
    Plugin plugin;

    public PlayerDeathEventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        deathCount++;
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            p.sendTitle("死んでしまった......", deathCount + "回目の死亡です。", 10, 60, 10);
            p.setGameMode(GameMode.SPECTATOR);
        }
        Bukkit.broadcastMessage(ChatColor.GOLD + "30秒後に再起動をします.....");
        new ServerRestartScheduler().runTaskTimer(plugin, 0L, 20L);
    }
}
