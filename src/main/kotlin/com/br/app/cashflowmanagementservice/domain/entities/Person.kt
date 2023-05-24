package com.br.app.cashflowmanagementservice.domain.entities

import com.br.app.cashflowmanagementservice.application.entity.PersonPayload
import com.br.app.cashflowmanagementservice.domain.entities.entity.PersonEntity
import java.time.LocalDateTime

data class Person(
    val id: String?,
    val createdAt: LocalDateTime?
)

fun Person.toEntity() = PersonEntity(
    id = id
)

fun PersonEntity.toDomain() = Person(
    id = id,
    createdAt = createdAt
)

fun Person.toPayload() = PersonPayload(
    id = id!!,
    createdAt = createdAt
)