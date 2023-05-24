package com.br.app.cashflowmanagementservice.application.controller.v1

import com.br.app.cashflowmanagementservice.application.entity.MovementPayload
import com.br.app.cashflowmanagementservice.application.entity.toDomain
import com.br.app.cashflowmanagementservice.domain.service.MovementService
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/")
@RestController
class MovementController(
    private val movementService: MovementService
) {

    @PostMapping("/movement")
    @ResponseStatus(HttpStatus.CREATED)
    fun entry(@RequestBody movements: List<MovementPayload>) = movementService.entry(movements.map { it.toDomain() })

}