package com.titan.tserver.service

import com.titan.tserver.model.UploadInfo

/**
 * 森林防火服务
 */
interface SclyService {
    /**
     *
     */
    fun uploadinfo(uploadinfo: UploadInfo, uploadPath: String): Boolean

    /**
     * 图片存储
     * @param picArray 图片数组
     */
    fun savePic(picArray:Array<String>?,uplaodPath:String,uploadId:String,userName:String):Boolean

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