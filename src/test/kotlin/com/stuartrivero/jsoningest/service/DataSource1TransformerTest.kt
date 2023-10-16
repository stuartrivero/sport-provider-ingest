package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate


class DataSource1TransformerTest {


    @Test
    fun `convert fails if json not complete`() {
        val json = """{
            "tournamentId": "174638",
            "tournamentName": "Women's Open Championship",
            "forecast": "fair",
            "courseName": "Sunnydale Golf Course",
            "countryCode": "GB",
            "startDate": "09/07/21",
            "endDate": "13/07/21",
            "roundCount___WRONGLY_NAMED": "4"
        }"""

        assertThrows<IllegalArgumentException> { DataSource1Transformer().transform(json) }
    }

    @Test
    fun `golf tournament created`() {

        val json = """{
            "tournamentId": "174638",
            "tournamentName": "Women's Open Championship",
            "forecast": "fair",
            "courseName": "Sunnydale Golf Course",
            "countryCode": "GB",
            "startDate": "09/07/21",
            "endDate": "13/07/21",
            "roundCount": "4"
        }"""

        assertEquals(
            GolfTournament(
                externalTournamentId = "174638",
                tournamentName = "Women's Open Championship",
                startDate = LocalDate.of(2021, 7, 9),
                endDate = LocalDate.of(2021, 7, 13),
                golfCourse = "Sunnydale Golf Course",
                hostCountryCode = "GB",
                numRounds = 4,
                dataSource = SportProvider.PROV1
            ), DataSource1Transformer().transform(json))

    }
}