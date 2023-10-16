package com.stuartrivero.jsoningest.controller

import com.ninjasquad.springmockk.MockkBean
import com.stuartrivero.jsoningest.repository.GolfTournamentRepository
import com.stuartrivero.jsoningest.service.IngestionPipeline
import com.stuartrivero.jsoningest.service.SportProviderDeterminerService
import com.stuartrivero.jsoningest.service.golfTournament
import com.stuartrivero.jsoningest.service.toEntity
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class GolfTournamentControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var golfTournamentRepository: GolfTournamentRepository

    @MockkBean
    lateinit var sportProviderDeterminerService: SportProviderDeterminerService

    @MockkBean
    lateinit var ingestionPipeline: IngestionPipeline

    @Test
    fun `can retrieve all golf tournaments`() {

        every {  golfTournamentRepository.findAll() } returns
            listOf(
                golfTournament.toEntity()
            )

        this.mockMvc.perform(MockMvcRequestBuilders.get("/golf/tournaments"))
            .andExpect(status().isOk())
            .andExpect(
                content().string(
                    """
                        [{"id":null,"externalTournamentId":"1","tournamentName":"aName","startDate":"1970-01-11","endDate":"1970-01-21","golfCourse":"aCourse","hostCountryCode":"US","numRounds":2,"dataSource":"PROV1"}]
                        """.trimIndent()
                )
            )
    }
}