package me.github.henry.hook.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;

public class ItemBuilder {
    private ItemStack is;

    public ItemBuilder(Material m) {
        this(m, 1, (short)0);
    }

    public ItemBuilder(ItemStack is) {
        this.is = is.clone();
    }

    public ItemBuilder(Material m, int amount, short data) {
        this.is = new ItemStack(m, amount, data);
    }

    public ItemBuilder clone() {
        return new ItemBuilder(this.is);
    }

    public ItemBuilder durability(int dur) {
        this.is.setDurability((short)dur);
        return this;
    }

    public ItemBuilder name(String name) {
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder unsafeEnchantment(Enchantment ench, int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder enchant(Enchantment ench, int level) {
        ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder owner(String owner) {
        try {
            SkullMeta im = (SkullMeta)this.is.getItemMeta();
            im.setOwner(owner);
            this.is.setItemMeta(im);
        } catch (ClassCastException var3) {
        }

        return this;
    }

    public ItemBuilder infinityDurabilty() {
        this.is.setDurability((short)32767);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        ItemMeta im = this.is.getItemMeta();
        List<String> out = im.getLore() == null ? new ArrayList() : im.getLore();
        String[] var4 = lore;
        int var5 = lore.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String string = var4[var6];
            ((List)out).add(ChatColor.translateAlternateColorCodes('&', string));
        }

        im.setLore((List)out);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder listLore(List<String> lore) {
        ItemMeta im = this.is.getItemMeta();
        List<String> out = im.getLore() == null ? new ArrayList() : im.getLore();
        Iterator var4 = lore.iterator();

        while(var4.hasNext()) {
            String string = (String)var4.next();
            ((List)out).add(ChatColor.translateAlternateColorCodes('&', string));
        }

        im.setLore((List)out);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder woolColor(DyeColor color) {
        if (!this.is.getType().equals(Material.WOOL)) {
            return this;
        } else {
            this.is.setDurability((short)color.getDyeData());
            return this;
        }
    }

    public ItemBuilder amount(int amount) {
        if (amount > 64) {
            amount = 64;
        }

        this.is.setAmount(amount);
        return this;
    }

    public ItemBuilder removeAttributes() {
        ItemMeta meta = this.is.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        this.is.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.is;
    }

    public ItemBuilder color(Color color) {
        if (!this.is.getType().name().contains("LEATHER_")) {
            return this;
        } else {
            LeatherArmorMeta meta = (LeatherArmorMeta)this.is.getItemMeta();
            meta.setColor(color);
            this.is.setItemMeta(meta);
            return this;
        }
    }

    public static ItemStack createHead(String displayName, List<String> lore, String texture) {
        GameProfile profile = createGameProfile(texture, UUID.randomUUID());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        RefSet(headMetaClass, headMeta, "profile", profile);
        head.setItemMeta(headMeta);
        SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
        skullMeta.setDisplayName(displayName);
        skullMeta.setLore(lore);
        head.setItemMeta(skullMeta);
        return head;
    }

    public static ItemStack createHead(String displayName, String lore, String texture) {
        GameProfile profile = createGameProfile(texture, UUID.randomUUID());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        RefSet(headMetaClass, headMeta, "profile", profile);
        head.setItemMeta(headMeta);
        SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
        skullMeta.setDisplayName(displayName);
        String[] lines = lore.split("\n");
        ArrayList<String> Lore = new ArrayList();

        for(int i = 0; i < lines.length; ++i) {
            Lore.add(lines[i]);
        }

        skullMeta.setLore(Lore);
        head.setItemMeta(skullMeta);
        return head;
    }

    public static ItemStack createHead(String displayName, String texture) {
        GameProfile profile = createGameProfile(texture, UUID.randomUUID());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        if (!RefSet(headMetaClass, headMeta, "profile", profile)) {
            return null;
        } else {
            head.setItemMeta(headMeta);
            SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
            skullMeta.setDisplayName(displayName);
            head.setItemMeta(skullMeta);
            return head;
        }
    }

    public static ItemStack createHead(String displayName, GameProfile profile) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        if (!RefSet(headMetaClass, headMeta, "profile", profile)) {
            return null;
        } else {
            head.setItemMeta(headMeta);
            SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
            skullMeta.setDisplayName(displayName);
            head.setItemMeta(skullMeta);
            return head;
        }
    }

    public static GameProfile createGameProfile(String texture, UUID id) {
        GameProfile profile = new GameProfile(id, (String)null);
        PropertyMap propertyMap = profile.getProperties();
        propertyMap.put("textures", new Property("textures", texture));
        return profile;
    }

    public static ItemStack createPlayerHead(String displayname, String playername, String... lore) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
        skullMeta.setDisplayName(displayname);
        skullMeta.setOwner(playername);
        if (lore.length != 0) {
            String[] lines = lore[0].split("\n");
            ArrayList<String> Lore = new ArrayList();

            for(int i = 0; i < lines.length; ++i) {
                Lore.add(lines[i]);
            }

            skullMeta.setLore(Lore);
        }

        head.setItemMeta(skullMeta);
        return head;
    }

    public static ItemStack createItem(Material material, int amount, String itemname, short... data) {
        ItemStack item = data.length == 0 ? new ItemStack(material, amount) : new ItemStack(material, amount, data[0]);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemname);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, String itemname, String lore, short... data) {
        ItemStack item = data.length == 0 ? new ItemStack(material, amount) : new ItemStack(material, amount, data[0]);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemname);
        if (lore.length() != 0) {
            String[] lines = lore.split("\n");
            ArrayList<String> Lore = new ArrayList();

            for(int i = 0; i < lines.length; ++i) {
                Lore.add(lines[i]);
            }

            meta.setLore(Lore);
        }

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack renameStack(ItemStack stack, int newamount, String newitename, boolean remlore) {
        ItemStack item = stack.clone();
        item.setAmount(newamount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(newitename);
        if (remlore && meta.hasLore()) {
            List<String> lore = null;
            meta.setLore((List)lore);
        }

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack renameStack(ItemStack stack, int newamount, String newitename, String newlore, short... newdata) {
        if (newdata.length != 0) {
            stack.setAmount(newamount);
        }

        stack.setAmount(newamount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(newitename);
        if (newlore.length() != 0) {
            String[] lines = newlore.split("\n");
            ArrayList<String> Lore = new ArrayList();

            for(int i = 0; i < lines.length; ++i) {
                Lore.add(lines[i]);
            }

            meta.setLore(Lore);
        }

        stack.setItemMeta(meta);
        return stack;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        this.is.getItemMeta().addItemFlags(new ItemFlag[]{flag});
        this.is.setItemMeta(this.is.getItemMeta());
        return this;
    }

    public static boolean RefSet(Class<?> sourceClass, Object instance, String fieldName, Object value) {
        try {
            Field field = sourceClass.getDeclaredField(fieldName);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            int modifiers = modifiersField.getModifiers();
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            if ((modifiers & 16) == 16) {
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, modifiers & -17);
            }

            try {
                field.set(instance, value);
            } finally {
                if ((modifiers & 16) == 16) {
                    modifiersField.setInt(field, modifiers | 16);
                }

                if (!field.isAccessible()) {
                    field.setAccessible(false);
                }

            }

            return true;
        } catch (Exception var11) {
            Bukkit.getLogger().log(Level.WARNING, "Unable to inject Gameprofile", var11);
            return false;
        }
    }
}