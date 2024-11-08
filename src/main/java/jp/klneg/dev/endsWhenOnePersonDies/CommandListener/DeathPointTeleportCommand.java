package jp.klneg.dev.endsWhenOnePersonDies.CommandListener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathPointTeleportCommand implements CommandExecutor {
    public static Location playerDeathPoint = null;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (playerDeathPoint == null) {
            commandSender.sendMessage(ChatColor.GOLD + "[EWOPD]まだ誰も死んでいないため飛べません。");
            return true;
        }
        Player player = (Player) commandSender;
        player.teleport(playerDeathPoint);
        commandSender.sendMessage(ChatColor.GOLD + "[EWOPD]テレポートしました！。");

        return true;
    }
}
