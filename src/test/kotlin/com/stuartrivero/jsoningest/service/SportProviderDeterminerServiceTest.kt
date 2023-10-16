package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.SportProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SportProviderDeterminerServiceTest {

    @Test
    fun `key found`(){
        assertEquals(SportProvider.PROV1, SportProviderDeterminerService().sportProviderFromApiKey("apiKey1".apiKey()))
    }

    @Test
    fun `key not found`(){
        assertNull(SportProviderDeterminerService().sportProviderFromApiKey("unknownKey1".apiKey()))
    }
}

