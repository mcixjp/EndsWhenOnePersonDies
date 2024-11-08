package jp.klneg.dev.endsWhenOnePersonDies.EventHandler;

import jp.klneg.dev.endsWhenOnePersonDies.Util.PlayerDeathEventCountSaver;
import jp.klneg.dev.endsWhenOnePersonDies.Runnable.ServerRestartScheduler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.deathCount;
import static jp.klneg.dev.endsWhenOnePersonDies.CommandListener.DeathPointTeleportCommand.playerDeathPoint;
import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.rsp;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.plugin;

public class PlayerDeathEventListener implements Listener {


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        deathCount++;
        playerDeathPoint = player.getLocation();

        PlayerDeathEventCountSaver.add(player);
        PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 5);

        TextComponent message = new TextComponent("[テレポート]");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dptp"));
        message.setColor(net.md_5.bungee.api.ChatColor.GREEN);

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            p.sendTitle("死んでしまった......", deathCount + "回目の死亡です。", 10, 60, 10);
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage("プレイヤーが死んだ場所へテレポートしたい場合は↓をクリック！");
            p.spigot().sendMessage(message);
            p.addPotionEffect(effect);
            rsp.addPlayer(p);
        }
        rsp.setPlaying(true);

        Bukkit.broadcastMessage(ChatColor.GOLD + "30秒後に再起動をします.....");
        new ServerRestartScheduler().runTaskTimer(plugin, 0L, 20L);
    }
}
