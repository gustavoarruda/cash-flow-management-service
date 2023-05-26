package com.br.app.cashflowmanagementservice.application.controller.v1

import com.br.app.cashflowmanagementservice.application.entity.MovementPayload
import com.br.app.cashflowmanagementservice.application.entity.toDomain
import com.br.app.cashflowmanagementservice.domain.entities.Movement
import com.br.app.cashflowmanagementservice.domain.entities.toPayload
import com.br.app.cashflowmanagementservice.domain.service.MovementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*


@RequestMapping("/v1/")
@RestController
@Api(tags = ["Movements"], description = "API to perform credit and debit transactions.")
class MovementController(
    private val movementService: MovementService
) {

    @ApiResponses(
        ApiResponse(code = 201, message = "Success in creating a movement.")
    )
    @ApiOperation("Create a new movement.")
    @PostMapping("/movement")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMovement(@RequestBody movements: List<MovementPayload>) = movementService.createMovement(movements.map { it.toDomain() })

    @ApiResponses(
        ApiResponse(code = 200, message = "Successfully retrieved movements"),
        ApiResponse(code = 404, message = "No movement was found.")
    )
    @ApiOperation("Get the movements.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/movement")
    fun getMovements(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(value="date") @DateTimeFormat(pattern="yyyy-MM-dd") date: LocalDate
    ): ResponseEntity<List<Movement>> {
        return ResponseEntity.ok(movementService.getMovements(date, page, size))
    }

    @ApiResponses(
        ApiResponse(code = 200, message = "Successfully retrieved movements"),
        ApiResponse(code = 404, message = "No movement was found.")
    )
    @ApiOperation("Get the movements.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/movement/{id}")
    fun getMovement(@PathVariable id: String): ResponseEntity<Optional<Movement>> {
        val movement = movementService.findById(id)
        return if (movement.isPresent) {
            ResponseEntity.ok(movement)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ApiResponses(
        ApiResponse(code = 200, message = "Success when updating a movement."),
        ApiResponse(code = 404, message = "No movement was found.")
    )
    @ApiOperation("Update a movement by ID.")
    @PutMapping("/movement/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateMovement(@PathVariable id: String, @RequestBody movement: MovementPayload): ResponseEntity<Movement> {
        val existingMovement = movementService.findById(id)
        return if (existingMovement.isPresent) {
            movementService.updateMovement(id, movement.toDomain())
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ApiResponses(
        ApiResponse(code = 204, message = "Successfully delete a movement by ID."),
        ApiResponse(code = 404, message = "Movement not found."),
    )
    @DeleteMapping("/movement/{id}")
    @ApiOperation("Delete a movement by ID.")
    fun deleteMovement(@PathVariable id: String): ResponseEntity<Movement> {
        movementService.deleteMovement(id)
        return ResponseEntity.noContent().build()
    }

}