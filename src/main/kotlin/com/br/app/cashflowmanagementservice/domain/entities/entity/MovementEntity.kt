package com.br.app.cashflowmanagementservice.domain.entities.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "movement")
data class MovementEntity(
    @Id
    val id: String?,
    val description: String,
    val personId: String,
    val date: LocalDate,
    val typeMovement: String,
    val value: BigDecimal,
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime? = LocalDateTime.now(),
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime? = LocalDateTime.now()
)