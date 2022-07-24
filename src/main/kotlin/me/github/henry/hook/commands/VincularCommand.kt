package me.github.henry.hook.commands

import me.github.henry.hook.inventory.MenuProfile
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class VincularCommand : CommandExecutor {

    override fun onCommand(p0: CommandSender?, p1: Command?, p2: String?, p3: Array<out String>?): Boolean {
        if(p0 is Player) {
            val player: Player = p0
            MenuProfile.open(player)
        }

        return false
    }
}