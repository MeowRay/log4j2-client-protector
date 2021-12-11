package net.wdsj.mcserver.log4j2clientprotector.bukkit.util

import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.isMatch
import org.bukkit.inventory.ItemStack

/**
 * @author  MeowRay
 * @date  2021/12/11 22:00
 * @version 1.0
 */
object LCPBukkitUtils {

    @JvmStatic
    fun ItemStack?.isIllegalItem() = (this?.itemMeta?.displayName ?: "").isMatch()

}