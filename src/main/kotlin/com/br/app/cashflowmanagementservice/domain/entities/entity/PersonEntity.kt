package com.br.app.cashflowmanagementservice.domain.entities.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "person")
data class PersonEntity(
    @Id
    val id: String? = UUID.randomUUID().toString().uppercase(),
    val createdAt: LocalDateTime? = LocalDateTime.now()
)