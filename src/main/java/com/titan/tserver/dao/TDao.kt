package com.titan.tserver.dao


import com.titan.tserver.model.Busi_User
import org.springframework.data.repository.Repository


interface TDao:Repository<Busi_User,Long> {




    /**
     * 连接查询用户信息
     */
//    @Query("select t.dqid as dqid,t.clientid as clientid,t.id as userid ,y.dqlevel as dqlevel from Busi_User t, Dic_Dqxx y where t.dqid=y.id And  t.username = ?1 AND  t.password = ?2")
//    fun login(username: String, password: String): LoginResult

    /**
     * 更新用户ID(推送)
     */
//    @Transactional
//    @Modifying
//    @Query("UPDATE Busi_User  t SET t.clientid=?1 WHERE t.username=?2 AND t.password=?3")
//    fun saveClientid(clientid:String,username: String, password: String): Int

}


