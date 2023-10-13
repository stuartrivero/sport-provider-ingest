package com.stuartrivero.jsoningest.service

import org.springframework.stereotype.Service

@Service
class SportProviderService {

    fun sportProviderFromApiKey(key: ApiKey): SportProvider? =
        SportProvider.values().firstOrNull { it.apiKey == key }
}

enum class SportProvider(val apiKey: ApiKey) {
    PROV1("apiKey1".apiKey()), PROV2("apiKey2".apiKey())
}

data class ApiKey(val value: String)

fun String.apiKey() = ApiKey(this)