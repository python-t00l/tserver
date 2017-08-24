package com.titan.tserver.service

import com.titan.server.model.LoginResult

/**
 * 森林防火服务
 */
interface SlfhService {
    /**
     * 登陆
     */
    fun login(username:String,password:String,clendid:String): LoginResult
}