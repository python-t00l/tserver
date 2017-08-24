package com.titan.tserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TserverApplication

fun main(args: Array<String>) {
    SpringApplication.run(TserverApplication::class.java, *args)
}
