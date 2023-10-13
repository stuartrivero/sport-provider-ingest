package com.stuartrivero.jsoningest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ingest")
class IngestController {

    @PostMapping
    @RequestMapping("/golf")
    fun add(@RequestBody update: String, @RequestHeader("X-API-KEY") apiKey: String): ResponseEntity<String> {
        return ResponseEntity("", HttpStatus.ACCEPTED)
    }

}