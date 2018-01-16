package com.titan.tserver.model

/**
 * 图片信息上报
 */
data  class ImageInfo (val uploadinfoid: String?,val path: String,val type: Int,val title:String,var remark: String)
    //主键
    /*var id: String? = null
    //上报id
    var uploadinfoid: String? = null
    //存储路径
    var path: String? = null
    //附件种类（1-图片，2-声音，3-视频）
    var type:Int? = null
    //文件名
    var title:String? = null
    //备注
    var remark: String? = null

    override fun toString(): String {
        return "UpPicInfo(id=$id, uploadinfoid=$uploadinfoid, path=$path, type=$type, title=$title, remark=$remark)"
    }
*/
