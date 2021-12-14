package net.wdsj.mcserver.log4j2clientprotector.bukkit.listener

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import io.papermc.paper.text.PaperComponents
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.chat.ComponentSerializer
import net.wdsj.mcserver.log4j2clientprotector.bukkit.plugin.LCPBukkitPlugin
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.levelMatch
import net.wdsj.mcserver.log4j2clientprotector.common.util.LCPUtils.replaceIllegal

/**
 * @author  MeowRay
 * @date  2021/12/11 20:10
 * @version 1.0
 */
class ChatClientPacketListener(val plugin0: LCPBukkitPlugin) :
    PacketAdapter(plugin0.bootstrap.loader, ListenerPriority.LOWEST, PacketType.Play.Client.CHAT) {


    override fun onPacketReceiving(event: PacketEvent) {
        val msg = event.packet.strings.read(0)
        if (msg != null) {
            msg.levelMatch().takeIf { it > 0 }?.let {
                plugin0.illegalAction(event.player.uniqueId,
                    event.player.name,
                    "type:player->server message:${msg.replaceIllegal()}", level = it)
                event.isCancelled = true
            }
        }
    }


}

class ChatServerPacketListener(val plugin0: LCPBukkitPlugin) :
    PacketAdapter(plugin0.bootstrap.loader, ListenerPriority.LOWEST, PacketType.Play.Server.CHAT) {


    override fun onPacketSending(event: PacketEvent) {
        val jsons = event.packet.chatComponents.read(0)?.json?.run { listOf(this) }
            ?: readBaseComponent(listOf(event.packet.modifier.read(1), event.packet.modifier.read(2)))
        jsons.firstNotNullOfOrNull { it -> it.levelMatch().takeIf { it >= plugin0.receiveProtectLevel } }?.let {
            plugin0.illegalAction(event.player.uniqueId,
                event.player.name,
                "type:server->player json:${jsons.joinToString(" ").replaceIllegal()}", level = 0)
            event.isCancelled = true
            return
        }
    }


    private fun readBaseComponent(values: List<Any>): List<String> {
        return values.map {
            when {
                it is Array<*> && it.isArrayOf<BaseComponent>() -> {
                    ComponentSerializer.toString(it)
                }
                plugin0.existPaperComponent && it is net.kyori.adventure.text.Component -> {
                    PaperComponents.gsonSerializer().serialize(it)
                }
                else -> ""
            }
        }
    }


}