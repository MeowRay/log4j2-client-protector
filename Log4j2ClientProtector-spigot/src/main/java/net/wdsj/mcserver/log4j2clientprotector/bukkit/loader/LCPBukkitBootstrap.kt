package net.wdsj.mcserver.log4j2clientprotector.bukkit.loader

import net.wdsj.mcserver.log4j2clientprotector.bukkit.BukkitLoaderPlugin
import net.wdsj.mcserver.log4j2clientprotector.bukkit.plugin.LCPBukkitPlugin
import net.wdsj.mcserver.log4j2clientprotector.common.loader.AbstractLCPBootstrap

/**
 * @author  MeowRay
 * @date  2021/12/10 18:22
 * @version 1.0
 */
class LCPBukkitBootstrap(val loader: BukkitLoaderPlugin) : AbstractLCPBootstrap() {

    override val plugin: LCPBukkitPlugin = LCPBukkitPlugin(this)

}