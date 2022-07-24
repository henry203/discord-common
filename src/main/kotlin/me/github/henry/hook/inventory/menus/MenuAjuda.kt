package me.github.henry.hook.inventory.menus

import me.github.henry.hook.utils.ItemBuilder
import me.github.henry.hook.utils.SkullCreator
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory


object MenuAjuda  {

    fun openHelp(player: Player) {
        val inv: Inventory = Bukkit.createInventory(null, 45, "Ajuda")
        inv.setItem(13, ItemBuilder(Material.SKULL_ITEM).name("§aInformações").lore("§7", "§fNickname: §7" + player.name + "", "§fúltimo acesso: §7XX/XX/2022").removeAttributes().build())
        inv.setItem(30, ItemBuilder(Material.ITEM_FRAME).name("§aVincular sua conta").lore("§7Vincule sua conta para", "§7aprimorar uma proteção em", "§7sua conta.", "", "§eClique para vincular!").removeAttributes().build())
        inv.setItem(32, ItemBuilder(Material.BOOK_AND_QUILL).name("§6Remover vinculação").lore("§7Mudou de conta? ou Discord? retire", "§7o vinculo de sua conta por aqui!", "", "§eClique para retirar!").removeAttributes().build())

        player.openInventory(inv)
    }
}