package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import java.time.LocalDate

val golfTournament = GolfTournament(
    "1",
    "aName",
    LocalDate.EPOCH.plusDays(10),
    LocalDate.EPOCH.plusDays(20),
    "aCourse",
    "US",
    2,
    SportProvider.PROV1
)