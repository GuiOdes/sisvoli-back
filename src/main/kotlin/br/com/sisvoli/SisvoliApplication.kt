package br.com.sisvoli

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
class SisvoliApplication

fun main(args: Array<String>) {
    runApplication<SisvoliApplication>(*args)
}
