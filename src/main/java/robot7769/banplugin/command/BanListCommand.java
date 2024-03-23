package robot7769.banplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import robot7769.banplugin.Main;

public class BanListCommand implements CommandExecutor {

    private Main plugin;

    public BanListCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0 || !commandSender.hasPermission("banplugin.ban")) {
            return false;
        }
        commandSender.sendMessage("Blocked addresses:");
        plugin.store.getBlockedAddresses().forEach(commandSender::sendMessage);
        return true;
    }
}
