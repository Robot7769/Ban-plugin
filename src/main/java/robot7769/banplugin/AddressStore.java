package robot7769.banplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AddressStore {

    private JavaPlugin plugin;

    private List<String> blockedAddresses;

    public AddressStore(JavaPlugin plugin) {
        this.plugin = plugin;
        blockedAddresses = plugin.getConfig().getStringList("blocked-addresses");
    }
    public boolean isAddressBlocked(String address) {

        return true;
    }
    public void addBlockedAddress(String address) {

    }
    public void removeBlockedAddress(String address) {
    }
    public void save() {
        plugin.getConfig().set("blocked-addresses", blockedAddresses);
        plugin.saveConfig();
    }
}
