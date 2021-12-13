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
    fun String.isMatch() = contains("\${")

    @JvmStatic
    fun String.isMatchRegex() = matches(REGEX)

    @JvmStatic
    fun String.isMatchJndi() = lowercase().contains("{jndi:")

    @JvmStatic
    fun String.levelMatch(): Int {
        var level = 0
        if (isMatch()) {
            level++
            if (isMatchRegex()) {
                level++
            }
            if (isMatchJndi()) {
                level++
            }
        }
        return level
    }

    @JvmStatic
    fun String.replaceIllegal() = replace(REGEX, PLACEHOLDER)

}