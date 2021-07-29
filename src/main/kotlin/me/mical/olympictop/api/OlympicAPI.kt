package me.mical.olympictop.api

import me.mical.olympictop.inventory.TopMenu
import org.bukkit.entity.Player

object OlympicAPI {

    fun openMenu(player: Player?) {
        player?.openInventory(TopMenu.menu)
    }
}