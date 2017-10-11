package com.titan.tserver.dao

import com.titan.tserver.model.AlarmInfo
import com.titan.tserver.model.Busi_Alarm_Receipt
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

/*
interface AlarmDao : CrudRepository<Busi_Alarm_Receipt, Long> {


   */
/* @Query("select h.city as city, h.name as name, avg(r.rating) as averageRating from Busi_Alarm_Receipt h left outer join h.reviews r where h.city = ?1 group by h")
    fun findByQuery(dqid: Int, pageable: Pageable): Page<AlarmInfo>*//*

}*/
