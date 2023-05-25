package com.br.app.cashflowmanagementservice.resource.messaging.entity

import java.math.BigDecimal
import java.time.LocalDate

data class MovementStreamPayload(
    val id: String?,
    val description: String,
    val personId: String,
    val date: LocalDate,
    val typeMovement: String,
    val value: BigDecimal
): java.io.Serializable