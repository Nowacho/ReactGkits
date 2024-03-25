package dev.hardling.us.Utils.provider;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.provider.files.ConfigCreator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Config {
    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();

    public static boolean GKIT_MENU_GLASS_PANEL_ENABLED = gkitsFile.getBoolean("GKIT_MENU.GLASS_PANEL.ENABLED");
    public static boolean GKIT_MENU_SOUND_ENABLED = gkitsFile.getBoolean("GKIT_MENU.SOUND.ENABLED");
    public static int GKIT_MENU_GLASS_PANEL_ID = gkitsFile.getInt("GKIT_MENU.GLASS_PANEL.ID");
    public static int GKIT_MENU_INVENTORY_SLOTS = gkitsFile.getInt("GKIT_MENU.INVENTORY_SLOTS");
    public static String GKIT_MENU_TITLE = gkitsFile.getString("GKIT_MENU.TITLE");
    public static String GKIT_MENU_SOUND_SOUND = gkitsFile.getString("GKIT_MENU.SOUND.SOUND");
    public static String KIT_EDITOR_MENU_TITLE = "&3&lReactGkits Editor";
    public static String MATERIAL_INVALID = "&cMaterial Invalid";
    public static String MATERIAL_CORRECTLY = "&aMaterial saved correctly";
    public static String SOUND_DISABLED = "&cSound is disabled";
    public static String SOUND_ACTIVATE = "&aSound activate";
    public static String ENCHANTED_DISABLED = "&cEnchantment is deactivated";
    public static String ENCHANTED_ACTIVATE = "&aEnchantment activated";

    public static void createKit(String s) {
        String stripColor = CC.translate(ChatColor.stripColor(s));
        String kits = "KITS." + stripColor + ".";
        // WITH_PERMISSIONS
        gkitsFile.set(kits + "WITH_PERMISSIONS.DISPLAYNAME", s);
        gkitsFile.set(kits + "WITH_PERMISSIONS.LORE", Arrays.asList(CC.LINELIGH, "&8* &6Right click to get the gkit", CC.LINELIGH));
        gkitsFile.set(kits + "WITH_PERMISSIONS.MESSAGE", "&ayou got the kit correctly");
        gkitsFile.set(kits + "WITH_PERMISSIONS.ENCHANTED", true);
        gkitsFile.set(kits + "WITH_PERMISSIONS.MATERIAL", "BOW");
        gkitsFile.set(kits + "WITH_PERMISSIONS.DATA", 0);
        int x = (int) (Math.random() * ((gkitsFile.getInt("GKIT_MENU.INVENTORY_SLOTS") - 0) + 1)) + 0;
        gkitsFile.set(kits + "WITH_PERMISSIONS.SLOT", x);
        gkitsFile.set(kits + "WITH_PERMISSIONS.SOUND.ENABLE", true);
        gkitsFile.set(kits + "WITH_PERMISSIONS.SOUND.SOUND", "VILLAGER_YES");
        // NO_PERMISSIONS
        gkitsFile.set(kits + "NO_PERMISSIONS.MESSAGE", "&cyou can purchase this kit in the hardling.tebex.io");
        gkitsFile.set(kits + "NO_PERMISSIONS.SOUND.ENABLED", true);
        gkitsFile.set(kits + "NO_PERMISSIONS.SOUND.SOUND", "VILLAGER_YES");
        // COOLDOWN
        gkitsFile.set(kits + "COOLDOWN.MESSAGE", "&cYou have to wait some &a&n%time%&c to get the kit again.");
        gkitsFile.set(kits + "COOLDOWN.SOUND.ENABLED", true);
        gkitsFile.set(kits + "COOLDOWN.SOUND.SOUND", "VILLAGER_YES");
        // PERMISSION
        gkitsFile.set(kits + "PERMISSION", "react.gkit." + stripColor.toLowerCase());
        // INVENTORY_ITEMS
        gkitsFile.set(kits + "INVENTORY_ITEMS", "");
        gkitsFile.save();
    }

    public static void setItems(Player p, String s) {
        gkitsFile.set("KITS." + s + ".INVENTORY_ITEMS.ITEMS", p.getInventory().getContents());
        gkitsFile.set("KITS." + s + ".INVENTORY_ITEMS.ARMOR", p.getInventory().getArmorContents());
        gkitsFile.save();
    }
}
