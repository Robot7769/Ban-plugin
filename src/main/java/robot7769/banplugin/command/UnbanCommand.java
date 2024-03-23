package robot7769.banplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import robot7769.banplugin.Main;

import java.util.Objects;

public class UnbanCommand implements CommandExecutor {

    private Main plugin;

    public UnbanCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1 || !commandSender.hasPermission(Objects.requireNonNull(command.getPermission()))) {
            return false;
        }
        String ipAddressString = strings[0];
        if (plugin.store.removeBlockedAddress(ipAddressString)) {
            commandSender.sendMessage("Address " + ipAddressString + " has been unblocked.");
        } else {
            commandSender.sendMessage("Address " + ipAddressString + " is not blocked. Use /ban-list to see all blocked addresses.");
        }
        return true;
    }
}