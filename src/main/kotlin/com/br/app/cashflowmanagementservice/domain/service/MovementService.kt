package com.br.app.cashflowmanagementservice.domain.service

import com.br.app.cashflowmanagementservice.domain.entities.Movement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.util.*

interface MovementService {
    fun createMovement(movements: List<Movement>)
    fun getMovements(date: LocalDate, page: Int, size: Int): List<Movement>
    fun findById(id: String): Optional<Movement>
    fun updateMovement(id: String, updatedMovement: Movement): Movement?
    fun deleteMovement(id: String)
}