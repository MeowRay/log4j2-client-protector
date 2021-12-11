package net.wdsj.mcserver.log4j2clientprotector.bukkit.listener

import net.wdsj.mcserver.log4j2clientprotector.bukkit.plugin.LCPBukkitPlugin
import net.wdsj.mcserver.log4j2clientprotector.bukkit.util.LCPBukkitUtils.isIllegalItem
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareAnvilEvent

/**
 * @author  MeowRay
 * @date  2021/12/11 21:59
 * @version 1.0
 */
class AnvilListener(val plugin: LCPBukkitPlugin) : Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    fun on(event: PrepareAnvilEvent) {
        if (event.result.isIllegalItem()) {
            event.result = null
            event.viewers.firstOrNull()?.let {
                plugin.illegalAction(it.uniqueId, it.name, "type:anvil")
            }
        }
    }
}