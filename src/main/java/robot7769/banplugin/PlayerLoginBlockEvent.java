package robot7769.banplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Objects;

public class PlayerLoginBlockEvent implements Listener {
    private Main plugin;

    public PlayerLoginBlockEvent(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if(event.getPlayer().getAddress() != null) {
            if (plugin.store.isAddressBlocked(event.getAddress().getHostAddress())) {
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, Objects.requireNonNull(plugin.getConfig().getString("ban-msg")));
            }
        }
        if (plugin.store.isAddressBlocked(event.getAddress().getHostAddress())) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, Objects.requireNonNull(plugin.getConfig().getString("ban-msg")));
        }
    }
}
