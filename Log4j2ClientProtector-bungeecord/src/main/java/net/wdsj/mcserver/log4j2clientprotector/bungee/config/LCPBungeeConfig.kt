package net.wdsj.mcserver.log4j2clientprotector.bungee.config

/**
 * @author  MeowRay
 * @date  2021/12/13 0:37
 * @version 1.0
 */
data class LCPBungeeConfig(
    var executor: Map<Int, ExecutorConfig> = emptyMap(),

    ) {
}

data class ExecutorConfig(
    var commands: List<String> = emptyList(),

    ) {

}