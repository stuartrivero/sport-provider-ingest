package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import java.time.LocalDate

val golfTournament = GolfTournament(
    externalTournamentId = "1",
    tournamentName = "aName",
    startDate = LocalDate.EPOCH.plusDays(10),
    endDate = LocalDate.EPOCH.plusDays(20),
    golfCourse = "aCourse",
    hostCountryCode = "US",
    numRounds = 2,
    dataSource = SportProvider.PROV1
)