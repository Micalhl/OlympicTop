package me.mical.olympictop.command

import me.mical.olympictop.api.OlympicAPI
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command

object OlympicTopCommand {

    fun register() {
        command(
            name = "olympic",
            permission = "OlympicTop.use",
            permissionMessage = "${ChatColor.RED}I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.",
        ) {
            literal("open") {
                dynamic(optional = true) {
                    execute<ProxyCommandSender> { _, _, argument ->
                        val player = Bukkit.getPlayerExact(argument)
                        OlympicAPI.openMenu(player)
                    }
                }
                execute<Player> { sender, _, _ ->
                    OlympicAPI.openMenu(sender)
                }
                incorrectSender { sender, _ ->
                    sender.sendMessage("只有玩家才可以执行此命令...")
                }
            }
        }
    }
}