package com.stuartrivero.jsoningest.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface  GolfTournamentRepository : JpaRepository<GolfTournamentEntity, Long>