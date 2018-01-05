package com.titan.tserver.dao

import com.titan.tserver.model.Busi_uploadinfo
import org.springframework.data.repository.CrudRepository

interface HistoryInfoRepository:CrudRepository<Busi_uploadinfo,String>
