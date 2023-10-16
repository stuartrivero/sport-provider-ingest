package com.stuartrivero.jsoningest.model

import com.stuartrivero.jsoningest.service.ApiKey
import com.stuartrivero.jsoningest.service.apiKey

enum class SportProvider(val apiKey: ApiKey) {
    PROV1("apiKey1".apiKey()), PROV2("apiKey2".apiKey())
}