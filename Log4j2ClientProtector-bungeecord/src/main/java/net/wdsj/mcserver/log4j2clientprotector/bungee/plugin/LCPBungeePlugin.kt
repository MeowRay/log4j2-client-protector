package net.wdsj.mcserver.log4j2clientprotector.bungee.plugin

import dev.simplix.protocolize.api.Protocolize
import net.wdsj.mcserver.log4j2clientprotector.bungee.listener.ChatDownStreamPacketListener
import net.wdsj.mcserver.log4j2clientprotector.bungee.listener.ChatUpStreamPacketListener
import net.wdsj.mcserver.log4j2clientprotector.bungee.loader.LCPBungeeBootstrap
import net.wdsj.mcserver.log4j2clientprotector.common.plugin.AbstractLCPPlugin
import java.util.logging.Logger

/**
 * @author  MeowRay
 * @date  2021/12/10 18:26
 * @version 1.0
 */
class LCPBungeePlugin(private val bootstrap: LCPBungeeBootstrap) : AbstractLCPPlugin() {

    private val chatUpStreamListener = ChatUpStreamPacketListener(this)
    private val chatDownStreamListener = ChatDownStreamPacketListener(this)

    override fun enable() {
        Protocolize.listenerProvider().registerListener(chatUpStreamListener)
        Protocolize.listenerProvider().registerListener(chatDownStreamListener)
        logger.info("Log4j2(CVE-2021-44228)客户端保护已启用")

    }

    override fun disable() {
        Protocolize.listenerProvider().unregisterListener(chatUpStreamListener)
        Protocolize.listenerProvider().unregisterListener(chatDownStreamListener)
    }

    override val logger: Logger
        get() = bootstrap.loader.logger


}