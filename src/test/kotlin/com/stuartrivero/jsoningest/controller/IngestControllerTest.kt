package com.stuartrivero.jsoningest.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class IngestControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `Known providers' data can be ingested `() {

        mockMvc.perform(
            post("/ingest/golf")
                .header("X-API-KEY", "aKnownProvider")
                .content(sampleJson1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isAccepted())
    }

    @Test
    fun `Bad request when client is unknown`() {

        mockMvc.perform(
            post("/ingest/golf")
                .header("X-API-KEY", "unKnownProvider")
                .content(sampleJson1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `Bad request when client is unidentified`() {

        mockMvc.perform(
            post("/ingest/golf")
                .content(sampleJson1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }
}

val sampleJson1 = """{
    "tournamentId": "174638",
    "tournamentName": "Women's Open Championship",
    "forecast": "fair",
    "courseName": "Sunnydale Golf Course",
    "countryCode": "GB",
    "startDate": "09/07/21",
    "endDate": "13/07/21",
    "roundCount": "4"
}""".trimMargin()