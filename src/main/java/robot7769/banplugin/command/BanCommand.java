package robot7769.banplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import robot7769.banplugin.Main;

import java.util.Objects;


public class BanCommand implements CommandExecutor {

    private Main plugin;

    public BanCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1 || !commandSender.hasPermission(Objects.requireNonNull(command.getPermission()))) {
            return false;
        }
        String ipAddressString = strings[0];
        if (plugin.store.addBlockedAddress(ipAddressString)) {
            commandSender.sendMessage("Address " + ipAddressString + " has been blocked.");
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (plugin.store.isAddressBlocked(player.getAddress().getAddress().getHostAddress())) {
                    player.kickPlayer(plugin.getConfig().getString("ban-msg"));
                    commandSender.sendMessage("Player " + player.getName() + " has been kicked.");
                }
            });
        } else {
            commandSender.sendMessage("Address " + ipAddressString + " is already blocked. Use /ban-list to see all blocked addresses.");
        }
        return true;
    }


}
