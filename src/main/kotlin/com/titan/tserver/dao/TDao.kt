package com.titan.tserver.dao

import com.titan.server.model.Busi_User
import com.titan.server.model.Dic_Dqxx


import com.titan.server.model.LoginResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository


interface TDao:Repository<Busi_User,Long> {



    //fun findByUsernameAndPassword(USERNAME: String, PASSWORD: String): Busi_User
    /**
     * 连接查询用户信息
     */
    //val dqid: String,val clientid: String,val userid: String
    //select t.DQID as dqid,t.CLIENTID as clientid,t.ID as userid ,y.DQLEVEL AS dqlevel from BUSI_USER t  JOIN  DIC_DQXX y  ON  t.DQID=y.ID where t.USERNAME = ？ AND  t.PASSWORD=？
    //@Query("select t.dqid as dqid,t.clientid as clientid,t.id as userid,y.dqlevel as dqlevel  from Busi_User t left outer JOIN  Dic_Dqxx y  ON  t.dqid=y.id where    t.username = ?1 AND  t.password = ?2")
    @Query("select t.dqid as dqid,t.clientid as clientid,t.id as userid ,y.dqlevel as dqlevel from Busi_User t, Dic_Dqxx y where t.dqid=y.id And  t.username = ?1 AND  t.password = ?2")
    fun login(username: String, password: String): LoginResult

    /*D
     * 更新用户ID(推送)
     */
    @Modifying
    @Query("UPDATE Busi_User  t SET t.clientid=?1 WHERE t.username=?2 AND t.password=?3")
    fun saveClientid(clientid:String,username: String, password: String): Int

}


