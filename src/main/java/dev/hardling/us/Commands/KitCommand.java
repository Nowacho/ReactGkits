package dev.hardling.us.Commands;

import dev.hardling.us.Listeners.Editor.EditorListener;
import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.command.BaseCommand;
import dev.hardling.us.Utils.command.CommandArgs;
import dev.hardling.us.Utils.command.MainCommand;
import dev.hardling.us.Utils.provider.Config;
import dev.hardling.us.Utils.provider.Lang;
import dev.hardling.us.Utils.provider.files.ConfigCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class KitCommand extends BaseCommand {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();

    @MainCommand(name = "kit", description = "Kit editor", permission = "react.command.kit", inGameOnly = true, aliases = { "rkit", "rk" })
    public boolean sendMessage(CommandArgs command) {
        Player p = command.getPlayer();
        String[] args = command.getArgs();
        if (args.length == 0) {
            p.sendMessage(CC.translate(CC.LINELIGH));
            p.sendMessage(CC.translate("                   &a&lKit Editor &8- [&3&lReactGkits&8]"));
            p.sendMessage("");
            p.sendMessage(CC.translate("&7»&b /Kit &fSetItems <Kit> &7- &fPlace an inventory of any kit"));
            p.sendMessage(CC.translate("&7»&b /Kit &fDelete <Kit> &7- &fRemove the kit from the list"));
            p.sendMessage(CC.translate("&7»&b /Kit &fCreate <Name> &7- &fCreate a kit"));
            p.sendMessage(CC.translate("&7»&b /Kit &fGive <Player> <Kit> &7- &fGive kit to a player"));
            p.sendMessage(CC.translate("&7»&b /Kit &fEditor &7- &fFast kit editor"));
            p.sendMessage(CC.translate("&7»&b /Kit &fRename <Name> <Kit>&7- &fRename kit"));
            p.sendMessage(CC.translate("&7»&b /Kit &fSetperm <Perm> <kit>&7- &fI place a permit"));
            p.sendMessage(CC.translate("&7»&b /Kit &fList"));
            p.sendMessage(CC.translate(CC.LINELIGH));
            // KIT SETITEMS
        } else if (p.hasPermission("react.command.setitems") && args[0].equalsIgnoreCase("setitems")) {
            if (args.length == 1) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
                return false;
            }
            if (gkitsFile.contains("KITS." + args[1])) {
                Config.setItems(p, args[1]);
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_CORRETLY_PLACED));
            } else {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
            }
            // KIT LIST
        } else if (p.hasPermission("react.command.list") && args[0].equalsIgnoreCase("list")) {
            p.sendMessage(CC.translate(CC.LINELIGH));
            p.sendMessage(CC.translate("                   &a&lKit List &8- [&3&lReactGkits&8]"));
            p.sendMessage("");
            for (String string : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
                p.sendMessage(CC.translate("&aName&7: &f" + string + " &7| &aDisplayName&7: [" + gkitsFile.getString("KITS." + string + ".WITH_PERMISSIONS.DISPLAYNAME") + "&7]"));
            }
            p.sendMessage(CC.translate(CC.LINELIGH));
            // KIT CREATOR
        } else if (p.hasPermission("react.command.create") && args[0].equalsIgnoreCase("create")) {
            if (args.length == 1) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
                return false;
            }

            String string = CC.translate(ChatColor.stripColor(args[1]));
            if (gkitsFile.contains("KITS." + string)) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_ALREADY_CREATED));
            } else {
                Config.createKit(args[1]);
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_SUCCESSFULLY_CREATED));
            }
            // KIT DELETE
        } else if (p.hasPermission("react.command.delete") && args[0].equalsIgnoreCase("delete")) {
            if (args.length == 1) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
                return false;
            }

            String string = ChatColor.stripColor(args[1]);
            if (gkitsFile.contains("KITS." + string)) {
                gkitsFile.set("KITS." + string, null);
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_SUCCESSFULLY_DELETE));
            } else {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
            }
            // KIT GIVE PLAYER
        } else if (p.hasPermission("react.command.give") && args[0].equalsIgnoreCase("give")) {
            Player target = Bukkit.getPlayer(args[1]);
            String kits = "KITS." + args[2] + ".";
            if (args.length == 1) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_PLAYER));
                return false;
            }
            if (target == null) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_PLAYER_NOT_EXIST));
                return false;
            }
            if (args.length == 2) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
                return false;
            }
            if (gkitsFile.contains("KITS." + args[2])) {
                if (gkitsFile.contains(kits + "INVENTORY_ITEMS.ARMOR") && gkitsFile.contains(kits + "INVENTORY_ITEMS.ITEMS")) {
                    // KIT
                    ItemStack[] armor = ((List<ItemStack>) gkitsFile.get(kits + "INVENTORY_ITEMS.ARMOR")).stream().toArray(ItemStack[]::new);
                    ItemStack[] contents = ((List<ItemStack>) gkitsFile.get(kits + "INVENTORY_ITEMS.ITEMS")).stream().toArray(ItemStack[]::new);
                    target.getInventory().setArmorContents(armor);
                    target.getInventory().setContents(contents);
                    target.updateInventory();
                    //OTHERS
                    target.sendMessage(CC.translate(gkitsFile.getString(kits + "WITH_PERMISSIONS.MESSAGE")));
                    target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.valueOf(gkitsFile.getString(kits + "WITH_PERMISSIONS.SOUND.SOUND")), 1.5F, 1.5F);
                } else {
                    // KIT NO ITEMS
                    target.sendMessage(CC.translate(Lang.KIT_NO_ITEMS));
                }
            } else {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT_NOT_EXIST));
            }
            // KIT EDITOR MENU
        } else if (p.hasPermission("react.command.editor") && args[0].equalsIgnoreCase("editor")) {
            EditorListener.invGkit(p);
            p.sendMessage(CC.translate(Lang.KIT_COMMAND_OPEN_EDIT_MENU));
            // KIT RENAMER
        } else if (p.hasPermission("react.command.rename") && args[0].equalsIgnoreCase("rename")) {
            if (args.length == 1) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_NAME_KIT));
                return false;
            }
            if (args.length == 2) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
                return false;
            }

            if (gkitsFile.contains("KITS." + args[2])) {
                gkitsFile.set("KITS." + args[2] + ".WITH_PERMISSIONS.DISPLAYNAME", args[1]);
                gkitsFile.save();
                p.sendMessage(CC.translate("&aThe name kit &7(" + args[1] + "&7) &awas placed correctly"));
            } else {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
            }
            // KIT SETPERM
        } else if (p.hasPermission("react.command.setperm") && args[0].equalsIgnoreCase("setperm")) {
            if (args.length == 1) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_PERM_KIT));
                return false;
            }
            if (args.length == 2) {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
                return false;
            }
            if (gkitsFile.contains("KITS." + args[2])) {
                gkitsFile.set("KITS." + args[2] + ".PERMISSION", args[1]);
                gkitsFile.save();
                p.sendMessage(CC.translate("&aThe permission &7(" + args[1] + "&7) &awas placed correctly"));
            } else {
                p.sendMessage(CC.translate(Lang.KIT_COMMAND_MENTION_KIT));
            }
        }
        return false;
    }
}