package com.br.app.cashflowmanagementservice.application.config

import org.springframework.kafka.support.serializer.JsonSerializer

open class CustomKafkaSerializer<T>: JsonSerializer<T>(JsonParserBuilder.default())
