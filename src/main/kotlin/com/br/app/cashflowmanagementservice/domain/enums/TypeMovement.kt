package com.br.app.cashflowmanagementservice.application.enums

enum class TypeMovement(val type: String) {
    CREDIT("C"),
    DEBIT("D");

    companion object {
        fun fromType(type: String): TypeMovement {
            return values().firstOrNull { it.type == type }
                ?: throw IllegalArgumentException("Invalid TypeMovement: $type")
        }
    }
}

