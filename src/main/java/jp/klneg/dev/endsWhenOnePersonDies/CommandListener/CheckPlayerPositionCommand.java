package jp.klneg.dev.endsWhenOnePersonDies.CommandListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;


public class CheckPlayerPositionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.GLOWING, 10 * 20, 10);
        player.addPotionEffect(potionEffect);
        return true;
    }
}
