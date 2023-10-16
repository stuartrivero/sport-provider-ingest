package com.stuartrivero.jsoningest.repository

import com.stuartrivero.jsoningest.model.SportProvider
import com.stuartrivero.jsoningest.model.TournamentId
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class GolfTournamentEntity(
    @Id @GeneratedValue var id: Long? = null,
    var externalTournamentId: TournamentId,
    var tournamentName: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var golfCourse: String,
    var hostCountryCode: String,
    var numRounds: Int,
    var dataSource: SportProvider
)