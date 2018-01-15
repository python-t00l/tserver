package com.titan.tserver.model

/**
 * 信息上报
 */
class UploadInfo {
    //主键
    var id: String? = null
    //用户名
    var username: String? = null
    //联系方式
    var phone: String? = null
    //事件类型
    var eventtype:String? = null
    //子事件
    var eventsubtype:String? = null
    //经度
    var lon: Double? = null
    //纬度
    var lat: Double? = null
    //描述信息
    var info: String? = null
    //地址详情
    var address: String? = null
    //备注
    var remark: String = ""
    //照片
    var picArray: Array<String>? = null
    //地区名称
    lateinit var district:String

    override fun toString(): String {
        return "UploadInfo(id=$id, username=$username, phone=$phone, eventtype=$eventtype, eventsubtype=$eventsubtype, lon=$lon, lat=$lat, info=$info, address=$address, remark=$remark, district=$district)"
    }

}
