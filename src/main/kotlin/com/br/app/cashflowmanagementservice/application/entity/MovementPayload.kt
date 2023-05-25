package com.br.app.cashflowmanagementservice.application.entity

import com.br.app.cashflowmanagementservice.application.enums.TypeMovement
import com.br.app.cashflowmanagementservice.domain.entities.Movement
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class MovementPayload(
    val id: String? = UUID.randomUUID().toString(),
    val description: String,
    val personId: String,
    val date: LocalDate,
    val typeMovement: String,
    val value: BigDecimal,
): java.io.Serializable

fun MovementPayload.toDomain() = Movement(
    id = id,
    description = description,
    personId = personId,
    date = date,
    typeMovement = TypeMovement.fromType( typeMovement.uppercase() ),
    value = value
)
