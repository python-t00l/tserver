package com.titan.tserver.service

import com.titan.tserver.dao.TDao
import com.titan.tserver.model.AlarmInfo
import com.titan.tserver.model.LoginResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SlfhServiceImpl: SlfhService {
    override fun getAlarmInfos(dqid: String): AlarmInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Autowired
    private val repository: TDao? = null


    override fun login(username: String, password: String, clendid: String): LoginResult? {
        //return repository!!.login(username,password)
//        repository!!.saveClientid(clendid,username,password)
//        return repository!!.login(username,password)
        //val dd=repository!!.saveClientid(clendid,username,password)
        /*if(repository!!.saveClientid(clendid,username,password)>0){
                return repository.login(username,password)
        }else{
            return null
        }*/
        return null

    }
}