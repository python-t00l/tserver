package com.titan.tserver.model

import javax.persistence.*

/**
 * 上报信息
 */
@Entity
class Busi_uploadinfo {
    @SequenceGenerator(name = "SEQ_UPINFO",sequenceName = "SEQ_UPINFO",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_UPINFO")
    @Id
    //主键ID
    var id: Long? = null
    //事件类型
    var eventtype: String? = null
    //事件子类型
    var eventsubtype: String? = null
    //纬度
    var lat: String? = null
    //经度
    var lon: String? = null
    //描述信息
    var info: String? = null
    //上传类型
    var uploadtype: String? = null
    //用户id
    var userid: String? = null
    //用户名
    var username: String? = null
    //电话
    var phone: String? = null
    //备注
    var remark: String? = null
    //备用字段1
    var remark1: String? = null
    //备用字段2
    var remark2: String? = null
    //地址
    var address: String? = null
}
