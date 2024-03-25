package dev.hardling.us.Listeners.Editor;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.provider.files.ConfigCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ViewInventoryListener implements Listener {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();

    public static void ViewInvKit(Player p, String s) {
        // INVENTORY
       // Inventory inv = Bukkit.createInventory(null, 54, CC.translate("&3&l" + s + " Slot"));

        // ITEMS
           /* p.closeInventory();
            ItemStack[] armor = ((List<ItemStack>) gkitsFile.get("KITS." + s + ".INVENTORY_ITEMS.ARMOR")).stream().toArray(ItemStack[]::new);
            ItemStack[] contents = ((List<ItemStack>) gkitsFile.get("KITS." + s + ".INVENTORY_ITEMS.ITEMS")).stream().toArray(ItemStack[]::new);
            p.getInventory().setArmorContents(armor);
            p.getInventory().setContents(contents);
            p.updateInventory();
            p.getOpenInventory();*/


        /*// BACK MENU
        inv.setItem(53, (new ItemCreator(Material.FEATHER))
                .setDisplayName("&cBack to previous menu")
                .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Back to old menu", CC.LINELIGHCUT))
                .build());

        // SOUND
        p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.CHEST_OPEN, 1.5F, 1.5F);
        p.openInventory(inv);*/
    }

    /*@EventHandler
    public void onViewInv(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
            if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("&3&l" + s + " Slot"))) {
                // BACK TO MENU
                if (event.getSlot() == 53) {
                    KitListener.invEditorKit(p, s);
                }
                event.setCancelled(true);
            }
        }
    }*/
}