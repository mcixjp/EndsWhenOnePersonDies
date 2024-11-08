package jp.klneg.dev.endsWhenOnePersonDies.Runnable;

import jp.klneg.dev.endsWhenOnePersonDies.Messanger.PlayerTransfer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.plugin;

public class ServerRestartScheduler extends BukkitRunnable {


    private int nowTime;

    public ServerRestartScheduler() {
        this.nowTime = 30;
    }

    @Override
    public void run() {
        if (nowTime < 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                PlayerTransfer.start(p, plugin);
            }
            plugin.getServer().shutdown();
            return;
        }
        if (nowTime % 10 == 0) {
            plugin.getLogger().info("CountDown:" + nowTime);
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("あと" + nowTime + "秒"));
        }
        nowTime--;
    }

}
