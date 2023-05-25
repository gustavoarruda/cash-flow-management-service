package com.br.app.cashflowmanagementservice.application.controller.v1

import com.br.app.cashflowmanagementservice.application.entity.MovementPayload
import com.br.app.cashflowmanagementservice.application.entity.toDomain
import com.br.app.cashflowmanagementservice.domain.entities.Movement
import com.br.app.cashflowmanagementservice.domain.service.MovementService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RequestMapping("/v1/")
@RestController
class MovementController(
    private val movementService: MovementService
) {

    @PostMapping("/movement")
    @ResponseStatus(HttpStatus.CREATED)
    fun entry(@RequestBody movements: List<MovementPayload>) = movementService.entry(movements.map { it.toDomain() })

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/movement")
    fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(value="date") @DateTimeFormat(pattern="yyyy-MM-dd") date: LocalDate
    ): Page<Movement>? {
        val pageRequest: PageRequest = PageRequest.of(page, size)
        return movementService.getList(pageRequest)
    }

    @PutMapping("/movement/{id}")
    fun updateMovement(@PathVariable id: String, @RequestBody movement: MovementPayload): ResponseEntity<Movement> {
        val existingMovement = movementService.findById(id)
        return if (existingMovement != null) {
            movementService.updateMovement(id, movement.toDomain())
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}