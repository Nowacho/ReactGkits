package dev.hardling.us.Commands;

import dev.hardling.us.Listeners.GkitListener;
import dev.hardling.us.Utils.command.BaseCommand;
import dev.hardling.us.Utils.command.CommandArgs;
import dev.hardling.us.Utils.command.MainCommand;
import org.bukkit.entity.Player;

public class GkitCommand extends BaseCommand {

    @MainCommand(name = "gkit", description = "Kit Selector", inGameOnly = true, aliases = { "gkits", "kits" })
    public boolean sendMessage(CommandArgs command) {
        Player p = command.getPlayer();
        String[] args = command.getArgs();
        if (args.length == 0) {
            GkitListener.invGkit(p);
        }
        return false;
    }
}