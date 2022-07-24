package me.github.henry.hook

import me.github.henry.hook.commands.VincularCommand
import me.github.henry.hook.events.PlayerInteractEvent
import me.github.henry.hook.inventory.MenuProfile
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable


/**
 * Plugin Interação com o DiscordSRV
 * é usado somente em uso com o DiscordSRV com sistema de menus e etc..
 *
 * @author henry
 */


class BukkitMain : JavaPlugin() {

    override fun onEnable() {

        this.getCommand("vincular").executor = VincularCommand()
        this.logger.info("O plugin foi carregado.")
        this.onSetup()

    }

    private fun onSetup() {
        object : BukkitRunnable() {
            override fun run() {
                Bukkit.getPluginManager().registerEvents(PlayerInteractEvent(), this@BukkitMain)
            }
        }.runTask(this)
    }

    override fun onDisable() {
        this.logger.info("O plugin foi desativado.")
    }

}