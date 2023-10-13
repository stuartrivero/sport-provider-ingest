package com.stuartrivero.jsoningest

import com.stuartrivero.jsoningest.service.SportProvider
import com.stuartrivero.jsoningest.service.SportProviderService
import com.stuartrivero.jsoningest.service.apiKey
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SportProviderServiceTest {

    @Test
    fun `key found`(){
        assertEquals(SportProvider.PROV1, SportProviderService().sportProviderFromApiKey("apiKey1".apiKey()))
    }

    @Test
    fun `key not found`(){
        assertNull(SportProviderService().sportProviderFromApiKey("unknownKey1".apiKey()))
    }
}

