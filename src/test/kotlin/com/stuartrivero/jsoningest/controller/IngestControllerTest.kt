package com.stuartrivero.jsoningest.controller

import com.ninjasquad.springmockk.MockkBean
import com.stuartrivero.jsoningest.model.SportProvider
import com.stuartrivero.jsoningest.repository.GolfTournamentRepository
import com.stuartrivero.jsoningest.service.IngestionPipeline
import com.stuartrivero.jsoningest.service.SportProviderDeterminerService
import com.stuartrivero.jsoningest.service.apiKey
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest

class IngestControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var sportProviderDeterminerService: SportProviderDeterminerService

    @MockkBean
    lateinit var ingestionPipeline: IngestionPipeline

    @MockkBean
    lateinit var golfTournamentRepository: GolfTournamentRepository

    @Test
    fun `Known providers' data can be ingested `() {
        every { sportProviderDeterminerService.sportProviderFromApiKey("aKnownProvider".apiKey()) } returns SportProvider.PROV1
        every { ingestionPipeline.ingest(any(), any()) } just Runs

        mockMvc.post("/ingest/golf") {
            contentType = MediaType.APPLICATION_JSON
            header("X-API-KEY", "aKnownProvider")
            content = (sampleJson1)
        }.andExpect {
            status { isAccepted() }
        }

        verify { ingestionPipeline.ingest(SportProvider.PROV1, sampleJson1) }
    }

    @Test
    fun `Bad request when client is unknown`() {
        every { sportProviderDeterminerService.sportProviderFromApiKey("unKnownProvider".apiKey()) } returns null

        mockMvc.post("/ingest/golf") {
            contentType = MediaType.APPLICATION_JSON
            header("X-API-KEY", "unKnownProvider")
            content = (sampleJson1)
        }.andExpect {
            status { isBadRequest() }
        }

        verify { ingestionPipeline wasNot Called }
    }

    @Test
    fun `Bad request when client is unidentified`() {

        mockMvc.post("/ingest/golf") {
            contentType = MediaType.APPLICATION_JSON
            content = (sampleJson1)
        }.andExpect {
            status { isBadRequest() }
        }

        verify { ingestionPipeline wasNot Called }
    }
}

val sampleJson1 = """{
    "tournamentId": "174638",
    "tournamentName": "Ladies Open",
    "weather": "fair",
    "course": "St Andreas",
    "countryCode": "GB",
    "startDate": "09/07/21",
    "endDate": "13/07/21",
    "numRounds": "4"
}""".trimMargin()