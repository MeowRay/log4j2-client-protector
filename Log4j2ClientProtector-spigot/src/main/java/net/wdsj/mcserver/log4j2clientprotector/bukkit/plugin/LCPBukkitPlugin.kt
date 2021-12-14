package net.wdsj.mcserver.log4j2clientprotector.bukkit.plugin

import com.comphenix.protocol.ProtocolLibrary
import net.wdsj.mcserver.log4j2clientprotector.bukkit.config.LCPBukkitConfig
import net.wdsj.mcserver.log4j2clientprotector.bukkit.event.Log4j2ClientProtectorBukkitEvent
import net.wdsj.mcserver.log4j2clientprotector.bukkit.listener.AnvilListener
import net.wdsj.mcserver.log4j2clientprotector.bukkit.listener.ChatClientPacketListener
import net.wdsj.mcserver.log4j2clientprotector.bukkit.listener.ChatServerPacketListener
import net.wdsj.mcserver.log4j2clientprotector.bukkit.listener.PlayerListener
import net.wdsj.mcserver.log4j2clientprotector.bukkit.loader.LCPBukkitBootstrap
import net.wdsj.mcserver.log4j2clientprotector.common.plugin.AbstractLCPPlugin
import net.wdsj.servercore.WdsjServerAPI
import net.wdsj.servercore.database.frame.box.value.bytes.ymal.DatabaseBytesConfigValue
import net.wdsj.servercore.utils.extensions.BukkitListenerHolder
import net.wdsj.servercore.utils.extensions.invokeOrNew
import net.wdsj.servercore.utils.extensions.registerListener
import net.wdsj.servercore.utils.extensions.unregisterAllListener
import org.bukkit.Bukkit
import java.util.*
import java.util.logging.Logger

/**
 * @author  MeowRay
 * @date  2021/12/10 18:26
 * @version 1.0
 */
class LCPBukkitPlugin(val bootstrap: LCPBukkitBootstrap) : AbstractLCPPlugin(), BukkitListenerHolder {

    val existPaperComponent = kotlin.runCatching { Class.forName("io.papermc.paper.text.PaperComponents") }.isSuccess

    private val clientListener = ChatClientPacketListener(this)
    private val serverListener = ChatServerPacketListener(this)
    private val playerListener = PlayerListener(this)
    private val anvilListener = AnvilListener(this)
    private var config: LCPBukkitConfig = LCPBukkitConfig()

    override fun illegalAction(uuid: UUID, username: String, attach: String, level: Int) {
        super.illegalAction(uuid, username, attach, level)
        if (level > 0) {
            Bukkit.getScheduler().runTaskAsynchronously(bootstrap.loader, Runnable {
                Bukkit.getPluginManager().callEvent(Log4j2ClientProtectorBukkitEvent(Bukkit.getPlayer(uuid)!!, level))
            })
        }
    }

    override fun enable() {
        readConfig()
        config.apply {
            if (enableBukkitListener) {
                registerListener(playerListener)
                kotlin.runCatching {
                    Class.forName("org.bukkit.event.inventory.PrepareAnvilEvent")
                    registerListener(anvilListener)
                }
            }
            if (enablePacketListener) {
                ProtocolLibrary.getProtocolManager().addPacketListener(clientListener)
                ProtocolLibrary.getProtocolManager().addPacketListener(serverListener)
            }
        }
        logger.info("Log4j2(CVE-2021-44228)客户端保护已启用 $config ")

    }

    fun readConfig() {
        kotlin.runCatching {
            config = WdsjServerAPI.getConfigManager()
                .readServerGroupOrGlobal("Log4j2ClientProtector", DatabaseBytesConfigValue())
                .invokeOrNew(LCPBukkitConfig::class.java)
        }
    }

    override fun disable() {
        config.apply {
            if (enableBukkitListener) {
                unregisterAllListener()
            }
            if (enablePacketListener) {
                ProtocolLibrary.getProtocolManager().removePacketListener(clientListener)
                ProtocolLibrary.getProtocolManager().removePacketListener(serverListener)
            }
        }
    }

    override val logger: Logger
        get() = bootstrap.loader.logger

    override fun getPlugin() = bootstrap.loader
}