package me.mical.olympictop.inventory

import me.mical.olympictop.serializers.MedalSerializer
import me.mical.olympictop.utils.Request
import org.bukkit.inventory.Inventory
import taboolib.library.xseries.XMaterial
import taboolib.module.ui.buildMenu
import taboolib.module.ui.type.Linked
import taboolib.platform.util.buildItem
import taboolib.platform.util.inventoryCenterSlots

object TopMenu {

    val menu: Inventory = buildMenu<Linked<MedalSerializer>>("") {
        rows(6)
        slots(inventoryCenterSlots)
        handLocked(true)
        elements {
            Request.getTop()
        }
        onGenerate { _, element, _, _ ->
            buildItem(XMaterial.PAPER) {
                name = "&f${element.countryNameSSY}"
                lore.addAll(
                    arrayListOf(
                        " ",
                        "&8| &7金牌: &6${element.gold}",
                        "&8| &7银牌: &f${element.silver}",
                        "&8| &7铜牌: &c${element.bronze}",
                        " ",
                        "&8| &7总数: &b${element.total}",
                        "&8| &7排名: &a${element.rank}",
                        " "
                    )
                )
                colored()
            }
        }
        setNextPage(51) { _, hasNextPage ->
            if (hasNextPage) {
                buildItem(XMaterial.SPECTRAL_ARROW) {
                    name = "&7下一页"
                    colored()
                }
            } else {
                buildItem(XMaterial.ARROW) {
                    name = "&8下一页"
                    colored()
                }
            }
        }
        setPreviousPage(47) { _, hasPreviousPage ->
            if (hasPreviousPage) {
                buildItem(XMaterial.SPECTRAL_ARROW) {
                    name = "&7上一页"
                    colored()
                }
            } else {
                buildItem(XMaterial.ARROW) {
                    name = "&8上一页"
                    colored()
                }
            }
        }
    }
}