package com.br.app.cashflowmanagementservice.domain.service

import com.br.app.cashflowmanagementservice.domain.entities.Movement

interface MovementService {
    fun entry(movements: List<Movement>)
    fun getList(): List<Movement>
}