package com.titan.tserver.dao

import com.titan.tserver.model.Busi_uploadinfo
import org.springframework.data.repository.CrudRepository

interface UploadRepository : CrudRepository<Busi_uploadinfo, Long> {

    /**
     * 保存数据
     */
//    @Transactional
//    @Modifying
//    @Query("insert Busi_uploadinfo  t SET t.clientid=?1 WHERE t.username=?2 AND t.password=?3")
//    fun save(clientid:String,username: String, password: String): Int

}