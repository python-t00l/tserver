package com.titan.tserver.service

import com.titan.tserver.dao.UploadRepository
import com.titan.tserver.model.Busi_uploadinfo
import com.titan.tserver.model.UploadInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SclyServiceImpl {
    @Autowired
    private val repository: UploadRepository? = null

    private val busi_uploadinfo: Busi_uploadinfo?=null

    fun uploadinfo(uploadinfo: UploadInfo): Boolean {
        try {
            busi_uploadinfo!!.address=uploadinfo.address
            busi_uploadinfo!!.eventsubtype= uploadinfo.eventsubtype.toString()
            busi_uploadinfo!!.eventtype=uploadinfo.eventtype.toString()
            busi_uploadinfo!!.id=uploadinfo.id
            busi_uploadinfo!!.info=uploadinfo.info
            busi_uploadinfo!!.lat=uploadinfo.lat.toString()
            busi_uploadinfo!!.lon=uploadinfo.lon.toString()
            busi_uploadinfo!!.phone=uploadinfo.phone
            busi_uploadinfo!!.remark=uploadinfo.remark
            busi_uploadinfo!!.username=uploadinfo.username
            repository!!.save(busi_uploadinfo)
            println()
            return true
        }catch (e:Exception){
            return false
        }
    }




}