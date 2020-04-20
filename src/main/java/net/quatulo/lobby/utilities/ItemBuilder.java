package net.quatulo.lobby.utilities;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private List<String> loreList;

    public ItemBuilder() {
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
        this.itemMeta = this.itemStack.getItemMeta();
        this.loreList = new ArrayList();
    }

    public ItemBuilder(Material material, int amount, int subID) {
        this.itemStack = new ItemStack(material, amount, (short) subID);
        this.itemMeta = this.itemStack.getItemMeta();
        this.loreList = new ArrayList();
    }

    public ItemBuilder(int ID, int amount, int subID) {
        this.itemStack = new ItemStack(Material.getMaterial(ID), amount, (short) subID);
        this.itemMeta = this.itemStack.getItemMeta();
        this.loreList = new ArrayList();
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.itemMeta.setDisplayName(displayName);

        return this;
    }

    public ItemBuilder setUnbreakable(Boolean value) {
        this.itemMeta.spigot().setUnbreakable(value.booleanValue());

        return this;
    }

    public ItemBuilder setGlow() {
        this.itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});

        return this;
    }

    public ItemBuilder setOwner(String owner) {
        ((SkullMeta) this.itemMeta).setOwner(owner);

        return this;
    }

    public ItemBuilder setColor(Color color) {
        ((LeatherArmorMeta) this.itemMeta).setColor(color);

        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);

        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(new ItemFlag[]{itemFlag});

        return this;
    }

    public ItemBuilder addLoreLine(String loreLine) {
        this.loreList.add(loreLine);

        return this;
    }

    public ItemStack create() {
        if (!this.loreList.isEmpty()) {
            this.itemMeta.setLore(this.loreList);
        }
        this.itemStack.setItemMeta(this.itemMeta);

        return this.itemStack;
    }
}

