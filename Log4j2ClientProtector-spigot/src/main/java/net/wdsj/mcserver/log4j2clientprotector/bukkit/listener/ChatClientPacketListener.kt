package net.wdsj.mcserver.log4j2clientprotector.bukkit.listener

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import net.wdsj.mcserver.log4j2clientprotector.bukkit.plugin.LCPBukkitPlugin
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.isMatch
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.replaceIllegal

/**
 * @author  MeowRay
 * @date  2021/12/11 20:10
 * @version 1.0
 */
class ChatClientPacketListener(val plugin0: LCPBukkitPlugin) :
    PacketAdapter(plugin0.bootstrap.loader, ListenerPriority.LOWEST, PacketType.Play.Client.CHAT) {


    override fun onPacketReceiving(event: PacketEvent) {
        val message = (event.packet.strings.read(0) as String)
        if (message.isMatch()) {
            plugin0.illegalAction(event.player.uniqueId,
                event.player.name,
                "type:player->server message:${message.replaceIllegal()}")
            event.isCancelled = true
        }
    }


}

class ChatServerPacketListener(val plugin0: LCPBukkitPlugin) :
    PacketAdapter(plugin0.bootstrap.loader, ListenerPriority.LOWEST, PacketType.Play.Server.CHAT) {


    override fun onPacketSending(event: PacketEvent) {
        val msg = event.packet.chatComponents.read(0).json
        if (msg.isMatch()) {
            plugin0.illegalAction(event.player.uniqueId,
                event.player.name,
                "type:server->player json:${msg.replaceIllegal()}")
            event.isCancelled = true
            return
        }
    }


}