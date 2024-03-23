package robot7769.banplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public AddressStore store;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        store = new AddressStore(this);
        getCommand("ban-more").setExecutor(new BanCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerLoginBlockEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
