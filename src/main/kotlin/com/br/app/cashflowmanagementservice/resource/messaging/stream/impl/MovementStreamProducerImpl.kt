package com.br.app.cashflowmanagementservice.resource.messaging.stream.impl

import com.br.app.cashflowmanagementservice.resource.messaging.entity.MovementStreamPayload
import com.br.app.cashflowmanagementservice.resource.messaging.stream.MovementStreamProducer
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Service("movementProducer")
class MovementStreamProducerImpl : Supplier<Flux<MovementStreamPayload>>, MovementStreamProducer {
    private val sink = Sinks.many().unicast().onBackpressureBuffer<MovementStreamPayload>()
    override fun get() = sink.asFlux()
    override fun produce(movementMessagePayload: MovementStreamPayload) {
        sink.emitNext(movementMessagePayload, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}