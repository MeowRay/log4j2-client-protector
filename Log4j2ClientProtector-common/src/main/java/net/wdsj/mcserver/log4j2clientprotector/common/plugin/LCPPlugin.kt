package net.wdsj.mcserver.log4j2clientprotector.common.plugin

import net.wdsj.servercore.plugin.WdsjPlugin
import java.util.logging.Logger

/**
 * @author  MeowRay
 * @date  2021/12/10 18:03
 * @version 1.0
 */
interface LCPPlugin : WdsjPlugin {

    val logger: Logger
}