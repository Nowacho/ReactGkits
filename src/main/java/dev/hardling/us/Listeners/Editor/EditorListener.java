package dev.hardling.us.Listeners.Editor;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.ItemCreator;
import dev.hardling.us.Utils.provider.Config;
import dev.hardling.us.Utils.provider.files.ConfigCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class EditorListener implements Listener {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();

    public static void invGkit(Player p) {
        // INVENTORY
        Inventory inv = Bukkit.createInventory(null, Config.GKIT_MENU_INVENTORY_SLOTS, CC.translate(Config.KIT_EDITOR_MENU_TITLE));

        // ITEM EDITOR
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
            String kit = "KITS." + s + ".";
            inv.setItem(gkitsFile.getInt(kit + "WITH_PERMISSIONS.SLOT"),
                    (new ItemCreator(Material.valueOf(gkitsFile.getString(kit + "WITH_PERMISSIONS.MATERIAL"))))
                            .setDisplayName(gkitsFile.getString(kit + "WITH_PERMISSIONS.DISPLAYNAME"))
                            .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/left click to edit the kit ", CC.LINELIGHCUT))
                            .setGlow(gkitsFile.getBoolean(kit + "WITH_PERMISSIONS.ENCHANTED"))
                            .setDurability(gkitsFile.getInt(kit + "WITH_PERMISSIONS.DATA"))
                            .build());

        }
        // GLASS PANEL
        ItemStack panel = (new ItemCreator(Material.STAINED_GLASS_PANE, 1, (short) 5)).setName("").build();
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, panel);
            }
        }

        // SOUND
        p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.CHEST_OPEN, 1.5F, 1.5F);
        p.openInventory(inv);
    }

    @EventHandler
    public void onEditor(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;
        if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate(Config.KIT_EDITOR_MENU_TITLE))) {
            for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
                String kit = "KITS." + s + ".";
                if (event.getSlot() == gkitsFile.getInt(kit + "WITH_PERMISSIONS.SLOT")) {
                    KitListener.invEditorKit(p, s);
                }
            }
            event.setCancelled(true);
        }
    }
}