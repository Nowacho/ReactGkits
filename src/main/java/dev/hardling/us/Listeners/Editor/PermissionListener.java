package dev.hardling.us.Listeners.Editor;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.ItemCreator;
import dev.hardling.us.Utils.provider.files.ConfigCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PermissionListener implements Listener {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();


    public static void invItems(Player p, String s) {

        //INVENTORY
        Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL);

        // ITEMS
        inv.setItem(0, (new ItemCreator(Material.PAPER)).build());

        // SOUND
        p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.CHEST_OPEN, 1.5F, 1.5F);
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {

            if (p instanceof Player) {
                Inventory inv = p.getInventory();
                inv.setItem(0, (new ItemCreator(Material.PAPER)).build());
                // ver si el evento es sobre un yunque
                if (inv instanceof AnvilInventory) {
                    InventoryView view = event.getView();
                    int rawSlot = event.getRawSlot();
                    // comparar la ranura en bruto con la vista del inventario para asegurarse de que se trata del inventario superior
                    if (rawSlot == view.convertSlot(rawSlot)) {
                        /*
                        slot 0 = left item slot
                        slot 1 = right item slot
                        slot 2 = result item slot
                        ver si el jugador ha hecho clic en la ranura del objeto resultante del inventario del yunque
                        */
                        if (rawSlot == 2) {
                            /*
                            obtener el elemento actual en la ranura de resultados
                            Creo que inv.getItem(rawSlot)  también sería posible
                            */
                            ItemStack item = event.getCurrentItem();
                            // comprobar si hay un elemento en la ranura de resultados
                            if (item != null) {
                                ItemMeta meta = item.getItemMeta();
                                // es posible que el artículo no tenga metadatos
                                if (meta != null) {
                                    // ver si el elemento está siendo renombrado
                                    if (meta.hasDisplayName()) {
                                        String displayName = meta.getDisplayName();
                                        gkitsFile.set("KITS." + s + ".PERMISSION", displayName);
                                        gkitsFile.save();
                                        p.sendMessage(CC.translate("&aThe permission &7(" + displayName + ") &ahas been changed correctly "));
                                        p.closeInventory();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*@EventHandler
    public void onEditor(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false))
            if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("Permission"))) {
                gkitsFile.set("KITS." + s + ".PERMISSION", event.getView().getTitle().toLowerCase());
                gkitsFile.saveAll();
                p.closeInventory();

        }*/
}
