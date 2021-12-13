package net.wdsj.mcserver.log4j2clientprotector.bungee.listener

import dev.simplix.protocolize.api.Direction
import dev.simplix.protocolize.api.listener.AbstractPacketListener
import dev.simplix.protocolize.api.listener.PacketReceiveEvent
import dev.simplix.protocolize.api.listener.PacketSendEvent
import net.md_5.bungee.api.config.ServerInfo
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.protocol.packet.Chat
import net.wdsj.mcserver.log4j2clientprotector.bungee.plugin.LCPBungeePlugin
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.levelMatch
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.replaceIllegal

/**
 * @author  MeowRay
 * @date  2021/12/11 14:10
 * @version 1.0
 */
class ChatUpStreamPacketListener(val plugin: LCPBungeePlugin) :
    AbstractPacketListener<Chat>(Chat::class.java, Direction.UPSTREAM, -1) {

    override fun packetReceive(event: PacketReceiveEvent<Chat>) {
        event.packet().message.levelMatch().takeIf { it > 0 }?.let {
            val handle = event.player().handle<ProxiedPlayer>()
            plugin.illegalAction(handle.uniqueId,
                handle.name,
                "type:user->bungeecord server:${event.server<ServerInfo>()}",
                level = it)
            event.cancelled(true)
        }
    }

    override fun packetSend(event: PacketSendEvent<Chat>) {
        if (event.packet().message.levelMatch() >=  plugin.receiveProtectLevel) {
            val handle = event.player().handle<ProxiedPlayer>()
            plugin.illegalAction(handle.uniqueId,
                handle.name,
                "type:bungeecord->user server:${event.server<ServerInfo>()}", level = 0)
            event.packet().message = event.packet().message.replaceIllegal()
        }
    }

}

class ChatDownStreamPacketListener(val plugin: LCPBungeePlugin) :
    AbstractPacketListener<Chat>(Chat::class.java, Direction.DOWNSTREAM, -1) {

    /*json*/
    override fun packetReceive(event: PacketReceiveEvent<Chat>) {
        if (event.packet().message.levelMatch() >= plugin.receiveProtectLevel) {
            val handle = event.player().handle<ProxiedPlayer>()
            plugin.illegalAction(handle.uniqueId,
                handle.name,
                "type:spigot->bungee server:${event.server<ServerInfo>()?.name} message:${event.packet().message.replaceIllegal()}",
                level = 0)
            event.cancelled(true)
        }
    }

    override fun packetSend(p0: PacketSendEvent<Chat>?) {

    }


}