package me.mical.olympictop

import me.mical.olympictop.command.OlympicTopCommand
import taboolib.common.env.RuntimeDependencies
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.Plugin

@RuntimeDependencies(
    RuntimeDependency(
        value = "!org.jsoup:jsoup:1.14.1",
        test = "!org.jsoup.Jsoup"
    ),
    RuntimeDependency(
        value = "!com.alibaba:fastjson:1.2.71",
        test = "!com.alibaba.fastjson.JSON"
    )
)
object OlympicTop : Plugin() {

    override fun onEnable() {
        OlympicTopCommand.register()
    }
}