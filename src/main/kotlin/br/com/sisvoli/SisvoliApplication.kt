package br.com.sisvoli

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SisvoliApplication

fun main(args: Array<String>) {
    runApplication<SisvoliApplication>(*args)
}
