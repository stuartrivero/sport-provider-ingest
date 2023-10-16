package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.SportProvider
import org.springframework.stereotype.Service

@Service
class IngestionPipeline(val jsonToGolfTournament: JsonToGolfTournament) {

    fun ingest(provider: SportProvider, data: String){
        jsonToGolfTournament.transform(sportProvider = provider, data = data)
    }
}