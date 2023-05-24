package com.br.app.cashflowmanagementservice.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestTemplateConfig {
    @Bean
    fun objectMapper() = JsonParserBuilder.default()
}