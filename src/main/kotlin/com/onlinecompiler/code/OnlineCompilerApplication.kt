package com.onlinecompiler.code

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OnlineCompilerApplication

fun main(args: Array<String>) {
	runApplication<OnlineCompilerApplication>(*args)
}
