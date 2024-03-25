package dev.hardling.us.Utils;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class ItemCreator {
    private ItemStack itemStack;

    public ItemCreator(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemCreator(Material material) {
        this(material, 1);
    }

    public ItemCreator ItemCreator(Material material) {
        this.itemStack = itemStack;
        return this;
    }

    public ItemCreator(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ItemCreator(Material material, int amount, short damage) {
        this(new ItemStack(material, amount, damage));
    }


    public ItemCreator setColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) this.itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        this.itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }

    public ItemCreator setType(Material material) {
        this.itemStack.setType(material);
        return this;
    }

    public ItemCreator setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemCreator setDurability(int durability) {
        this.itemStack.setDurability((short) durability);
        return this;
    }

    public ItemCreator setData(int data) {
        this.itemStack.setData(new MaterialData(data));
        return this;
    }

    public ItemCreator addEnchantment(Enchantment enchantment) {
        addEnchantment(enchantment, 1);
        return this;
    }

    public ItemCreator addEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemCreator removeEnchantment(Enchantment enchantment) {
        this.itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemCreator clearEnchantments() {
        for (Enchantment enchantment : this.itemStack.getEnchantments().keySet())
            this.itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemCreator setDisplayName(String name) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator addLore(String lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<String> lores = itemMeta.getLore();
        if (lores == null)
            lores = Lists.newArrayList();
        lores.add(CC.translate(lore));
        itemMeta.setLore(lores);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator setLore(String... lores) {
        clearLore();
        for (String lore : lores)
            addLore(lore);
        return this;
    }

    public ItemCreator setLore(List<String> lore) {
        clearLore();
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(CC.translate(lore));
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator clearLore() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Lists.newArrayList());
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator setPotionEffect(PotionEffect effect) {
        PotionMeta potionMeta = (PotionMeta) this.itemStack.getItemMeta();
        potionMeta.setMainEffect(effect.getType());
        potionMeta.addCustomEffect(effect, false);
        this.itemStack.setItemMeta(potionMeta);
        return this;
    }

    public ItemCreator setGlow(boolean type) {
        if (type) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemStack build() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        ItemStack clone = this.itemStack.clone();
        clone.setItemMeta(itemMeta.clone());
        return clone;
    }

    public ItemCreator setPotionEffects(PotionEffect... effects) {
        PotionMeta potionMeta = (PotionMeta) this.itemStack.getItemMeta();
        potionMeta.setMainEffect(effects[0].getType());
        for (PotionEffect effect : effects)
            potionMeta.addCustomEffect(effect, false);
        this.itemStack.setItemMeta(potionMeta);
        return this;
    }

    public ItemCreator setSkullOwner(String owner) {
        SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();
        skullMeta.setOwner(owner);
        this.itemStack.setItemMeta(skullMeta);
        return this;
    }

    public ItemCreator setName(String displayName) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemStack create() {
        return this.itemStack;
    }
}