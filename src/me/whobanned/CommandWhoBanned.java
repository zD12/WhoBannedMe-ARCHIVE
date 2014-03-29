package me.whobanned;

import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandWhoBanned implements CommandExecutor {

    private final WhoBannedMe plugin;
    private final BanLookup lookup;
    public String pName;

    public CommandWhoBanned(WhoBannedMe plugin, BanLookup lookup) {
        this.plugin = plugin;
        this.lookup = lookup;
    }

    @Override
    public boolean onCommand(CommandSender sentBy, Command command, String string, String[] args) {
        if (command.getName().equalsIgnoreCase("whobanned") && sentBy.hasPermission("whobannedme.lookup")) {
            if (args.length == 1) {
                pName = args[0];
                plugin.getLogger().log(Level.INFO, "pName variable from CommandWhoBanned: {0}", pName);//debugcode
                try {
                    plugin.getLogger().log(Level.INFO, "Variable pName inside try: {0}", pName);
                    lookup.check(pName);
                } catch (IOException ex) {
                ex.printStackTrace();
                }
            } else {
                sentBy.sendMessage(plugin.broadcastTag + "Usage: /<command> <username>");
            }
            return true;
        }
        return false;

    }

}
