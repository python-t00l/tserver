package com.titan.tserver.service

import com.titan.tserver.dao.TDao
import com.titan.tserver.model.AlarmInfo
import com.titan.tserver.model.LoginResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SclyServiceImpl {
    @Autowired
    private val repository: TDao? = null

    fun uploadinfo(uploadinfo: String): Boolean {
        return true;
    }




}