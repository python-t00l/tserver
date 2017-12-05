package com.titan.tserver.service

import com.titan.tserver.dao.AttachmentRepository
import com.titan.tserver.dao.UploadRepository
import com.titan.tserver.model.Busi_attachment
import com.titan.tserver.model.Busi_uploadinfo
import com.titan.tserver.model.UpPicInfo
import com.titan.tserver.model.UploadInfo
import com.titan.tserver.util.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

/**
 *保存信息到数据库
 */

@Service
class SclyServiceImpl {
    @Autowired
    //信息存储接口
    private val infoRepository: UploadRepository? = null
    @Autowired
    //图片存储接口
    private val picRepository: AttachmentRepository?=null
    //上报Id
    var uploadinfoId:String?=null

    /**
     * 信息存储
     * @param uploadinfo 上报信息
     */
    fun uploadinfo(uploadinfo: UploadInfo): Boolean {
        try {
            val busi_uploadinfo: Busi_uploadinfo?= Busi_uploadinfo()
            busi_uploadinfo!!.address=uploadinfo.address
            busi_uploadinfo.eventsubtype=uploadinfo.eventsubtype
            busi_uploadinfo.eventtype=uploadinfo.eventtype
            //busi_uploadinfo.id=uploadinfo.id
            busi_uploadinfo.info=uploadinfo.info
            busi_uploadinfo.lat=uploadinfo.lat.toString()
            busi_uploadinfo.lon=uploadinfo.lon.toString()
            busi_uploadinfo.phone=uploadinfo.phone
            busi_uploadinfo.remark=uploadinfo.remark
            busi_uploadinfo.username=uploadinfo.username
            infoRepository!!.save(busi_uploadinfo)
            uploadinfoId = busi_uploadinfo.id?.toString()
            return true
        }catch (e:Exception){
            println("异常:$e")
            return false
        }
    }

    /**
     * 图片存储
     * @param picArray 图片数组
     */
    fun savePic(picArray:Array<String>?):Boolean{
        try {
            if (picArray == null) {
                return true
            }
            for (i in picArray.indices){
                val dataFormat:SimpleDateFormat?=SimpleDateFormat("yyyyMMdd-HHmmssSSS")
                val date:Date?=Date()
                val time=dataFormat!!.format(date)
                FileUtil.decoderBase64File(picArray[i], "E:/pic_output/$time.jpg")
                val upPicInfo=createUpPicInfo(
                        "E:/pic_output/$time.jpg","$time.jpg","",1,uploadinfoId)
                insertPicInfo(upPicInfo)
            }
            return true
        }catch (e:Exception){
            println("error2:$e")
            return false
        }
    }

    /**
     * 图片信息插入数据库
     * @param upPicInfo 图片上报信息
     */
    fun insertPicInfo(upPicInfo: UpPicInfo):Boolean{
        try {
            val busi_attachment:Busi_attachment?=Busi_attachment()
            busi_attachment!!.path=upPicInfo.path
            busi_attachment.title=upPicInfo.title
            busi_attachment.remark=upPicInfo.remark
            busi_attachment.type=upPicInfo.type
            busi_attachment.uploadinfoid=upPicInfo.uploadinfoid
            picRepository!!.save(busi_attachment)
            return true
        }catch (e:Exception){
            println("error3:$e")
            return false
        }
    }

    /**
     * 创建图片存储信息实例
     * @param path 存储地址
     * @param title 文件名
     * @param remark 备注
     * @param type 文件类型
     * @param id 上报id
     */
    fun createUpPicInfo(path:String,title:String,remark:String,type:Int,id:String?):UpPicInfo{
        val upPicInfo:UpPicInfo?= UpPicInfo()
        upPicInfo!!.path=path
        upPicInfo.title=title
        upPicInfo.remark=remark
        upPicInfo.type=type
        upPicInfo.uploadinfoid=id
        return upPicInfo
    }
}