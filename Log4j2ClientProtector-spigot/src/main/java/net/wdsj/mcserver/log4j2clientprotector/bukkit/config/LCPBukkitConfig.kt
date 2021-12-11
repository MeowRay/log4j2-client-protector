package net.wdsj.mcserver.log4j2clientprotector.bukkit.config

/**
 * @author  MeowRay
 * @date  2021/12/11 21:21
 * @version 1.0
 */
data class LCPBukkitConfig(
    var enablePacketListener: Boolean = true,
    var enableBukkitListener: Boolean =true

    ) {
}