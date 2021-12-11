package net.wdsj.mcserver.log4j2clientprotector.bungee.loader

import net.wdsj.mcserver.log4j2clientprotector.bungee.BungeeCordLoaderPlugin
import net.wdsj.mcserver.log4j2clientprotector.bungee.plugin.LCPBungeePlugin
import net.wdsj.mcserver.log4j2clientprotector.common.loader.AbstractLCPBootstrap

/**
 * @author  MeowRay
 * @date  2021/12/10 18:22
 * @version 1.0
 */
class LCPBungeeBootstrap(val loader: BungeeCordLoaderPlugin) : AbstractLCPBootstrap() {

    override val plugin: LCPBungeePlugin = LCPBungeePlugin(this)

    override fun onLoadBefore() {
        super.onLoadBefore()
    }

    override fun onEnableBefore() {
        super.onEnableBefore()
    }

    override fun onDisableBefore() {
        super.onDisableBefore()
    }

}