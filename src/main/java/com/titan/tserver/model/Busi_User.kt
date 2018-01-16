package com.titan.tserver.model

import javax.persistence.Entity
import javax.persistence.Id

/**
 * 用户表
 */
@Entity
class Busi_User {
    @Id
    var id: String? = null
    var username: String? = null
    var password: String? = null
    var realname: String? = null
    var phone: String? = null
    var mobilepheone: String? = null
    var address: String? = null
    var loginip: String? = null
    var email: String? = null
    var user_Image: String? = null
    var departmentid: String? = null
    var dqid: String? = null
    var birthday: java.sql.Date? = null
    var sex: String? = null
    var nation: String? = null
    var political_Status: String? = null
    var marital_Status: String? = null
    var remark: String? = null
    var login_Status: String? = null
    var registerip: String? = null
    var account_Status: String? = null
    var clientid: String? = null
    override fun toString(): String {
        return "Busi_User(id=$id, username=$username, password=$password, realname=$realname, phone=$phone, mobilepheone=$mobilepheone, address=$address, loginip=$loginip, email=$email, user_Image=$user_Image, departmentid=$departmentid, dqid=$dqid, birthday=$birthday, sex=$sex, nation=$nation, political_Status=$political_Status, marital_Status=$marital_Status, remark=$remark, login_Status=$login_Status, registerip=$registerip, account_Status=$account_Status, clientid=$clientid)"
    }

}
