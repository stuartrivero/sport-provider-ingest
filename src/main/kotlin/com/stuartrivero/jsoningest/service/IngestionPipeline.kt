package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import com.stuartrivero.jsoningest.repository.GolfTournamentEntity
import com.stuartrivero.jsoningest.repository.GolfTournamentRepository
import org.springframework.stereotype.Service

@Service
class IngestionPipeline(private val jsonToGolfTournament: JsonToGolfTournament,
                        private val golfRepository: GolfTournamentRepository) {

    fun ingest(provider: SportProvider, data: String){
        val golfTournament = jsonToGolfTournament.transform(sportProvider = provider, data = data)

        golfRepository.save(golfTournament.toEntity())
    }
}

fun GolfTournament.toEntity(): GolfTournamentEntity =
    GolfTournamentEntity(
        id = null,
        externalTournamentId = this.externalTournamentId,
        tournamentName = this.tournamentName,
        startDate = this.startDate,
        endDate = this.endDate,
        golfCourse = this.golfCourse,
        hostCountryCode = this.hostCountryCode,
        numRounds = this.numRounds,
        dataSource = this.dataSource
    )

