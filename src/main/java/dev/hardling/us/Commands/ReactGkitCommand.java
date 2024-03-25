package dev.hardling.us.Commands;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.command.BaseCommand;
import dev.hardling.us.Utils.command.CommandArgs;
import dev.hardling.us.Utils.command.MainCommand;
import org.bukkit.entity.Player;

public class ReactGkitCommand extends BaseCommand {

    @MainCommand(name = "reactgkit", description = "Main command", inGameOnly = true, aliases = { "rg" })
    public boolean sendMessage(CommandArgs command) {
        Player p = command.getPlayer();
        String[] args = command.getArgs();
        if (args.length == 0) {
            p.sendMessage(CC.translate(CC.LINELIGH));
            p.sendMessage(CC.translate("                   &3&lReactGkits &8- [&a&lHardling Development&8]"));
            p.sendMessage("");
            p.sendMessage(CC.translate("&7»&b /ReactGkit &fReload &7- &fReload all Config"));
            p.sendMessage(CC.translate("&7»&b /ReactGkit &fInfo &7- &fInformation about the plugin"));
            p.sendMessage(CC.translate(CC.LINELIGH));
        } else if (args[0].equalsIgnoreCase("info")) {
            p.sendMessage(CC.translate(CC.LINELIGH));
            p.sendMessage(CC.translate("               &3&lReactGkits &8- [&a&lHardling Development&8]"));
            p.sendMessage("");
            p.sendMessage(CC.translate("&7»&b Version&7: &f" + ReactGkits.getInst().getDescription().getVersion()));
            p.sendMessage(CC.translate("&7»&b Authors&7: &f" + ReactGkits.getInst().getDescription().getAuthors()));
            p.sendMessage(CC.translate("&7»&b Discord&7: &fhttps://discord.hardling.us"));
            p.sendMessage(CC.translate(CC.LINELIGH));
        } else if (p.hasPermission("react.command.reload") && args[0].equalsIgnoreCase("reload")) {
            long time = System.currentTimeMillis();
            ReactGkits.getInst().getLangFile().save();
            ReactGkits.getInst().getLicenseFile().save();
            ReactGkits.getInst().getGkitFile().save();
            ReactGkits.getInst().getDataFile().save();
            p.sendMessage(CC.translate(ReactGkits.getInst().getPrefix() + "&fhas been reloaded &asuccessfully. &7(" + (System.currentTimeMillis() - time) + "ms)"));
        }
        return false;
    }
}