package net.wdsj.mcserver.log4j2clientprotector.common.plugin

import java.util.*

/**
 * @author  MeowRay
 * @date  2021/12/10 18:30
 * @version 1.0
 */
abstract class AbstractLCPPlugin : LCPPlugin {

    open fun illegalAction(uuid: UUID, username: String, attach: String = "", level: Int = 1) {
        logger.info("检测到非法利用 player:$username ${attach.ifBlank { "" }} level:${level}")
    }

    //  fun execute(uuid: UUID, level: Int): Boolean {}

    override var receiveProtectLevel: Int = 2

}