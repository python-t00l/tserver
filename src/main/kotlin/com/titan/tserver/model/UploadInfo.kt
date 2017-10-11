package com.titan.tserver.model

/**
 * 信息上报
 *
{
"username": "",
"phone": "",
"eventType": "",
"eventSubType": "",
"longitude": 117.22856,
"latitude": 31.79534,
"info": "",
"marke": "",
"picArray": ["",""],
}
 */
class UploadInfo {
    //主键
    var id: String? = null
    //用户名
    var username: String? = null
    //联系方式
    var phone: String? = null
    //事件类型
    var eventtype:Int? = null
    //子事件
    var eventsubtype:Int? = null
    //经度
    var lon: Double? = null
    //纬度
    var lat: Double? = null
    //描述信息
    var info: String? = null
    //地址详情
    var address: String? = null
    //备注
    var remark: String? = null
    //照片
    var picArray: String? = null

    override fun toString(): String {
        return "UploadInfo(id=$id, username=$username, phone=$phone, eventtype=$eventtype, eventsubtype=$eventsubtype, lon=$lon, lat=$lat, info=$info, address=$address, remark=$remark, picArray=$picArray)"
    }

}
