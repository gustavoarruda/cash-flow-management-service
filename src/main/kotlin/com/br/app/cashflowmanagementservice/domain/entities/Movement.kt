package com.br.app.cashflowmanagementservice.domain.entities

import com.br.app.cashflowmanagementservice.application.enums.TypeMovement
import com.br.app.cashflowmanagementservice.domain.entities.entity.MovementEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Movement(
    val id: String? = UUID.randomUUID().toString(),
    val description: String,
    val personId: String,
    val date: LocalDate,
    val typeMovement: TypeMovement,
    val value: Double,
    val createdAt: LocalDateTime? = LocalDateTime.now()
)

fun Movement.toEntity() = MovementEntity(
    id = id,
    description = description,
    personId = personId,
    date = date,
    typeMovement = typeMovement.type,
    value = value,
    createdAt = createdAt
)

fun MovementEntity.toDomain() = Movement(
    id = id,
    description = description,
    personId = personId,
    date = date,
    typeMovement = TypeMovement.fromType(typeMovement),
    value = value,
    createdAt = createdAt
)