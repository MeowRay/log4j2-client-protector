package net.wdsj.mcserver.log4j2clientprotector.common.plugin

import java.util.*

/**
 * @author  MeowRay
 * @date  2021/12/10 18:30
 * @version 1.0
 */
abstract class AbstractLCPPlugin : LCPPlugin {

    fun illegalAction(uuid: UUID, username: String, attach: String = "") {
        logger.info("检测到非法利用 player:$username ${attach.ifBlank { "" }}")
    }



}