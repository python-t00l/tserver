package com.titan

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
import com.titan.tserver.dao.DqxxRepository
import com.titan.tserver.storage.StorageService
import com.titan.tserver.util.FileUtil
import com.titan.tserver.util.PushUtil
import org.apache.log4j.LogManager
import org.springframework.util.StringUtils
import java.io.File
import java.net.URLDecoder


/**
 * 四川林业公众平台服务
 */
@RestController//默认以json返回数据
@RequestMapping(value = "scly/api")
class SclyController {

    //val counter = AtomicLong()

    private val logger = LogManager.getLogger(SclyController::class.java)


    @Autowired
    private val sclyservice: SclyService? = null

    @Autowired
    private val dqxxRepository: DqxxRepository? = null
    //文件存储
    @Autowired
    private var storageService: StorageService? = null


    @GetMapping("/")
    fun home(): String {
        return "Hello World!"
    }

    @RequestMapping("/gethistoryinfo")
    fun gethistoryinfo(@RequestParam("pageIndex", defaultValue = "0") index: Int
                       , @RequestParam("pageSize", defaultValue = "10") size: Int): ResultData {
        val result = sclyservice!!.getHisInfo(index, size)
        return ResultData(true, result, "成功")
    }

    /**
     * 文件上传
     */
    @PostMapping("/uploadfile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResultData {

        try {
            storageService!!.store(file)
            //FileUtil.uploadFile(file.bytes, UploadPath, fileName)
        } catch (e: Exception) {
            // TODO: handle exception
            println("上传异常" + e)
            return ResultData(false, "文件上传", "上传异常" + e)

        }
        return ResultData(true, "文件上传", "上报成功")
    }


    /**
     * 多个文件上传
     */
    @PostMapping("/uploadfiles")
    fun uploadFiles(@RequestParam("file") files: Array<MultipartFile>,
                    request: HttpServletRequest): ResultData {

        val json = request.getParameter("json")


        val UploadPath: String = request.getSession().servletContext.getRealPath(File.separator) + "UploadFiles" + File.separator + "imgs"

        try {
            //val result = uploadinfo(info, UploadPath)
            val type = object : TypeToken<UploadInfo>() {}.type
            val info = Gson().fromJson<UploadInfo>(json, type)
            val result = sclyservice!!.uploadinfo(info)
            println("文件存储开始")
            for (file in files) {
                storageService!!.store(file)
                val filename = URLDecoder.decode(StringUtils.cleanPath(file.originalFilename), "utf-8")
                val path = storageService!!.load(file.contentType, filename)
                FileUtil.uploadFile(file.bytes, UploadPath, filename)
                when (file.contentType) {
                    "image/*" -> sclyservice!!.saveFileInfo(path.toString(), 1)
                    "video/*" -> sclyservice!!.saveFileInfo(path.toString(), 3)
                }
                println("文件存储完成")
                logger.info("文件存储完成")

            }
            return when (result) {
                true -> {
                    val dic = dqxxRepository!!.findByName(info.district)
                    if (dic == null) {
                        ResultData(true, result, "上报信息成功，但没有查询到所属区域")
                    } else {
                        PushUtil().pushInfo(info, dic.djh)
                        logger.info("上报信息成功")
                        ResultData(true, result, "上报信息成功")
                    }
                }
            //"信息上报失败" -> ResultData(false, result, result)
            //"图片上报失败" -> ResultData(false, result, result)
                else -> ResultData(false, result, "上报信息失败")
            }
        } catch (e: Exception) {
            println("上传异常" + e)
            logger.info("上报异常" + e)
            return ResultData(false, "文件上传", "上传异常" + e.message)

        }
    }

    @RequestMapping("/login")
    fun login(@RequestParam("name") name: String,
              @RequestParam("password") psw: String): ResultData {
        val result = sclyservice!!.login(name, psw)
        return when (result) {
            "LOGIN_ERR" -> ResultData(false, result, "登录异常，请稍后再试")
            "PSW_ERR" -> ResultData(false, result, "密码错误")
            "USERNAME_ERR" -> ResultData(false, result, "用户名错误")
            else -> ResultData(true, result, "登录成功")
        }
    }

}