package com.titan.server.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Dic_Dqxx {
    @Id
    var id: String? = null
    var name: String? = null
    var parentid: String? = null
    var island: String? = null
    var effectivetime: java.sql.Date? = null
    var failuretime: java.sql.Date? = null
    var historyid: String? = null
    var remark: String? = null
    var dqlevel: String? = null
    var valueid: String? = null
    var allname: String? = null

}
