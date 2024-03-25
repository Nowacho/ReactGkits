package dev.hardling.us.Listeners;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
import dev.hardling.us.Utils.Cooldown;
import dev.hardling.us.Utils.ItemCreator;
import dev.hardling.us.Utils.provider.Config;
import dev.hardling.us.Utils.provider.Lang;
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

import java.util.List;

public class GkitListener implements Listener {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();
    private static ConfigCreator dataFile = ReactGkits.getInst().getDataFile();

    public static void invGkit(Player p) {
        // INVENTORY
        Inventory inv = Bukkit.createInventory(null, Config.GKIT_MENU_INVENTORY_SLOTS, CC.translate(Config.GKIT_MENU_TITLE));

        // ITEMS KITS
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
            String kits = "KITS." + s + ".";
            if (p.hasPermission(gkitsFile.getString(kits + "PERMISSION"))) {
                inv.setItem(gkitsFile.getInt(kits + "WITH_PERMISSIONS.SLOT"),
                        (new ItemCreator(Material.valueOf(gkitsFile.getString(kits + "WITH_PERMISSIONS.MATERIAL"))))
                                .setDisplayName(gkitsFile.getString(kits + "WITH_PERMISSIONS.DISPLAYNAME"))
                                .setLore(gkitsFile.getStringList(kits + "WITH_PERMISSIONS.LORE"))
                                .setGlow(gkitsFile.getBoolean(kits + "WITH_PERMISSIONS.ENCHANTED"))
                                .setDurability(gkitsFile.getInt(kits + "WITH_PERMISSIONS.DATA"))
                                .build());
            }
        }
        // GLASS PANEL
        if (Config.GKIT_MENU_GLASS_PANEL_ENABLED) {
            ItemStack panel = (new ItemCreator(Material.STAINED_GLASS_PANE, 1, (short) Config.GKIT_MENU_GLASS_PANEL_ID)).setName("").build();
            for (int i = 0; i < inv.getSize(); i++) {
                if (inv.getItem(i) == null) {
                    inv.setItem(i, panel);
                }
            }
        }
        // SOUND
        if (Config.GKIT_MENU_SOUND_ENABLED) {
            p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.valueOf(Config.GKIT_MENU_SOUND_SOUND), 1.5F, 1.5F);
        }
        p.openInventory(inv);
    }

    @EventHandler
    public void onEditor(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        Cooldown c = new Cooldown();
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) return;
        if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate(Config.GKIT_MENU_TITLE))) {
            // SEARCH KITS
            for (String string : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
                String kits = "KITS." + string + ".";
                // GET SLOT
                if (event.getSlot() == gkitsFile.getInt(kits + "WITH_PERMISSIONS.SLOT")) {
                    // KIT PERMISSION
                    if (p.hasPermission(gkitsFile.getString(kits + "PERMISSION"))) {
                        // COOLDOWN BYPASS
                        if (p.hasPermission("react.cooldown.bypass")) {
                            // CHECK YML ARMOR AND ITEMS
                            if (gkitsFile.contains(kits + "INVENTORY_ITEMS.ARMOR") && gkitsFile.contains(kits + "INVENTORY_ITEMS.ITEMS")) {
                                // KIT
                                ItemStack[] armor = ((List<ItemStack>) gkitsFile.get(kits + "INVENTORY_ITEMS.ARMOR")).stream().toArray(ItemStack[]::new);
                                ItemStack[] contents = ((List<ItemStack>) gkitsFile.get(kits + "INVENTORY_ITEMS.ITEMS")).stream().toArray(ItemStack[]::new);
                                p.getInventory().setArmorContents(armor);
                                p.getInventory().setContents(contents);
                                p.updateInventory();
                                // OTHERS
                                p.sendMessage(CC.translate(gkitsFile.getString(kits + "WITH_PERMISSIONS.MESSAGE")));
                                p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.valueOf(gkitsFile.getString(kits + "WITH_PERMISSIONS.SOUND.SOUND")), 1.5F, 1.5F);
                                p.closeInventory();
                            } else {
                                // KIT NO ITEMS
                                p.sendMessage(CC.translate(Lang.KIT_NO_ITEMS));
                            }
                        } else {
                            // NO COOLDOWN
                            if (c.getCooldown(p).equals("-1")) {
                                long millis = System.currentTimeMillis();
                                dataFile.set("PLAYER." + p.getUniqueId() + "." + string + ".COOLDOWN", millis);
                                dataFile.save();
                                // COOLDOWN
                            } else {
                                String msg = gkitsFile.getString(kits + "COOLDOWN.MESSAGE");
                                msg = msg.replace("%time%", c.getCooldown(p));
                                p.sendMessage(CC.translate(msg));
                                if (gkitsFile.getBoolean(kits + "COOLDOWN.SOUND.ENABLED")) {
                                    p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.valueOf(gkitsFile.getString(kits + "COOLDOWN.SOUND.SOUND")), 1.5F, 1.5F);
                                }
                            }
                        }
                        // NO PERMS
                    } else {
                        p.sendMessage(CC.translate(gkitsFile.getString(kits + "NO_PERMISSIONS.MESSAGE")));
                        if (gkitsFile.getBoolean(kits + "NO_PERMISSIONS.SOUND.ENABLED")) {
                            p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.valueOf(gkitsFile.getString(kits + "NO_PERMISSIONS.SOUND.SOUND")), 1.5F, 1.5F);
                        }
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}