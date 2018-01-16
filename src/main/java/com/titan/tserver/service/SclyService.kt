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
    fun uploadinfo(uploadinfo: UploadInfo, uploadPath: String): Int

    /**
     * 图片存储
     * @param picArray 图片数组
     */
    fun savePic(picArray:Array<String>?,uploadPath:String,uploadid:String,username:String):Boolean

    /**
     * 获取历史上报信息
     */
    fun getHisInfo(index:Int,size:Int):List<Busi_uploadinfo>

    /**
     * 视频存储
     */
    fun saveVideo(path:String, name: String):Boolean

    /**
     * 信息推送
     */
//    fun pushInfo(uploadinfo: UploadInfo)

    fun login(name:String,psw:String):String
    /**
     * 图片信息插入数据库
     * @param upPicInfo 图片上报信息
     */
    //fun insertPicInfo(upPicInfo: UpPicInfo):Boolean
    /**
     * 创建图片存储信息实例
     * @param path 存储地址
     * @param title 文件名
     * @param remark 备注
     * @param type 文件类型
     * @param id 上报id
     */
    //fun createUpPicInfo(path:String,title:String,remark:String,type:Int,id:String?):UpPicInfo
}