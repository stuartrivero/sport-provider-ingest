package com.stuartrivero.jsoningest.controller

import com.stuartrivero.jsoningest.repository.GolfTournamentEntity
import com.stuartrivero.jsoningest.repository.GolfTournamentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/golf")
class GolfTournamentController (private val golfTournamentRepository: GolfTournamentRepository){

    @GetMapping
    @RequestMapping("/tournaments")
    fun getAllTournaments(): ResponseEntity<List<GolfTournamentEntity>> {
        val findAll = golfTournamentRepository.findAll()
        return ResponseEntity(findAll, HttpStatus.OK)
    }

}