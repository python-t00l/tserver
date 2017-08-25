package com.titan.tserver.model


/**
 * 登陆返回结果
 * 定义接口接收返回多表查询结果
 */
interface LoginResult {
    fun getDqid(): String
    fun getUserid(): String
    fun getClientid(): String
    fun getDqlevel(): String
}