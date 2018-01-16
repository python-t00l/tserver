package com.titan.tserver.Configurer

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import javax.sql.DataSource

/**
 * 数据源配置
 */
@Configuration
class DataSourceConfigurer {
    @Bean
    fun dataSource(environment: Environment): DataSource {
        return DruidDataSourceBuilder.create()
                .build(environment, "spring.datasource.druid.")
    }
}