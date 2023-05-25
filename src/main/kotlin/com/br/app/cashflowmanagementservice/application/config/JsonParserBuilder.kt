package com.br.app.cashflowmanagementservice.application.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object JsonParserBuilder {

    fun default(): ObjectMapper = jacksonObjectMapper()
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .registerModule(KotlinModule())
        .registerModule(SimpleModule())
        .also {
            val format = DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss"))
                .toFormatter()
            val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val javaTimeModule = JavaTimeModule()

            javaTimeModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(format))
            javaTimeModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(format))
            javaTimeModule.addSerializer(ZonedDateTime::class.java, ZonedDateTimeSerializer(format))
            javaTimeModule.addSerializer(LocalDate::class.java, LocalDateSerializer(dateFormat))
            javaTimeModule.addDeserializer(LocalDate::class.java, LocalDateDeserializer(dateFormat))

            it.registerModule(javaTimeModule)

        }
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setDateFormat(StdDateFormat())
}

