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


public class SlotListener implements Listener {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();

    public static void invEditorKit(Player p, String s) {
        // INVENTORY
        Inventory inv = Bukkit.createInventory(null, Config.GKIT_MENU_INVENTORY_SLOTS, CC.translate("&3&l" + s + " Slot"));

        // SET SLOT
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slot = (new ItemCreator(Material.WOOL)).setDisplayName("&aSlot " + i).setDurability(5).build();
            if (inv.getItem(i) == null) {
                inv.setItem(i, slot);
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
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
            if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("&3&l" + s + " Slot"))) {
                for (int i = 0; i <= Config.GKIT_MENU_INVENTORY_SLOTS; i++) {
                if (event.getSlot() == i) {
                        gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.SLOT", i);
                        gkitsFile.save();
                        p.sendMessage(CC.translate("&aSlot &7("+ i +") &athen correctly "));
                        KitListener.invEditorKit(p, s);
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}