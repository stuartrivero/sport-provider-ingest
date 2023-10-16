package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.config.ProviderConfig
import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class JsonToGolfTournamentTest{

    @Test
    fun `transform using provider`(){
        val config = mockk<ProviderConfig>()
        val dataSourceTransformer = mockk<DataSourceTransformer>()
        every { config.transformerFor(SportProvider.PROV1) } returns dataSourceTransformer
        val golfTournament = GolfTournament(
            "1",
            "aName",
            LocalDate.EPOCH.plusDays(10),
            LocalDate.EPOCH.plusDays(20),
            "aCourse",
            "US",
            2,
            SportProvider.PROV1
        )
        every { dataSourceTransformer.transform("""{}""") } returns golfTournament
        assertEquals(golfTournament,
            JsonToGolfTournament(config).transform(SportProvider.PROV1, """{}"""))

        verify {  dataSourceTransformer.transform("""{}""") }

    }
}