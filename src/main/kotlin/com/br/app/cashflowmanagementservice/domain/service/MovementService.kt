package com.br.app.cashflowmanagementservice.domain.service

import com.br.app.cashflowmanagementservice.domain.entities.Movement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface MovementService {
    fun createMovement(movements: List<Movement>)
    fun getMovements(pageable: Pageable): Page<Movement>?
    fun findById(id: String): Optional<Movement>
    fun updateMovement(id: String, updatedMovement: Movement): Movement?
    fun deleteMovement(id: String)
}