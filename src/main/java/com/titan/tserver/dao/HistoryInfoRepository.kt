package com.titan.tserver.dao

import com.titan.tserver.model.*
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface HistoryInfoRepository:PagingAndSortingRepository<Busi_uploadinfo,Long>

interface DqxxRepository:CrudRepository<Dic_Dqxx,Long>{
    fun findByName(name:String):Dic_Dqxx
}

interface UserInfoRepository :CrudRepository<BsUserbase,Long> {
    fun findByusercode(code:String):BsUserbase?
}
interface DqxxidRepository:CrudRepository<BsUnit,Long>{
    fun findByUnitid(unitid:String):BsUnit
}

