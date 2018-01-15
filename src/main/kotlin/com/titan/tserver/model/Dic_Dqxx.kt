package com.titan.tserver.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Dic_Dqxx {
    @Id
    var id: String? = null
    var name: String? = null
    var djh: String = ""

}
