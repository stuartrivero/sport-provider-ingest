package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.SportProvider
import com.stuartrivero.jsoningest.repository.GolfTournamentEntity
import com.stuartrivero.jsoningest.repository.GolfTournamentRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IngestionPipelineTest {

    @Test
    fun `data is not saved if it is not successfully transformed`() {

        val jsonToGolfTournament = mockk<JsonToGolfTournament>()
        val golfRepository = mockk<GolfTournamentRepository>()
        val data = """{}"""

        every {
            jsonToGolfTournament.transform(
                SportProvider.PROV1,
                data
            )
        } throws IllegalArgumentException("Cannot transform")

        assertThrows<IllegalArgumentException> {
            IngestionPipeline(jsonToGolfTournament, golfRepository).ingest(
                SportProvider.PROV1,
                data
            )
        }

        verify { jsonToGolfTournament.transform(sportProvider = SportProvider.PROV1, data) }
        verify { golfRepository wasNot Called }
    }

    @Test
    fun `data is transformed and saved`() {

        val jsonToGolfTournament = mockk<JsonToGolfTournament>()
        val golfRepository = mockk<GolfTournamentRepository>()
        val data = """{}"""

        val entitySlot = slot<GolfTournamentEntity>()

        every { jsonToGolfTournament.transform(SportProvider.PROV1, data) } returns golfTournament
        every { golfRepository.save(capture(entitySlot)) } returns golfTournament.toEntity()

        IngestionPipeline(jsonToGolfTournament, golfRepository).ingest(SportProvider.PROV1, data)

        val captured = entitySlot.captured
        assertEquals(golfTournament.externalTournamentId, captured.externalTournamentId)
        assertEquals(golfTournament.tournamentName, captured.tournamentName)
        assertEquals(golfTournament.startDate, captured.startDate)
        assertEquals(golfTournament.endDate, captured.endDate)
        assertEquals(golfTournament.golfCourse, captured.golfCourse)
        assertEquals(golfTournament.hostCountryCode, captured.hostCountryCode)
        assertEquals(golfTournament.numRounds, captured.numRounds)
        assertEquals(golfTournament.dataSource, captured.dataSource)
        verify { jsonToGolfTournament.transform(sportProvider = SportProvider.PROV1, data) }
        verify { golfRepository.save(any()) }

    }
}
