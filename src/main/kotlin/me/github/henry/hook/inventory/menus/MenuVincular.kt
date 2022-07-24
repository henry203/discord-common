package me.github.henry.hook.inventory.menus

import me.github.henry.hook.inventory.MenuProfile
import me.github.henry.hook.utils.ItemBuilder
import me.github.henry.hook.utils.SkullCreator
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

object MenuVincular {

    fun open(player: Player) {
        val inv: Inventory = Bukkit.createInventory(null, 27, "Deseja continuar?")

        inv.setItem(12, ItemBuilder(SkullCreator.itemFromUrl("be5a01fb26a10afd4742bdf6a1e360da010040ac4338371165309d4cea6ddbdf")).name("§aAceitar").build())
        inv.setItem(14, ItemBuilder(SkullCreator.itemFromUrl("fdbe15ca2593bffa588033d340fb8666313580bdf58362b5cb70b83bd191c12b")).name("§cRecusar").build())

        player.openInventory(inv)
    }
}