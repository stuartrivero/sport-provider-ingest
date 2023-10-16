package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.SportProvider
import org.springframework.stereotype.Service

@Service
class SportProviderDeterminerService {

    fun sportProviderFromApiKey(key: ApiKey): SportProvider? =
        SportProvider.values().firstOrNull { it.apiKey == key }
}

data class ApiKey(val value: String)

fun String.apiKey() = ApiKey(this)