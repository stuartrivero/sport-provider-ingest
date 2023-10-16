package com.stuartrivero.jsoningest.model

import java.time.LocalDate

typealias TournamentId = String

data class GolfTournament(
    val externalTournamentId: TournamentId,
    val tournamentName: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val golfCourse: String,
    val hostCountryCode: String,
    val numRounds: Int,
    val dataSource: SportProvider
)