package com.titan.tserver.util

import cn.jiguang.common.ClientConfig
import cn.jpush.api.JPushClient
import cn.jpush.api.push.model.Platform
import cn.jpush.api.push.model.PushPayload
import cn.jpush.api.push.model.audience.Audience
import cn.jpush.api.push.model.notification.Notification
import com.titan.tserver.model.UploadInfo
import java.util.HashMap

/**
 * 极光推送
 */
class PushUtil {

    private val eventType = arrayOf("森林防火", "森林病虫害", "毁林", "盗伐")

    fun pushInfo(uploadinfo: UploadInfo, tag: String) {
        try {
            val jpushClient = JPushClient("2f77d400d836a48e290056b8", "6e103a551e75f6131c1a23e8", null, ClientConfig.getInstance())
            val map: MutableMap<String, String?> = HashMap()
            map.put("username", uploadinfo.username!!)
            map.put("address", uploadinfo.address!!)
            map.put("phone", uploadinfo.phone!!)
            map.put("eventtype", uploadinfo.eventtype!!)
            map.put("info", uploadinfo.info!!)
            map.put("remark", uploadinfo.remark)
            map.put("upinfotime", java.util.Date().time.toString())
            val title = eventType[Integer.parseInt(uploadinfo.eventtype) - 1]
            val content = uploadinfo.address
//        val people = queryPeople(queryDqcode(uploadinfo.district))
//        if (people == "") {
//            println("没有查询到推送对象")
////            return
//        }
//        val alias = people!!.split(",")

            val payload = PushPayload.newBuilder()
                    .setPlatform(Platform.all())
                    .setAudience(Audience.tag(tag))
                    .setNotification(Notification.android(content, title, map))
                    .build()

            val pushResult = jpushClient.sendPush(payload)
            println("推送返馈的信息:$pushResult")
        } catch (e: Exception) {
            println("推送异常:$e")
        }
    }
}