package com.br.app.cashflowmanagementservice.domain.service.impl

import com.br.app.cashflowmanagementservice.domain.entities.Movement
import com.br.app.cashflowmanagementservice.domain.entities.toDomain
import com.br.app.cashflowmanagementservice.domain.entities.toEntity
import com.br.app.cashflowmanagementservice.domain.repository.MovementRepository
import com.br.app.cashflowmanagementservice.domain.service.MovementService
import com.br.app.cashflowmanagementservice.resource.messaging.entity.MovementStreamPayload
import com.br.app.cashflowmanagementservice.resource.messaging.stream.MovementStreamProducer
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*


@Service
class MovementServiceImpl(
    private val movementRepository: MovementRepository,
    private val movementStreamProducer: MovementStreamProducer
) : MovementService {
    override fun createMovement(movements: List<Movement>) {
        runCatching {
            movementRepository.saveAll(movements.map { it.toEntity() })
        }.onSuccess {
            movements.map {
                movementStreamProducer.produce(
                    MovementStreamPayload(
                        description = it.description,
                        date = it.date,
                        typeMovement = it.typeMovement.type,
                        personId = it.personId,
                        value = it.value,
                        id = null
                    )
                )
            }
        }.onFailure { throw it }
    }

    override fun getMovements(date: LocalDate, page: Int, size: Int): List<Movement> {
        val pageRequest: PageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "date")
        val movements = movementRepository.findByDate(date, pageRequest)
        return movements.content.map { it.toDomain() }
    }

    override fun findById(id: String): Optional<Movement> {
        return movementRepository.findById(id).map { it.toDomain() }
    }

    override fun updateMovement(id: String, updatedMovement: Movement): Movement? {
        val existingMovement = findById(id).orElse(null)
        if (existingMovement != null) {
            val movement = existingMovement.copy(
                typeMovement = updatedMovement.typeMovement,
                date = updatedMovement.date,
                description = updatedMovement.description,
                value = updatedMovement.value,
                personId = updatedMovement.personId,
            )
            return movementRepository.save(movement.toEntity()).toDomain()
        }
        return null
    }

    override fun deleteMovement(id: String) {
        movementRepository.deleteById(id)
    }
}