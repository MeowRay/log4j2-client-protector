package net.wdsj.mcserver.log4j2clientprotector.bukkit

import net.wdsj.mcserver.log4j2clientprotector.bukkit.loader.LCPBukkitBootstrap
import org.bukkit.plugin.java.JavaPlugin

class BukkitLoaderPlugin : JavaPlugin() {

    val bootstrap: LCPBukkitBootstrap = LCPBukkitBootstrap(this)

    override fun onEnable() {
        // Plugin startup logic
        bootstrap.onEnable()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        bootstrap.onDisable()

    }

    override fun onLoad() {
        // Plugin shutdown logic
        bootstrap.onLoad()

    }
}