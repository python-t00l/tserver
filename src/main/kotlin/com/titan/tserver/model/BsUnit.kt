package com.titan.tserver.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class BsUnit {

    @Id
    @GeneratedValue
    var unitid: String? = null
    var unitname: String? = null
//    var unittelno: String? = null
//    var unitfaxno: String? = null
//    var unitpostno: String? = null
//    var unitaddress: String? = null
//    var uniturl: String? = null
//    var unitemail: String? = null
    var dqxxid: String = ""
//    var parentid: String? = null
//    var sortid: String? = null
//    var unitlevel: String? = null

}
