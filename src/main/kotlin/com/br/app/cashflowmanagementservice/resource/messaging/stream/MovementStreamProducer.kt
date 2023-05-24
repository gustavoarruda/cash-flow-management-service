package com.br.app.cashflowmanagementservice.resource.messaging.stream

import com.br.app.cashflowmanagementservice.resource.messaging.entity.MovementStreamPayload

interface MovementStreamProducer {
    fun produce(movementMessagePayload: MovementStreamPayload)
}