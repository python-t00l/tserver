package com.titan.tserver.service

import com.titan.tserver.model.Busi_uploadinfo
import com.titan.tserver.model.UploadInfo

/**
 * 四川林业服务
 */
interface SclyService {
    /**
     *
     */
    fun uploadinfo(uploadinfo: UploadInfo): Boolean


    /**
     * 获取历史上报信息
     */
    fun getHisInfo(index:Int,size:Int):List<Busi_uploadinfo>

    /**
     * 视频存储
     */
    //fun saveVideo(path:String, name: String):Boolean

    /**
     * 文件信息存储
     */
    fun saveFileInfo(path:String,type:Int):Boolean

    /**
     * 信息推送
     */
//    fun pushInfo(uploadinfo: UploadInfo)

    fun login(name:String,psw:String):String
}