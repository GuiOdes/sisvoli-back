package br.com.sisvoli

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SisvoliApplication

fun main(args: Array<String>) {
	runApplication<SisvoliApplication>(*args)
}
