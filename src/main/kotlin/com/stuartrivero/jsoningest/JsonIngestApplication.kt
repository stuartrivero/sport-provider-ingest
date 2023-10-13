package com.stuartrivero.jsoningest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JsonIngestApplication

fun main(args: Array<String>) {
    runApplication<JsonIngestApplication>(*args)
}
