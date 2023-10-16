package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.SportProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class IngestionPipelineTest {

    @Test
    fun `data is transformed`(){

        val jsonToGolfTournament = mockk<JsonToGolfTournament>()
        val data = """{}"""

        every { jsonToGolfTournament.transform(SportProvider.PROV1, data) } returns golfTournament

        IngestionPipeline(jsonToGolfTournament).ingest(SportProvider.PROV1, data)

        verify { jsonToGolfTournament.transform(sportProvider = SportProvider.PROV1, data) }
    }
}
