package com.titan.tserver.model

import javax.persistence.*

/**
 * 附件信息
 */
@Entity
class Busi_attachment {
    @SequenceGenerator(name = "SEQ_UPPICINFO",sequenceName = "SEQ_UPPICINFO",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_UPPICINFO")
    @Id
    //主键id
    var id: Long? = null
    //上报id
    var uploadinfoid: String? = null
    //存储地址
    var path: String? = null
    //文件类型 1-图片，2-声音，3-视频
    var type: Int? = null
    //文件名
    var title: String? = null
    //备注
    var remark: String? = null
}
