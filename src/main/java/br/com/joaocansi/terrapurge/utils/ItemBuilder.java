package br.com.joaocansi.terrapurge.utils;

import br.com.joaocansi.terrapurge.Config;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(String configPath) {
        Material material = Material.getMaterial(Objects.requireNonNull(Config.getInstance().getString(configPath + ".id")));
        assert material != null;
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
        name(Objects.requireNonNull(Config.getInstance().getString(configPath + ".name")));
        description(Config.getInstance().getStringList(configPath + ".description"));
        glow(Config.getInstance().getBoolean(configPath + ".glow"));
    }

    public ItemBuilder(String configPath, int amount) {
        Material material = Material.getMaterial(Objects.requireNonNull(Config.getInstance().getString(configPath + ".id")));
        assert material != null;
        this.item = new ItemStack(material);
        this.item.setAmount(amount);
        this.meta = this.item.getItemMeta();
        name(Objects.requireNonNull(Config.getInstance().getString(configPath + ".name")));
        description(Config.getInstance().getStringList(configPath + ".description"));
        glow(Config.getInstance().getBoolean(configPath + ".glow"));
    }

    public ItemBuilder glow(boolean isGlow) {
        if (isGlow) {
            this.meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemBuilder name(String name) {
        this.meta.setDisplayName(name.replace("&", "§"));
        return this;
    }

    public ItemBuilder description(List<String> description) {
        this.meta.setLore(description
                .stream()
                .map(x -> x.replace("&", "§"))
                .collect(Collectors.toList()));
        return this;
    }

    public ItemStack toItemStack() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
