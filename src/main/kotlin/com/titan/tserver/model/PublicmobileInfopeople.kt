package com.titan.tserver.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class PublicmobileInfopeople {

    @Id
    @GeneratedValue
    var areacode: String = ""
    var infopeople: String? = null
}
