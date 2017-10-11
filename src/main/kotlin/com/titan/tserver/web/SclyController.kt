package com.titan.tserver.web

import com.titan.tserver.model.ResultData
import com.titan.tserver.model.UploadInfo
import com.titan.tserver.service.SclyServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import sun.rmi.runtime.Log

/**
 * 四川林业公众平台服务
 */
@RestController//默认以json返回数据
@RequestMapping(value = "scly/api")
class SclyController{

    //val counter = AtomicLong()


    @Autowired
    private val service: SclyServiceImpl? = null

    @RequestMapping("/")
    fun home(): String {
        return "Hello World!"
    }

    /**
     * 信息上报
     */
    @PostMapping("/uploadinfo")
    fun uploadinfo(@RequestBody uploadinfo: UploadInfo): ResultData {
        try {
            //whs:ZO4b7R2S4SK9yH4JmdDd+w==
            //val newpsd=EncryptUtil.EncoderByMd5("whs")
            val result=service!!.uploadinfo("")
            System.out.print("uploadinfo:"+uploadinfo.toString())
            if(result){
                return ResultData(true, result, "上报成功"+uploadinfo.toString())
            }else{
                return ResultData(false, result, "上报失败")
            }
        }catch (e: Exception){
            return ResultData(false, null, "上报异常"+e)
        }
    }


}