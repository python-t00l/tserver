package com.titan.tserver.web

import com.titan.server.model.Greeting
import com.titan.server.model.ResultData
import com.titan.server.model.User
import com.titan.tserver.service.SlfhService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong


@RestController//默认以json返回数据
@RequestMapping(value = "gyslfh/api")
class SlfhController {

    val counter = AtomicLong()


    @Autowired
    private val slfhService: SlfhService? = null

    @RequestMapping("/")
    fun home(): String {
        return "Hello World!"
    }


    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")





    @GetMapping("/login")
    fun login(@RequestParam username: String,@RequestParam password:String,@RequestParam clientid:String): ResultData {
        try {
            //whs:ZO4b7R2S4SK9yH4JmdDd+w==
            //val newpsd=EncryptUtil.EncoderByMd5("whs")
            val loginData=slfhService!!.login(username,password,clientid)

            return ResultData(true, loginData, "登陆成功")
        }catch (e: Exception){
           return ResultData(false, null, "登陆异常")
        }
    }

}