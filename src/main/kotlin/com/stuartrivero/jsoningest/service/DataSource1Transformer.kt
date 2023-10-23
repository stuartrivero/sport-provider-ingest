package com.stuartrivero.jsoningest.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DataSource1Transformer : DataSourceTransformer {

    override fun transform(data: String): GolfTournament {

        try {
            val mapper = jacksonObjectMapper()
            val (tournamentId, tournamentName, weather, course, countryCode, startDate, endDate, numRounds) = mapper.readValue<DS1GolfTournament>(
                data
            )
            val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
            return GolfTournament(
                tournamentId,
                tournamentName,
                LocalDate.parse(startDate, dateTimeFormatter),
                LocalDate.parse(endDate, dateTimeFormatter),
                course,
                countryCode,
                numRounds,
                SportProvider.PROV1
            )
        } catch (e: Exception){
            throw IllegalArgumentException(e)
        }
    }
}

data class DS1GolfTournament(
    val tournamentId: String,
    val tournamentName: String,
    val weather: String,
    val course: String,
    val countryCode: String,
    val startDate: String,
    val endDate: String,
    val numRounds: Int
)