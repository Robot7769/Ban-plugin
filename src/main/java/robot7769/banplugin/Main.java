package robot7769.banplugin;

import org.bukkit.plugin.java.JavaPlugin;
import robot7769.banplugin.command.BanCommand;

public final class Main extends JavaPlugin {

    public AddressStore store;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        store = new AddressStore(this);
        getCommand("ban-more").setExecutor(new BanCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerLoginBlockEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        store.save();
    }
}
