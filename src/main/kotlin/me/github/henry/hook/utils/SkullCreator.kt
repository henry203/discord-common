package me.github.henry.hook.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import java.net.URI
import java.net.URISyntaxException
import java.util.*

object SkullCreator {

    @Deprecated("")
    fun itemFromName(name: String): ItemStack {
        val item = playerSkullItem
        return itemWithName(item, name)
    }

    @Deprecated("")
    fun itemWithName(item: ItemStack?, name: String): ItemStack {
        notNull(item, "item")
        notNull(name, "name")
        return Bukkit.getUnsafe().modifyItemStack(item, "{SkullOwner:\"$name\"}")
    }

    fun itemFromUrl(url: String): ItemStack {
        val item = playerSkullItem
        return itemWithUrl(item, "https://textures.minecraft.net/texture/$url")
    }

    fun itemWithUrl(item: ItemStack?, url: String?): ItemStack {
        notNull(item, "item")
        notNull(url, "url")
        return itemWithBase64(item, urlToBase64(url))
    }

    fun itemWithBase64(item: ItemStack?, base64: String): ItemStack {
        notNull(item, "item")
        notNull(base64, "base64")
        val hashAsId = UUID(base64.hashCode().toLong(), base64.hashCode().toLong())
        return Bukkit.getUnsafe().modifyItemStack(item, "{SkullOwner:{Id:\"$hashAsId\",Properties:{textures:[{Value:\"$base64\"}]}}}")
    }

    private fun newerApi(): Boolean {
        return try {
            Material.valueOf("PLAYER_HEAD")
            true
        } catch (var1: IllegalArgumentException) {
            false
        }
    }

    private val playerSkullItem: ItemStack
        private get() = if (newerApi()) ItemStack(Material.valueOf("PLAYER_HEAD")) else ItemStack(Material.valueOf("SKULL_ITEM"), 1, 3.toShort())


    private fun notNull(o: Any?, name: String) {
        if (o == null) {
            throw NullPointerException("$name should not be null!")
        }
    }

    private fun urlToBase64(url: String?): String {
        val actualUrl: URI
        actualUrl = try {
            URI(url)
        } catch (var3: URISyntaxException) {
            throw RuntimeException(var3)
        }
        val toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"$actualUrl\"}}}"
        return Base64.getEncoder().encodeToString(toEncode.toByteArray())
    }
}