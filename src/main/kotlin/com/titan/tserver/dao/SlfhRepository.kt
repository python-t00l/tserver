package com.titan.tserver.dao

import com.titan.tserver.model.LoginResult
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
class SlfhRepository {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    /**
     * 连接查询用户信息
     */

    //    //select t.DQID as dqid,t.CLIENTID as clientid,t.ID as userid ,y.DQLEVEL AS dqlevel from BUSI_USER t  JOIN  DIC_DQXX y  ON  t.DQID=y.ID where t.USERNAME = ？ AND  t.PASSWORD=？

    fun login(username: String,password: String): LoginResult {
        val dd=entityManager!!.createNativeQuery("select t.dqid as dqid,t.clientid as clientid,t.id as userid  from Busi_User t where t.username = '$username' AND  t.password = '$password'")
        return  entityManager!!.createNativeQuery("select t.dqid as dqid,t.clientid as clientid,t.id as userid,y.DQLEVEL as dqlevel  from Busi_User t JOIN  DIC_DQXX y  ON  t.dqid=y.ID     where t.username = '$username' AND  t.password = '$password'").singleResult as LoginResult
    }
}