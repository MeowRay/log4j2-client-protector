package net.wdsj.mcserver.log4j2clientprotector.bungee.plugin

import dev.simplix.protocolize.api.Protocolize
import net.md_5.bungee.api.ProxyServer
import net.wdsj.mcserver.log4j2clientprotector.bungee.config.LCPBungeeConfig
import net.wdsj.mcserver.log4j2clientprotector.bungee.event.Log4j2ClientProtectorBungeeEvent
import net.wdsj.mcserver.log4j2clientprotector.bungee.listener.ChatDownStreamPacketListener
import net.wdsj.mcserver.log4j2clientprotector.bungee.listener.ChatUpStreamPacketListener
import net.wdsj.mcserver.log4j2clientprotector.bungee.loader.LCPBungeeBootstrap
import net.wdsj.mcserver.log4j2clientprotector.common.plugin.AbstractLCPPlugin
import net.wdsj.servercore.WdsjServerAPI
import net.wdsj.servercore.component.bungee.BungeeServerManager
import net.wdsj.servercore.database.frame.box.value.bytes.ymal.DatabaseBytesConfigValue
import net.wdsj.servercore.utils.extensions.invokeOrNew
import net.wdsj.servercore.utils.extensions.replacePlaceholder
import java.util.*
import java.util.logging.Logger

/**
 * @author  MeowRay
 * @date  2021/12/10 18:26
 * @version 1.0
 */
class LCPBungeePlugin(private val bootstrap: LCPBungeeBootstrap) : AbstractLCPPlugin() {

    private val chatUpStreamListener = ChatUpStreamPacketListener(this)
    private val chatDownStreamListener = ChatDownStreamPacketListener(this)
    private var config: LCPBungeeConfig = LCPBungeeConfig()


    override fun illegalAction(uuid: UUID, username: String, attach: String, level: Int) {
        super.illegalAction(uuid, username, attach, level)

        ProxyServer.getInstance().pluginManager.callEvent(Log4j2ClientProtectorBungeeEvent(ProxyServer.getInstance()
            .getPlayer(uuid), level))

        config.executor[level]?.let { executor ->
            if (!BungeeServerManager.isBlackServer(BungeeServerManager.getServerDetailByName(ProxyServer.getInstance()
                    .getPlayer(uuid).server.info.name))
            ) {
                ProxyServer.getInstance().scheduler.runAsync(bootstrap.loader) {
                    executor.commands.forEach {
                        ProxyServer.getInstance().pluginManager.dispatchCommand(ProxyServer.getInstance().console,
                            it.replace("{player}", username)
                                .replacePlaceholder(WdsjServerAPI.getPlayerService().getPlayerEntity(uuid)))
                    }
                }
            }
        }
    }

    override fun enable() {
        readConfig()
        Protocolize.listenerProvider().registerListener(chatUpStreamListener)
        Protocolize.listenerProvider().registerListener(chatDownStreamListener)
        logger.info("Log4j2(CVE-2021-44228)客户端保护已启用")

    }

    fun readConfig() {
        kotlin.runCatching {
            config = WdsjServerAPI.getConfigManager()
                .readServerGroupOrGlobal("Log4j2ClientProtector#Bungee", DatabaseBytesConfigValue())
                .invokeOrNew(LCPBungeeConfig::class.java)
        }
    }

    override fun disable() {
        Protocolize.listenerProvider().unregisterListener(chatUpStreamListener)
        Protocolize.listenerProvider().unregisterListener(chatDownStreamListener)
    }

    override val logger: Logger
        get() = bootstrap.loader.logger


}