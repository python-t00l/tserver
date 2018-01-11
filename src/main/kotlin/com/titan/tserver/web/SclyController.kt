package com.titan.tserver.web

import com.titan.tserver.model.ResultData
import com.titan.tserver.model.UploadInfo
import com.titan.tserver.service.SclyService
import com.titan.tserver.storage.StorageService
import com.titan.tserver.util.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMethod




/**
 * 四川林业公众平台服务
 */
@RestController//默认以json返回数据
@RequestMapping(value = "scly/api")
class SclyController {

    //val counter = AtomicLong()


    @Autowired
    private val service: SclyService? = null
    //文件存储
    @Autowired
    private val storageService: StorageService?=null

    @RequestMapping("/")
    fun home(): String {
        return "Hello World!"
    }

    /**
     * 信息上报
     * @param uploadinfo 上报信息
     */
    @PostMapping("/uploadinfo")
    fun uploadinfo(@RequestBody uploadinfo: UploadInfo,request: HttpServletRequest): ResultData {
        try {
            //whs:ZO4b7R2S4SK9yH4JmdDd+w==
            //val newpsd=EncryptUtil.EncoderByMd5("whs")
            val UploadPath: String=request.contextPath+"UploadFiles/images"
            //文字信息上报
            val result = service!!.uploadinfo(uploadinfo,UploadPath)
            //图片上报
            //val  request: HttpServletRequest =request
            //val pic_result = service.savePic(uploadinfo.picArray,UploadPath)
            System.out.print("uploadinfo:" + uploadinfo.toString())
            if (result ) {//
                return ResultData(true, result, "上报成功")
            } else if (!result) {
                return ResultData(false, result, "信息上报失败")
            } else {
                return ResultData(false, result, "图片上报失败")
            }
        } catch (e: Exception) {
            return ResultData(false, null, "上报异常" + e)
        }
    }

    @PostMapping("/gethistoryinfo")
    fun gethistoryinfo():ResultData{
        var result= service!!.getHisInfo()
        return ResultData(true,result,"成功")
    }

    //跳转到上传文件的页面
    @GetMapping("/gouploadimg")
    fun goUploadImg(): String {
        //跳转到 templates 目录下的 uploadimg.html
        return "/upload"
    }

    @RequestMapping("/upVideo")
    fun upVideo(@RequestParam("file") file:MultipartFile){
        // 判断文件是否为空
        if (!file.isEmpty) {
            try {
                file.transferTo(File("F:\\image_source"))
            } catch (e: Exception) {
                println("异常video:$e")
            }

        }
    }

    /**
     * 文件上传
     */
    @PostMapping("/uploadfile")
    fun uploadFile(@RequestParam("file") file: MultipartFile, request: HttpServletRequest): ResultData {

        val UploadPath = request.contextPath + "UploadFiles/images"
        val contentType = file.contentType
        val fileName = file.originalFilename
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
    fun uploadFiles(@RequestParam("file") files: Array<MultipartFile>) : ResultData {
        for(file in files){
            try {
                storageService!!.store(file)
            } catch (e: Exception) {
                // TODO: handle exception
                System.out.println("上传异常" + e)
                return ResultData(false, "文件上传", "上传异常" + e)

            }
        }
        return ResultData(true, "文件上传", "上报成功")

    }

}