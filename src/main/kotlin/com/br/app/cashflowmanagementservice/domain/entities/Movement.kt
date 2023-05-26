package com.br.app.cashflowmanagementservice.domain.entities

import com.br.app.cashflowmanagementservice.application.entity.MovementPayload
import com.br.app.cashflowmanagementservice.application.enums.TypeMovement
import com.br.app.cashflowmanagementservice.domain.entities.entity.MovementEntity
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Movement(
    val id: String? = UUID.randomUUID().toString(),
    val description: String,
    val personId: String,
    val date: LocalDate,
    val typeMovement: TypeMovement,
    val value: BigDecimal
)

fun Movement.toEntity() = MovementEntity(
    id = id,
    description = description,
    personId = personId,
    date = date,
    typeMovement = typeMovement.type,
    value = value
)

fun Movement.toPayload() = MovementPayload(
    id = id,
    description = description,
    personId = personId,
    date = date,
    typeMovement = typeMovement.type,
    value = value
)

fun MovementEntity.toDomain() = Movement(
    id = id,
    description = description,
    personId = personId,
    date = date,
    typeMovement = TypeMovement.fromType(typeMovement),
    value = value
)