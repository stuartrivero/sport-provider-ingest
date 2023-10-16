package com.stuartrivero.jsoningest.service

import com.stuartrivero.jsoningest.model.GolfTournament

interface DataSourceTransformer {
    fun transform(data: String): GolfTournament
}