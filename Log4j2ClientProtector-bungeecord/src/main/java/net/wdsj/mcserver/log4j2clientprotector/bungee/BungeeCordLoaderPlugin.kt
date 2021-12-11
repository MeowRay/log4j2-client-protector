package net.wdsj.mcserver.log4j2clientprotector.bungee

import net.md_5.bungee.api.plugin.Plugin
import net.wdsj.mcserver.log4j2clientprotector.bungee.loader.LCPBungeeBootstrap

class BungeeCordLoaderPlugin : Plugin() {

    val bootstrap: LCPBungeeBootstrap = LCPBungeeBootstrap(this)

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