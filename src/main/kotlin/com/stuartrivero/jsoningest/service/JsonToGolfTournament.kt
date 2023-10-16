package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.config.ProviderConfig
import com.stuartrivero.jsoningest.model.GolfTournament
import com.stuartrivero.jsoningest.model.SportProvider

class JsonToGolfTournament(private val providerConfig: ProviderConfig){
    fun transform(sportProvider: SportProvider, data: String): GolfTournament =
        providerConfig.transformerFor(sportProvider).transform(data)

}