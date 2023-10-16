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
                        "tournamentUUID":"87fc6650-e114-4179-9aef-6a9a13030f80",
                        "golfCourse":"Happy Days Golf Club",
                        "competitionName":"South West Invitational",
                        "hostCountry":"United States Of America",
                        "epochStart":"1638349200",
                        "epochFinish":"1638468000",
                        "rounds":"2",
                        "playerCount_WRONGLY_NAMED":"35"
                    }""".trimMargin()

        assertThrows<IllegalArgumentException> { DataSource2Transformer().transform(json) }
    }

    @Test
    fun `golf tournament created`() {

        val json = """{
                        "tournamentUUID":"87fc6650-e114-4179-9aef-6a9a13030f80",
                        "golfCourse":"Happy Days Golf Club",
                        "competitionName":"South West Invitational",
                        "hostCountry":"United States Of America",
                        "epochStart":"1638349200",
                        "epochFinish":"1638468000",
                        "rounds":"2",
                        "playerCount":"35"
                    }""".trimMargin()

        assertEquals(
            GolfTournament(
                externalTournamentId = "87fc6650-e114-4179-9aef-6a9a13030f80",
                tournamentName = "South West Invitational",
                startDate = LocalDate.of(2021, 12, 1),
                endDate = LocalDate.of(2021, 12, 2),
                golfCourse = "Happy Days Golf Club",
                hostCountryCode = "US",
                numRounds = 2,
                dataSource = SportProvider.PROV2
            ), DataSource2Transformer().transform(json)
        )

    }
}