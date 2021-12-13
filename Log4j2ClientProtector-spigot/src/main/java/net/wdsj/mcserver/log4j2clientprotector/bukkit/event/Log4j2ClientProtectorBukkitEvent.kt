package net.wdsj.mcserver.log4j2clientprotector.bukkit.event

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author  MeowRay
 * @date  2021/12/13 13:07
 * @version 1.0
 */
class Log4j2ClientProtectorBukkitEvent(val player: Player, val level: Int) : BaseEvent(false) {

}

abstract class BaseEvent(isAsync: Boolean) : Event(isAsync) {


    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    override fun getHandlers(): HandlerList {
        return getHandlerList()
    }


}