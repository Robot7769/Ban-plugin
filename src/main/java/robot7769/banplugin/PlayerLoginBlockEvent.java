package robot7769.banplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginBlockEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if(event.getPlayer().getAddress() == null) {
            System.out.println("onPlayerLogin: address is null");
        } else {
            System.out.println("onPlayerLogin: " + event.getPlayer().getAddress().getAddress().getHostAddress());
        }
        System.out.println("event adr: " + event.getAddress().getHostAddress());
    }
}
