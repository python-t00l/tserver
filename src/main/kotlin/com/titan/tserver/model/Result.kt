package com.titan.server.model



data class ResultData(val result: Boolean, val content: Any?,val msg: String)

//{"dqid":"1470","dqName":"贵阳市","role":"超级管理员","accountStatus":"1","clientID":"1222","userID":"1","dqLevel":"3"}
//data class LoginResult(val dqid: String,val clientid: String,val userid: String)

/**
 * 服务端返回结果
 */
//class Result<T>{
//    //结果标识
//    var result: Boolean? = null
//    //返回描述
//    var msg: String? = null
//    //返回数据
//    var data:T? = null
//
//    constructor() {}
//}