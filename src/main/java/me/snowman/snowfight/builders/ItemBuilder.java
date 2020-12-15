package me.snowman.snowfight.builders;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private ItemStack item;

    public ItemBuilder(Material material){
        this(material, 1);
    }

    public ItemBuilder(ItemStack item){
        this.item = item;
    }

    public ItemBuilder(Material material, int amount){
        item = new ItemStack(material, amount);
    }

    public ItemBuilder clone(){
        return new ItemBuilder(item);
    }

    @Deprecated
    public ItemBuilder setDurability(short dur){
        item.setDurability(dur);
        return this;
    }

    public ItemBuilder setName(String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level){
        item.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench){
        item.removeEnchantment(ench);
        return this;
    }

    @Deprecated
    public ItemBuilder setSkullOwner(String owner){
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(owner);
            item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(ench, level, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
        item.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setLore(String... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder hideItemFlags(){
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack build(){
        return item;
    }
}
