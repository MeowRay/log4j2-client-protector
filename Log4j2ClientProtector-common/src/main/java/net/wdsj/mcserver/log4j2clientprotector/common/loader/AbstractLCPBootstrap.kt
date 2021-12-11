package net.wdsj.mcserver.log4j2clientprotector.common.loader

import net.wdsj.mcserver.log4j2clientprotector.common.plugin.LCPPlugin
import net.wdsj.mcserver.log4j2clientprotector.common.LCPProvider
import net.wdsj.servercore.plugin.AbstractPluginLoaderBootstrap

/**
 * @author  MeowRay
 * @date  2021/12/10 17:58
 * @version 1.0
 */
abstract class AbstractLCPBootstrap : AbstractPluginLoaderBootstrap<LCPPlugin>(LCPProvider) {

    override fun onEnableAfter() {
        super.onEnableAfter()
        plugin.logger.info("有疑问请联系QQ 1213530749 By:Wdsj.net")
    }
}