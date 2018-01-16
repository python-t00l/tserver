package com.titan.tserver.service

import com.titan.tserver.model.AlarmInfo
import com.titan.tserver.model.LoginResult

/**
 * 森林防火服务
 */
interface SlfhService {
    /**
     * 登陆
     */
    fun login(username:String,password:String,clendid:String): LoginResult?

    /**
     * 获取火警信息
     */
    fun  getAlarmInfos(dqid: String): AlarmInfo?
}