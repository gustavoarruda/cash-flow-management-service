package com.br.app.cashflowmanagementservice.domain.service

import com.br.app.cashflowmanagementservice.domain.entities.Movement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MovementService {
    fun entry(movements: List<Movement>)
    fun getList(pageable: Pageable): Page<Movement>?
}