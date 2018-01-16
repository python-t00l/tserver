//package com.titan.tserver.web
//
//import com.titan.tserver.model.ResultData
//import com.titan.tserver.service.SlfhService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//
//
//@RestController//默认以json返回数据
//@RequestMapping(value = "gyslfh/api")
//class SlfhController {
//
//    //val counter = AtomicLong()
//
//
//    @Autowired
//    private val slfhService: SlfhService? = null
//
//    @RequestMapping("/")
//    fun home(): String {
//        return "Hello World!"
//    }
//
//
//    @GetMapping("/login")
//    fun login(@RequestParam username: String,@RequestParam password:String,@RequestParam clientid:String): ResultData {
//        try {
//            //whs:ZO4b7R2S4SK9yH4JmdDd+w==
//            //val newpsd=EncryptUtil.EncoderByMd5("whs")
//            val loginData=slfhService!!.login(username,password,clientid)
//            if(loginData!=null){
//                return ResultData(true, loginData, "登陆成功")
//
//            }else{
//                return ResultData(false, null, "登陆失败:用户名或密码错误")
//            }
//        }catch (e: Exception){
//            return ResultData(false, null, "登陆异常"+e)
//        }
//    }
//
//    //@GetMapping("/getAlarmInfo")
//    /*fun getAlarmInfo(@RequestParam dqid:Int): ResultData{
//        try {
//           // val alarminfos=slfhService!!.getAlarmInfos()
//            return ResultData(true,alarminfos,"获取成功")
//        }catch (e:Exception){
//            return ResultData(false,null,"获取信息异常"+e)
//        }
//    }*/
//
//}