package com.titan.tserver.dao

import com.titan.tserver.model.Busi_attachment
import org.springframework.data.repository.CrudRepository

/**
 * 附件上报接口
 */
interface  AttachmentRepository : CrudRepository<Busi_attachment, Long>