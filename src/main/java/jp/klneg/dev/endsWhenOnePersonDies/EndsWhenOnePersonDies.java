package jp.klneg.dev.endsWhenOnePersonDies;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import jp.klneg.dev.endsWhenOnePersonDies.CommandListener.CheckPlayerDeathCountCommand;
import jp.klneg.dev.endsWhenOnePersonDies.CommandListener.CheckPlayerPositionCommand;
import jp.klneg.dev.endsWhenOnePersonDies.CommandListener.DeathPointTeleportCommand;
import jp.klneg.dev.endsWhenOnePersonDies.EventHandler.PlayerDeathEventListener;
import jp.klneg.dev.endsWhenOnePersonDies.EventHandler.PlayerFirstJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class EndsWhenOnePersonDies extends JavaPlugin {
    public static Plugin plugin;
    public static String lobbyName;
    public static int deathCount;
    public static RadioSongPlayer rsp;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        deathCount = getConfig().getInt("PlayerDeathCounter");

        if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")) {
            getLogger().info("NoteBlockAPI not enabled");
        } else {
            getLogger().info("NoteBlockAPI enabled");
        }

        getLogger().info("EWOPD Plugin enabled");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new PlayerDeathEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFirstJoinEvent(), this);
        lobbyName = getConfig().getString("lobbyName");
        getCommand("deathpointtp").setExecutor(new DeathPointTeleportCommand());
        getCommand("deathcount").setExecutor(new CheckPlayerDeathCountCommand());
        getCommand("glow").setExecutor(new CheckPlayerPositionCommand());

        plugin = this;
        Song song = NBSDecoder.parse(new File(plugin.getDataFolder(), "songs/" + plugin.getConfig().getString("DeathSong")));
        rsp = new RadioSongPlayer(song);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getConfig().set("PlayerDeathCounter", deathCount);
        saveConfig();
    }
}
