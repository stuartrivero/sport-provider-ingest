package com.stuartrivero.jsoningest.service

import org.springframework.stereotype.Service

@Service
class IngestionPipeline(val jsonToGolfTournament: JsonToGolfTournament) {

    fun ingest(data: String){

    }
}