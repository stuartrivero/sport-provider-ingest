package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate


class DataSource2TransformerTest {

    @Test
    fun `convert fails if json not complete`() {
        val json = """{
                        "tournamentUUID":"6cec8a4f-83ce-4114-804e-9ad458495af7",
                        "golfCourse":"Duckies Meadows",
                        "competitionName":"US Palm Tree Championship",
                        "hostCountry":"United States Of America",
                        "epochStart":"1700732326",
                        "epochFinish":"1700905126",
                        "rounds":"2",
                        "playerCount_WRONGLY_NAMED":"35"
                    }""".trimMargin()

        assertThrows<IllegalArgumentException> { DataSource2Transformer().transform(json) }
    }

    @Test
    fun `golf tournament created`() {

        val json = """{
                        "tournamentUUID":"6cec8a4f-83ce-4114-804e-9ad458495af7",
                        "golfCourse":"Duckies Meadows",
                        "competitionName":"US Palm Tree Championship",
                        "hostCountry":"United States Of America",
                        "epochStart":"1700732326",
                        "epochFinish":"1700905126",
                        "rounds":"2",
                        "playerCount":"35"
                    }""".trimMargin()

        assertEquals(
            GolfTournament(
                externalTournamentId = "6cec8a4f-83ce-4114-804e-9ad458495af7",
                tournamentName = "US Palm Tree Championship",
                startDate = LocalDate.of(2023, 11, 23),
                endDate = LocalDate.of(2023, 11, 25),
                golfCourse = "Duckies Meadows",
                hostCountryCode = "US",
                numRounds = 2,
                dataSource = SportProvider.PROV2
            ), DataSource2Transformer().transform(json)
        )

    }
}