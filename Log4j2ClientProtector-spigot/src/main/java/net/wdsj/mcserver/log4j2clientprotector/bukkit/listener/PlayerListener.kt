package net.wdsj.mcserver.log4j2clientprotector.bukkit.listener

import net.wdsj.mcserver.log4j2clientprotector.bukkit.plugin.LCPBukkitPlugin
import net.wdsj.mcserver.log4j2clientprotector.bukkit.util.LCPBukkitUtils.isIllegalItem
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.isMatch
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCreativeEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.inventory.PrepareAnvilEvent

/**
 * @author  MeowRay
 * @date  2021/12/11 20:36
 * @version 1.0
 */
class PlayerListener(val plugin: LCPBukkitPlugin) : Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    fun on(event: InventoryClickEvent) {
        if (event.clickedInventory?.type == InventoryType.ANVIL) {
            if (event.currentItem.isIllegalItem()) {
                plugin.illegalAction(event.whoClicked.uniqueId,event.whoClicked.name ,"type:inventory-click")
                event.isCancelled = true
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    fun on(event: InventoryCreativeEvent) {
        if (event.currentItem.isIllegalItem()) {
            event.isCancelled = true
            plugin.illegalAction(event.whoClicked.uniqueId, event.whoClicked.name, "type:creative")
        }
    }

}