package me.mical.olympictop.utils

import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.parser.Feature
import me.mical.olympictop.serializers.MedalSerializer
import org.jsoup.Jsoup
import java.security.SecureRandom

object Request {

    private const val MEDAL_URL = "http://match.2020.sina.com.cn/pc/api/medal/all?ak=h4a7ot1i"

    private val USER_AGENT = arrayOf(
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/73.0.3683.103"
    )

    private fun getRandomUserAgent(): String = USER_AGENT[SecureRandom().nextInt(5)]

    fun getTop(): List<MedalSerializer> {
        return try {
            var result = listOf<MedalSerializer>()
            val medalData =
                Jsoup.connect(MEDAL_URL).ignoreContentType(true).userAgent(getRandomUserAgent()).execute().body()
            val medalDataObj = JSONObject.parseObject(medalData, Feature.OrderedField)
            val successCode = medalDataObj.getJSONObject("result").getJSONObject("status").getInteger("code")
            if (successCode == 0) {
                val dataObj = medalDataObj.getJSONObject("result").getJSONObject("data")
                val totalArray = dataObj.getJSONArray("total")
                result = totalArray.toJavaList(MedalSerializer::class.java)
            }
            result
        } catch (e: Throwable) {
            e.printStackTrace()
            arrayListOf()
        }
    }
}