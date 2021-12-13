package net.wdsj.mcserver.log4j2clientprotector.bungee.event

import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Event

/**
 * @author  MeowRay
 * @date  2021/12/13 13:07
 * @version 1.0
 */
class Log4j2ClientProtectorBungeeEvent(val player: ProxiedPlayer, val level: Int) : Event() {
}