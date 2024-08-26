ackage com.admingui.adminguiplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminGUIPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AdminGUIPlugin has been enabled!");
        getCommand("admingui").setExecutor(new GUI());
    }

    @Override
    public void onDisable() {
        getLogger().info("AdminGUIPlugin has been disabled!");
    }
}
