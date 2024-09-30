package jp.klneg.dev.endsWhenOnePersonDies;

import jp.klneg.dev.endsWhenOnePersonDies.EventHandler.PlayerDeathEventListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndsWhenOnePersonDies extends JavaPlugin {
    public static Plugin plugin;
    public static String lobbyName;
    public static int deathCount;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        deathCount = getConfig().getInt("PlayerDeathCounter");


        getLogger().info("EWOPD Plugin enabled");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new PlayerDeathEventListener(this), this);
        lobbyName = getConfig().getString("lobbyName");
        plugin = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getConfig().set("PlayerDeathCounter", deathCount);
        saveConfig();
    }
}
