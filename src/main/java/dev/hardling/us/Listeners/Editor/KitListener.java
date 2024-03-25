package dev.hardling.us.Listeners.Editor;

import dev.hardling.us.ReactGkits;
import dev.hardling.us.Utils.CC;
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

import java.util.Arrays;

public class KitListener implements Listener {

    private static ConfigCreator gkitsFile = ReactGkits.getInst().getGkitFile();

    public static void invEditorKit(Player p, String s) {
        // INVENTORY
        Inventory inv = Bukkit.createInventory(null, 54, CC.translate("&3&l" + s + " Editor"));

        if (gkitsFile.contains("KITS." + s)) {
            // ITEM CHANGE
            inv.setItem(13, (new ItemCreator(Material.valueOf(gkitsFile.getString("KITS." + s + ".WITH_PERMISSIONS.MATERIAL"))))
                    .setDisplayName(gkitsFile.getString("KITS." + s + ".WITH_PERMISSIONS.DISPLAYNAME"))
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/Left Click To Change the Icon of the Item to be displayed", CC.LINELIGHCUT))
                    .setGlow(gkitsFile.getBoolean("KITS." + s + ".WITH_PERMISSIONS.ENCHANTED"))
                    .setDurability(gkitsFile.getInt("KITS." + s + ".WITH_PERMISSIONS.DATA"))
                    .build());
            // SET LOOT
            if (gkitsFile.contains("KITS." + s + ".INVENTORY_ITEMS.ARMOR") && gkitsFile.contains("KITS." + s + ".INVENTORY_ITEMS.ITEMS")) {
                inv.setItem(19, (new ItemCreator(Material.STORAGE_MINECART))
                        .setDisplayName("&6Place Items &7(&aThere is already a Kit Placed&7)")
                        .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/left click to add items to the kit", CC.LINELIGHCUT))
                        .build());
            } else {
                inv.setItem(19, (new ItemCreator(Material.MINECART))
                        .setDisplayName("&6Place Items &7(&cNo Kit Placed&7)")
                        .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/left click to add items to the kit", CC.LINELIGHCUT))
                        .build());
            }
            // SOUND
            if (gkitsFile.getBoolean("KITS." + s + ".WITH_PERMISSIONS.SOUND.ENABLED")) {
                inv.setItem(29, (new ItemCreator(Material.NOTE_BLOCK))
                        .setDisplayName("&bSound &7(&aActivated&7)")
                        .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/left click to deactivate or activate the kit sound.", CC.LINELIGHCUT))
                        .build());
            } else {
                inv.setItem(29, (new ItemCreator(Material.JUKEBOX))
                        .setDisplayName("&bSound &7(&cDisabled&7)")
                        .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/left click to deactivate or activate the kit sound.", CC.LINELIGHCUT))
                        .build());
            }
            // ENCHANMENT
            if (gkitsFile.getBoolean("KITS." + s + ".WITH_PERMISSIONS.ENCHANTED")) {
                inv.setItem(30, (new ItemCreator(Material.ENCHANTMENT_TABLE))
                        .setDisplayName("&3Enchantment &7(&aActivated&7)")
                        .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/Left Click To disable the item's Enchantment.", CC.LINELIGHCUT))
                        .build());
            } else {
                inv.setItem(30, (new ItemCreator(Material.ENDER_PORTAL_FRAME))
                        .setDisplayName("&3Enchantment &7(&cDisabled&7)")
                        .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/Left Click To activate the item's Enchantment.", CC.LINELIGHCUT))
                        .build());
            }
            // VIEW INVENTORY
            inv.setItem(40, (new ItemCreator(Material.CHEST))
                    .setDisplayName("&aView inventory")
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Right/left click To see the items placed in the kit.", CC.LINELIGHCUT))
                    .build());
            // SET NAME KIT
            inv.setItem(32, (new ItemCreator(Material.ANVIL))
                    .setDisplayName("&4Change Kit name")
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7&7Right/left click to Change the Kit name", CC.LINELIGHCUT))
                    .build());
            // PERMISSION
            inv.setItem(33, (new ItemCreator(Material.SIGN))
                    .setDisplayName("&6Permission")
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7&7Right/left click to Change the kit permission", CC.LINELIGHCUT))
                    .build());
            // SET SLOT
            inv.setItem(25, (new ItemCreator(Material.HOPPER))
                    .setDisplayName("&dSet a slot")
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7&7Right/left click to Set a slot to display in kit", CC.LINELIGHCUT))
                    .build());
            // DELETE KIT
            inv.setItem(45, (new ItemCreator(Material.RED_ROSE))
                    .setDisplayName("&4&l❃ &c&lDelete Kit &4&l❃")
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7&7Right/left click to remove the kit ", CC.LINELIGHCUT))
                    .build());
            // BACK MENU
            inv.setItem(53, (new ItemCreator(Material.FEATHER))
                    .setDisplayName("&cBack to previous menu")
                    .setLore(Arrays.asList(CC.LINELIGHCUT, "&7Back to old menu", CC.LINELIGHCUT))
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
        for (String s : gkitsFile.getConfigurationSection("KITS").getKeys(false)) {
            if (event.getInventory().getTitle().equalsIgnoreCase(CC.translate("&3&l" + s + " Editor"))) {
                // EDITOR ITEM
                if (event.getSlot() == 13) {
                    ItemStack stack = event.getCurrentItem();
                    if (stack == null || stack.getType().equals(Material.AIR) || stack.getType().equals(Material.valueOf(gkitsFile.getString("KITS." + s + ".WITH_PERMISSIONS.MATERIAL"))) || event.getSlotType() == null) {
                        p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
                        p.sendMessage(CC.translate(Config.MATERIAL_INVALID));
                        return;
                    }
                    gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.MATERIAL", String.valueOf(event.getView().getItem(13).getType()));
                    gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.DATA", event.getView().getItem(13).getDurability());
                    gkitsFile.save();
                    p.sendMessage(CC.translate(Config.MATERIAL_CORRECTLY));
                    p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
                }
                // SET LOOT
                if (event.getSlot() == 19) {
                    Config.setItems(p, s);
                    p.sendMessage(CC.translate(Lang.KIT_COMMAND_CORRETLY_PLACED));
                    invEditorKit(p, s);
                }
                // SOUND
                if (event.getSlot() == 29) {
                    if (gkitsFile.getBoolean("KITS." + s + ".WITH_PERMISSIONS.SOUND.ENABLED")) {
                        gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.SOUND.ENABLED", false);
                        p.sendMessage(CC.translate(Config.SOUND_DISABLED));
                    } else {
                        gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.SOUND.ENABLED", true);
                        p.sendMessage(CC.translate(Config.SOUND_ACTIVATE));
                    }
                    gkitsFile.save();
                    invEditorKit(p, s);
                }
                // VIEW INVENTORY
                if (event.getSlot() == 40) {
                   // ViewInventoryListener.ViewInvKit(p, s);
                    p.sendMessage(CC.translate("&7Coming soon..."));
                }
                // ENCHANMENT
                if (event.getSlot() == 30) {
                    if (gkitsFile.getBoolean("KITS." + s + ".WITH_PERMISSIONS.ENCHANTED")) {
                        gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.ENCHANTED", false);
                        p.sendMessage(CC.translate(Config.ENCHANTED_DISABLED));
                    } else {
                        gkitsFile.set("KITS." + s + ".WITH_PERMISSIONS.ENCHANTED", true);
                        p.sendMessage(CC.translate(Config.ENCHANTED_ACTIVATE));
                    }
                    gkitsFile.save();
                    invEditorKit(p, s);
                }
                // EDITOR PERMISSION
                if (event.getSlot() == 33) {
                    //PermissionListener.invItems(p, s);
                    p.sendMessage(CC.translate("&7Coming soon..."));
                }
                // SET NAME KIT
                if (event.getSlot() == 32) {
                    p.sendMessage(CC.translate("&7Coming soon..."));
                }
                // SET SLOT
                if (event.getSlot() == 25) {
                    SlotListener.invEditorKit(p, s);
                }
                // DELETE KIT
                if (event.getSlot() == 45) {
                    gkitsFile.set("KITS." + s, null);
                    EditorListener.invGkit(p);
                    p.sendMessage(CC.translate(Lang.KIT_COMMAND_SUCCESSFULLY_DELETE));
                }
                // BACK TO MENU
                if (event.getSlot() == 53) {
                    EditorListener.invGkit(p);
                }
                event.setCancelled(true);
            }
        }
    }
}