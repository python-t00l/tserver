package com.titan.tserver.service

import com.titan.tserver.dao.*
import com.titan.tserver.model.Busi_attachment
import com.titan.tserver.model.Busi_uploadinfo
import com.titan.tserver.model.UploadInfo
import com.titan.tserver.util.EncryptUtil
import com.titan.tserver.util.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 *保存信息到数据库
 */

@Service
class SclyServiceImpl : SclyService {
    private val PSW_ERR = "PSW_ERR"
    private val USERNAME_ERR = "USERNAME_ERR"
    private val LOGIN_ERR = "LOGIN_ERR"
    var uploadinfoId = ""

    @Autowired
    //信息存储接口
    private val infoRepository: UploadRepository? = null

    @Autowired
    //附件存储接口
    private val attachmentRepository: AttachmentRepository? = null

    //历史信息接口
    @Autowired
    private val historyRepository: HistoryInfoRepository? = null

    //用户登录接口
    @Autowired
    private val userInfoRepository: UserInfoRepository? = null

    //行政区划id查询接口
    @Autowired
    private val dqxxidRepository: DqxxidRepository? = null

    /**
     * 用户登录
     * @param name 用户名
     * @param psw 密码
     */
    override fun login(name: String, psw: String): String {
        return try {
            val user =  userInfoRepository!!.findByusercode(name)
            if (user != null) {
                if (user.password.equals(EncryptUtil().encrypt(psw))) {
                    dqxxidRepository!!.findByUnitid(user.unitid).dqxxid
                } else {
                    PSW_ERR
                }
            } else {
                USERNAME_ERR
            }
        } catch (e: Exception) {
            print("登录异常:$e")
            LOGIN_ERR
        }
    }


    /**
     * 信息存储
     * @param uploadinfo 上报信息
     * @return 1:上报成功；2:图片上报失败；3:上报失败
     */
    override fun uploadinfo(uploadinfo: UploadInfo, uploadPath: String): Int {
        try {
            val busi_uploadinfo: Busi_uploadinfo? = Busi_uploadinfo()
            busi_uploadinfo!!.address = uploadinfo.address
            //busi_uploadinfo.eventsubtype=uploadinfo.eventsubtype
            busi_uploadinfo.eventtype = uploadinfo.eventtype
            //busi_uploadinfo.id=uploadinfo.id
            busi_uploadinfo.info = uploadinfo.info
            busi_uploadinfo.lat = uploadinfo.lat.toString()
            busi_uploadinfo.lon = uploadinfo.lon.toString()
            busi_uploadinfo.phone = uploadinfo.phone
            busi_uploadinfo.remark = uploadinfo.remark
            busi_uploadinfo.username = uploadinfo.username
            val dt = java.util.Date()
            val date = java.sql.Timestamp(dt.time)
            busi_uploadinfo.upinfotime = date
            infoRepository!!.save(busi_uploadinfo)
            uploadinfoId = busi_uploadinfo.id?.toString()!!
            val result = savePic(uploadinfo.picArray, uploadPath, uploadinfoId, uploadinfo.username!!)
            if (result) {
                return 1
            } else {
                return 2
            }
        } catch (e: Exception) {
            println("异常:$e")
            return 3
        }
    }

    /**
     * 图片存储
     * @param picArray 图片数组
     */
    override fun savePic(picArray: Array<String>?, uploadPath: String, uploadid: String, username: String): Boolean {
        try {
            if (picArray == null) {
                return true
            }
            for (i in picArray.indices) {
                val dataFormat: SimpleDateFormat? = SimpleDateFormat("yyyyMMdd-HHmmssSSS")
                val date: Date? = Date()
                val time = dataFormat!!.format(date)
                //如果文件目录不存在创建目录
                val file: File = File(uploadPath)
                if (!file.exists()) {
                    file.mkdirs()
                }
                //val ProjectPath:String =ClassUtils.getDefaultClassLoader().getResource("upload_images").path
                FileUtil.decoderBase64File(picArray[i], "$uploadPath\\$username-$time.jpg")
                /*val upPicInfo=createUpPicInfo(
                        "E:/pic_output/$time.jpg","$time.jpg","",1,uploadinfoId)*/


                val busi_attachment: Busi_attachment? = Busi_attachment()
                //文件路径
                busi_attachment!!.path = "$uploadPath\\$username-$time.jpg"
                //文件名
                busi_attachment.title = "$username-$time.jpg"
                //备注
                //busi_attachment.remark=upPicInfo.remark
                //文件类型
                busi_attachment.type = 1
                //事件ID
                busi_attachment.uploadinfoid = uploadid
                attachmentRepository!!.save(busi_attachment)
                //insertPicInfo(upPicInfo)
            }
            return true
        } catch (e: Exception) {
            println("error2:$e")
            return false
        }
    }

    /**
     * 获取上报历史信息
     */
    override fun getHisInfo(index: Int, size: Int): List<Busi_uploadinfo> {
        val sort = Sort(Sort.Direction.DESC, "upinfotime")
        val pageable = PageRequest(index, size, sort)
        val iterable = historyRepository!!.findAll(pageable)
        val list = ArrayList<Busi_uploadinfo>()
        val iterator = iterable.iterator()
        while (iterator.hasNext()) {
            list.add(iterator.next())
        }
        return list
    }

    /**
     * 视频存储
     */
    override fun saveVideo(path: String, name: String): Boolean {
        try {
            val busi_attachment: Busi_attachment? = Busi_attachment()
            //文件路径
            busi_attachment!!.path = path
            //文件名
            busi_attachment.title = name
            //备注
            //busi_attachment.remark=upPicInfo.remark
            //文件类型 1-图片，2-声音，3-视频
            busi_attachment.type = 3
            //事件ID
            busi_attachment.uploadinfoid = uploadinfoId
            attachmentRepository!!.save(busi_attachment)
            return true
        } catch (e: Exception) {
            println("文件入库异常：$e")
            return false
        }
    }

    /**
     * 图片信息插入数据库
     * @param upPicInfo 图片上报信息
     */
    /*fun insertPicInfo(upPicInfo: UpPicInfo):Boolean{
        try {
            val busi_attachment:Busi_attachment?=Busi_attachment()
            busi_attachment!!.path=upPicInfo.path
            busi_attachment.title=upPicInfo.title
            busi_attachment.remark=upPicInfo.remark
            busi_attachment.type=upPicInfo.type
            busi_attachment.uploadinfoid=upPicInfo.uploadinfoid
            attachmentRepository!!.save(busi_attachment)
            return true
        }catch (e:Exception){
            println("error3:$e")
            return false
        }
    }*/

    /**
     * 创建图片存储信息实例
     * @param path 存储地址
     * @param title 文件名
     * @param remark 备注
     * @param type 文件类型
     * @param id 上报id
     */
    /*fun createUpPicInfo(path:String,title:String,remark:String,type:Int,id:String?):UpPicInfo{
        val upPicInfo:UpPicInfo?= UpPicInfo()
        upPicInfo!!.path=path
        upPicInfo.title=title
        upPicInfo.remark=remark
        upPicInfo.type=type
        upPicInfo.uploadinfoid=id
        return upPicInfo
    }*/
}