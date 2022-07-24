package me.github.henry.hook.events

import me.github.henry.hook.inventory.MenuProfile
import me.github.henry.hook.inventory.menus.MenuAjuda
import me.github.henry.hook.inventory.menus.MenuVincular
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent


class PlayerInteractEvent  : Listener {


    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.whoClicked is Player) {
            val player: Player = event.whoClicked as Player
            if (event.clickedInventory == null) {
                return
            }

            if (event.clickedInventory.title.equals("Vincular")) {
                event.isCancelled = true

                when (event.rawSlot) {
                    12 -> {
                        player.closeInventory()
                        MenuVincular.open(player)
                    }
                    14 -> {
                        player.closeInventory()
                        MenuAjuda.openHelp(player)
                    }
                }
            } else if (event.clickedInventory.title.equals("Deseja continuar?")) {
                event.isCancelled = true
                when (event.rawSlot) {
                    12 -> {
                        player.closeInventory()
                        player.chat("/discordsrv link")
                    }

                    14 -> {
                        player.closeInventory()
                        MenuProfile.open(player)
                    }
                }
            } else if (event.clickedInventory.title.equals("Ajuda")) {
                event.isCancelled = true
                when (event.rawSlot) {
                    30 -> {
                        player.closeInventory()
                        player.chat("/discordsrv link")
                    }

                    32 -> {
                        player.closeInventory()
                        player.chat("/discordsrv unlink")
                    }
                }
            }
        }
    }
}