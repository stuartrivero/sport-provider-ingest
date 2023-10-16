package com.stuartrivero.jsoningest.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import java.time.Instant
import java.time.ZoneOffset.UTC

class DataSource2Transformer {

    fun transform(data: String): GolfTournament {

        try {
            val mapper = jacksonObjectMapper()
            val (tournamentUUID, golfCourse, competitionName, hostCountry, epochStart, epochFinish, rounds, playerCount) = mapper.readValue<DS2GolfTournament>(
                data
            )
            return GolfTournament(
                tournamentUUID,
                competitionName,
                Instant.ofEpochSecond(epochStart).atZone(UTC).toLocalDate(),
                Instant.ofEpochSecond(epochFinish).atZone(UTC).toLocalDate(),
                golfCourse,
                hostCountryAsIsoCode(hostCountry),
                rounds,
                SportProvider.PROV2
            )
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }
    }
}

fun hostCountryAsIsoCode(hostCountry: String): String =
    when (hostCountry) {
        "United States Of America" -> "US"
        else -> "GB"
    }


data class DS2GolfTournament(
    val tournamentUUID: String,
    val golfCourse: String,
    val competitionName: String,
    val hostCountry: String,
    val epochStart: Long,
    val epochFinish: Long,
    val rounds: Int,
    val playerCount: String,
)