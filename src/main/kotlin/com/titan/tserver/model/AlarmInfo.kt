package com.titan.tserver.model

/**
 * 火警信息
 */
interface AlarmInfo {


//    var id: String? = null
//    var dailyid: String? = null
//    var unionid: String? = null
//    var receipttime: java.sql.Date? = null
//    var originid: String? = null
//    var adress: String? = null
//    var lon: String? = null
//    var lat: String? = null
//    var policecase: String? = null
//    var policetime: java.sql.Date? = null
//    var reportcase: String? = null
//    var reporttime: java.sql.Date? = null
//    var userid: String? = null
//    var tel: String? = null
//    var dqname: String? = null
//    var dqid: String? = null
//    var iswork: String? = null
//    var remark: String? = null
//    var remark1: String? = null
//    var remark2: String? = null

    fun getId(): String

    fun getDailyid(): String

    fun getUnionid(): String

    fun getReceipttime(): String

    fun getOriginid(): String

    fun getAdress(): String

    fun getLon(): String

    fun getLat(): String

    fun getPolicecase(): String

    fun getPolicetime(): String

    fun getReportcase(): String

    fun getUserid(): String

    fun getTel(): String

    fun getDqid(): String

    fun getIswork(): String

    fun getRemark(): String

    fun getRemark1(): String

    fun getRemark2(): String


}