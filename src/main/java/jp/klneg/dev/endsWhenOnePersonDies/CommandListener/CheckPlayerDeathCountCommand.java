package jp.klneg.dev.endsWhenOnePersonDies.CommandListener;

import jp.klneg.dev.endsWhenOnePersonDies.Util.PlayerDeathEventCountSaver;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static jp.klneg.dev.endsWhenOnePersonDies.EndsWhenOnePersonDies.plugin;

public class CheckPlayerDeathCountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        player.sendMessage(ChatColor.GOLD + "------ Count -----");

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            player.sendMessage(p.getName() + " : " + PlayerDeathEventCountSaver.get(p));
        }
        player.sendMessage(ChatColor.GOLD + "------------------");

        return false;
    }
}
