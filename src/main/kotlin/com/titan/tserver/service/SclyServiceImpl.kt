package com.titan.tserver.service

import cn.jiguang.common.ClientConfig
import cn.jpush.api.JPushClient
import cn.jpush.api.push.model.Platform
import cn.jpush.api.push.model.PushPayload
import cn.jpush.api.push.model.audience.Audience
import cn.jpush.api.push.model.notification.Notification
import com.titan.tserver.dao.*
import com.titan.tserver.model.*
import com.titan.tserver.util.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.*
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec

/**
 *保存信息到数据库
 */

@Service
class SclyServiceImpl : SclyService {
    private val SUCCESS = "SUCCESS"
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
    private val dqxxRepository: DqxxRepository? = null

    //行政区划对应人员查询接口
    @Autowired
    private val peopleRepository: PeopleRepository? = null

    //行政区划id查询接口
    @Autowired
    private val dqxxidRepository: DqxxidRepository? = null


    val eventType = arrayOf("森林防火", "森林病虫害", "毁林", "盗伐")

    var dqxxid = ""
    /**
     * 用户登录
     */
    override fun login(name: String, psw: String): String {
        return try {
            val user = queryUserInfo(name)
            if (user != null) {
                if (user.password.equals(encrypt(psw))) {
                    dqxxid = queryDqcode2(user.unitid)
                    dqxxid
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
     * 数据加密
     */
    fun encrypt(src: String): String {
        val key = "AFA50D75"
        try {
            val srcEncode = java.net.URLEncoder.encode(src, "utf-8")
            val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")

            val desKeySpec = DESKeySpec(key.toByteArray(charset("UTF-8")))
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val secretKey = keyFactory.generateSecret(desKeySpec)

            val bytes = key.toByteArray(charset("UTF-8"))
            val iv = IvParameterSpec(bytes)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

            val b = cipher.doFinal(srcEncode.toByteArray(charset("UTF-8")))
            return toHexString(b)

        } catch (e: Exception) {
            print("加密异常：$e")
            return ""
        }

    }

    /**
     * 转换16进制
     */
    fun toHexString(bytes: ByteArray): String {
        val hexString = StringBuffer()
        for (i in bytes.indices) {
            val value = bytes[i].toInt() and 0xff
            if (value < 16) {
                hexString.append(0)
            }
            hexString.append(Integer.toHexString(value))
        }
        return hexString.toString().toUpperCase()
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
                FileUtil.decoderBase64File(picArray[i], "$uploadPath$username-$time.jpg")
                /*val upPicInfo=createUpPicInfo(
                        "E:/pic_output/$time.jpg","$time.jpg","",1,uploadinfoId)*/


                val busi_attachment: Busi_attachment? = Busi_attachment()
                //文件路径
                busi_attachment!!.path = "$uploadPath$username-$time.jpg"
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
     * 消息推送
     */
    override fun pushInfo(uploadinfo: UploadInfo) {
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
                    .setAudience(Audience.tag(queryDqcode(uploadinfo.district)))
                    .setNotification(Notification.android(content, title, map))
                    .build()

            val pushResult = jpushClient.sendPush(payload)
            println("推送返馈的信息:$pushResult")
        } catch (e: Exception) {
            println("推送异常:$e")
        }
    }

    fun queryUserInfo(name: String): BsUserbase {
        return userInfoRepository!!.findByusercode(name)
    }

    fun queryDqcode(name: String): String {
        val dic = dqxxRepository!!.findByName(name) ?: return ""
        return dic.djh
    }

    fun queryPeople(areaCode: String): String? {
        if (areaCode == "") {
            return ""
        }
        val peopleInfo = peopleRepository!!.findByAreacode(areaCode)
        if (peopleInfo == null) {
            return ""
        }
        return peopleInfo.infopeople
    }

    fun queryDqcode2(unitid: String): String {
        val unitInfo = dqxxidRepository!!.findByUnitid(unitid) ?: return ""
        return unitInfo.dqxxid
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