# log4j2-client-protector
Bungee&Bukkit防止Log4j2(CVE-2021-44228)漏洞入侵玩家客户端

本插件主要作用是在已修复Log4j2漏洞(升级log4j2版本)的服务器上防御其玩家客户端受到侵害
（理论上同时安装bc和bukkit在未修复的服务端上也能起到拦截作用）

保护他人，为玩家负责

bungeecord服务器仅在bungeecord上安装即可实现基础防御功能

当然为了达到100%的效果，也可以同时在bungeecord和bukkit安装

Bukkit前置需求: ProtocolLib

Bungee前置需求: Protocolize (以下环境包已包含)


## 特性

* bungeecord: 阻止 bukkit->玩家、玩家->bungee的远程执行代码
* bukkit: 阻止 玩家->bukkit、玩家铁砧物品更名远程执行代码、创造模式更名远程执行代码、bukkit->玩家等
* 日志记录非法利用

## 演示

![image](https://user-images.githubusercontent.com/29674595/145683145-355d4205-296d-4a34-a0ea-aeaecbe2a9fd.png)

![image](https://user-images.githubusercontent.com/29674595/145683148-9b46bb0c-fd04-4306-88da-7c3fb58682ef.png)
