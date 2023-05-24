package com.br.app.cashflowmanagementservice.resource.messaging.entity

import com.br.app.cashflowmanagementservice.application.enums.TypeMovement
import com.br.app.cashflowmanagementservice.domain.entities.Movement
import java.time.LocalDate
import java.time.LocalDateTime

data class MovementStreamPayload(
    val id: String?,
    val description: String,
    val personId: String,
    val date: LocalDate,
    val typeMovement: String,
    val value: Double
): java.io.Serializable