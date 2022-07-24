package me.github.henry.hook.inventory

import me.github.henry.hook.inventory.menus.MenuAjuda
import me.github.henry.hook.inventory.menus.MenuVincular
import me.github.henry.hook.utils.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


object MenuProfile {

    fun open(player: Player) {
        val inv: Inventory = Bukkit.createInventory(null, 27, "Vincular")
        inv.setItem(12, ItemBuilder(Material.SIGN).name("§aInformações").lore("§7Nickname: §f" + player.name+ "§7", "", "§eClique para vincular.").build())
        inv.setItem(14, ItemBuilder(Material.BOOK_AND_QUILL).name("§6Ajuda").lore("§7Clique aqui para abrir o menu.").removeAttributes().build())

        player.openInventory(inv)
    }
}