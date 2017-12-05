package com.titan.tserver.web

import com.titan.tserver.model.ResultData
import com.titan.tserver.model.UploadInfo
import com.titan.tserver.service.SclyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest


/**
 * 四川林业公众平台服务
 */
@RestController//默认以json返回数据
@RequestMapping(value = "scly/api")
class SclyController {

    //val counter = AtomicLong()


    @Autowired
    private val service: SclyService? = null

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
            val UploadPath: String=request.contextPath+"UploadFiles/"
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



}