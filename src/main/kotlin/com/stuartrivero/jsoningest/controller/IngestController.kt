package com.stuartrivero.jsoningest.controller

import com.stuartrivero.jsoningest.service.ApiKey
import com.stuartrivero.jsoningest.service.IngestionPipeline
import com.stuartrivero.jsoningest.service.SportProviderDeterminerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ingest")
class IngestController (private val sportProviderDeterminerService: SportProviderDeterminerService, val ingestionPipeline: IngestionPipeline){

    @PostMapping
    @RequestMapping("/golf")
    fun add(@RequestBody data: String, @RequestHeader("X-API-KEY") apiKey: ApiKey): ResponseEntity<String> {
        val sportProvider = sportProviderDeterminerService.sportProviderFromApiKey(apiKey)
            ?: return ResponseEntity(HttpStatus.BAD_REQUEST)

        ingestionPipeline.ingest(sportProvider, data)
        return ResponseEntity("", HttpStatus.ACCEPTED)
    }

}