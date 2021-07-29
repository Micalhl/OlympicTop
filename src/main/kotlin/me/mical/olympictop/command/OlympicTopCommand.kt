package me.mical.olympictop.command

import me.mical.olympictop.api.OlympicAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command

object OlympicTopCommand {

    fun register() {
        command("olympic") {
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