package com.br.app.cashflowmanagementservice.domain.entities

import com.br.app.cashflowmanagementservice.application.enums.TypeMovement
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

object MovementFakeData {
    fun buildList() = listOf(
        Movement(
            description = "Assaí Atacadista",
            personId = UUID.randomUUID().toString(),
            date = LocalDate.of(2023, 5, 25),
            typeMovement = TypeMovement.CREDIT,
            value = BigDecimal(1.000),
            id = UUID.randomUUID().toString()
        ),
        Movement(
            description = "Assaí Atacadista",
            personId = UUID.randomUUID().toString(),
            date = LocalDate.of(2023, 5, 25),
            typeMovement = TypeMovement.CREDIT,
            value = BigDecimal(1000),
            id = UUID.randomUUID().toString()
        )
    )

    fun build() = Movement(
        description = "Assaí Atacadista",
        personId = UUID.randomUUID().toString(),
        date = LocalDate.of(2023, 5, 25),
        typeMovement = TypeMovement.CREDIT,
        value = BigDecimal(1000.00),
        id = UUID.randomUUID().toString()
    )
}