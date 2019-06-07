package br.com.leonardoferreira.future

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FutureApplication

fun main(args: Array<String>) {
	runApplication<FutureApplication>(*args)
}
