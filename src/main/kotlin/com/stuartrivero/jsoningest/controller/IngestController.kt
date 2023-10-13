package com.stuartrivero.jsoningest.controller

import com.stuartrivero.jsoningest.service.ApiKey
import com.stuartrivero.jsoningest.service.SportProviderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ingest")
class IngestController (private val sportProviderService: SportProviderService){

    @PostMapping
    @RequestMapping("/golf")
    fun add(@RequestBody update: String, @RequestHeader("X-API-KEY") apiKey: ApiKey): ResponseEntity<String> {
        if(sportProviderService.sportProviderFromApiKey(apiKey) == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity("", HttpStatus.ACCEPTED)
    }

}