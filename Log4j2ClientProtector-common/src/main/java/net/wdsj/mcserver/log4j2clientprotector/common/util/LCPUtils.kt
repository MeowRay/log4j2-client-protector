package net.wdsj.mcserver.log4j2clientprotector.common.util

/**
 * @author  MeowRay
 * @date  2021/12/10 18:59
 * @version 1.0
 */
val PLACEHOLDER = "[ILLEGAL]"
val REGEX = "\\$\\{.*}".toRegex()

object LCPUtils {


    @JvmStatic
    fun String.isMatch() = lowercase().contains("\${jndi")

    @JvmStatic
    fun String.replaceIllegal() = replace(REGEX, PLACEHOLDER)

}