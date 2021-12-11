# log4j2-client-protector
Bungee&Bukkit防止Log4j2(CVE-2021-44228)漏洞入侵玩家客户端

本插件主要作用是在已修复漏洞的服务器上防御其玩家客户端受到侵害
保护自己保护他人

bungeecord服务器仅在bungeecord上安装即可实现基础防御功能

当然为了达到100%的效果，也可以同时在bungeecord和bukkit安装

#

bungeecord: 阻止 bukkit->玩家、玩家->bungee的远程执行代码

bukkit: 阻止 玩家->bukkit、玩家铁砧物品更名、创造模式更名、bukkit->玩家等

