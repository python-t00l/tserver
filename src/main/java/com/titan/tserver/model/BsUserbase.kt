package com.titan.tserver.model


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class BsUserbase {

    var usercode: String? = null
    var password: String? = null
//    var realname: String? = null
//    var telno: String? = null
//    var mobilephoneno: String? = null
//    var useremail: String? = null
//    var usersex: String? = null
//    var userold: String? = null
//    var userbirth: java.sql.Date? = null
//    var user_S: String? = null
//    var usermz: String? = null
//    var userhy: String? = null
//    var userzzmm: String? = null
//    var usergrpj: String? = null
//    var user_City: String? = null
//    var user_Jd: String? = null
//    var isactive: String? = null
//    var systemids: String? = null
    @Id
    @GeneratedValue
    var id: String? = null
//    var systemtype: String? = null
//    var user_Image: String? = null
//    var bz: String? = null
//    var iszj: String? = null
//    var lastlogin: java.sql.Date? = null
//    var thislogin: java.sql.Date? = null
//    var logintimes: String? = null
//    var skinname: String? = null
//    var px: String? = null
    var unitid: String = ""
//    var unitname: String? = null
//    var datashare: String? = null
//    var userlevel: String? = null
//    var touxiang: String? = null
//    var user_Address: String? = null
//    var unitset: String? = null
//    var xsw: String? = null

}
