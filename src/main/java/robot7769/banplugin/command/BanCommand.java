package robot7769.banplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import robot7769.banplugin.Main;


public class BanCommand implements CommandExecutor {

    private Main plugin;

    public BanCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1 || !commandSender.hasPermission("banplugin.ban")) {
            return false;
        }
        String ipAddressString = strings[0];
        plugin.store.addBlockedAddress(ipAddressString);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (plugin.store.isAddressBlocked(player.getAddress().getAddress().getHostAddress())) {
                player.kickPlayer(plugin.getConfig().getString("ban-msg"));
            }
        });
        return false;
    }


}
