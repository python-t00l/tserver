package com.titan.tserver.web

import com.google.gson.Gson
import com.titan.tserver.model.ResultData
import com.titan.tserver.model.UploadInfo
import com.titan.tserver.service.SclyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import com.google.gson.reflect.TypeToken
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.util.StringUtils
import java.net.URLDecoder


/**
 * 四川林业公众平台服务
 */
@RestController//默认以json返回数据
@EnableAutoConfiguration
@RequestMapping(value = "scly/api")
class SclyController {

    //val counter = AtomicLong()


    @Autowired
    private val service: SclyService? = null
    //文件存储
    @Autowired
    private val storageService: StorageService? = null

    @RequestMapping("/")
    fun home(): String {
        return "Hello World!"
    }

    /**
     * 信息上报
     * @param uploadinfo 上报信息
     */
//    @PostMapping("/uploadinfo")
//    fun uploadinfo(@RequestBody uploadinfo: UploadInfo, request: HttpServletRequest): ResultData {

    fun uploadinfo(uploadinfo: UploadInfo, UploadPath: String): String {
        try {
            //whs:ZO4b7R2S4SK9yH4JmdDd+w==
            //val newpsd=EncryptUtil.EncoderByMd5("whs")
//            val UploadPath: String = request.contextPath + "UploadFiles/images"
            //文字信息上报
            val result = service!!.uploadinfo(uploadinfo, UploadPath)
            //图片上报
            //val  request: HttpServletRequest =request
            //val pic_result = service.savePic(uploadinfo.picArray,UploadPath)
            System.out.print("uploadinfo:" + uploadinfo.toString())
            if (result == 1) {//
//                service!!.pushInfo(uploadinfo)
                return "上报成功"
            } else if (result == 3) {
                return "信息上报失败"
            } else {
                return "图片上报失败"
            }
        } catch (e: Exception) {
            return "上报异常" + e
        }
    }

    @RequestMapping("/gethistoryinfo")
    fun gethistoryinfo(@RequestParam("pageIndex", defaultValue = "0") index: Int
                       , @RequestParam("pageSize", defaultValue = "10") size: Int): ResultData {
        val result = service!!.getHisInfo(index, size)
        return ResultData(true, result, "成功")
    }

    /**
     * 文件上传
     */
    @PostMapping("/uploadfile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResultData {

        try {
            storageService!!.store(file)
            FileUtil.uploadFile(file.bytes, UploadPath, fileName)
        } catch (e: Exception) {
            // TODO: handle exception
            println("上传异常" + e)
            return ResultData(false, "文件上传", "上传异常" + e)

        }
        return ResultData(true, "文件上传", "上报成功")
    }

    //@RequestParam("file") files: Array<MultipartFile>,
    //json: UploadInfo,
    /**
     * 多个文件上传
     */
    @PostMapping("/uploadfiles")
    fun uploadFiles(@RequestParam("file") files: Array<MultipartFile>,
                    request: HttpServletRequest): ResultData {

        val json = request.getParameter("json")

        val gson = Gson()
        val type = object : TypeToken<UploadInfo>() {}.type
        val info = gson.fromJson<UploadInfo>(json, type)
        val UploadPath: String = request.contextPath + "UploadFiles\\images"
        try {
            val result = uploadinfo(info, UploadPath)
            for (file in files) {
                storageService!!.store(file)
                val filename = URLDecoder.decode(StringUtils.cleanPath(file.originalFilename),"utf-8")
                val path = storageService!!.load(filename)
                service!!.saveVideo(path.toString(), filename)
            }
            return when (result) {
                "上报成功" -> {
                    service!!.pushInfo(info)
                    ResultData(true, result, result)
                }
                "信息上报失败" -> ResultData(false, result, result)
                "图片上报失败" -> ResultData(false, result, result)
                else -> ResultData(false, result, result)
            }
        } catch (e: Exception) {
            // TODO: handle exception
            System.out.println("上传异常" + e)
            return ResultData(false, "文件上传", "上传异常" + e)

        }
    }

    @RequestMapping("/login")
    fun login(@RequestParam("name") name: String,
              @RequestParam("password") psw: String): ResultData {
        val result = service!!.login(name, psw)
        return when (result) {
            "LOGIN_ERR" -> ResultData(false, result, "登录异常，请稍后再试")
            "PSW_ERR" -> ResultData(false, result, "密码错误")
            "USERNAME_ERR" -> ResultData(false, result, "用户名错误")
            else -> ResultData(true, result, "登录成功")
        }
    }

}