package com.br.app.cashflowmanagementservice.domain.service.impl

import com.br.app.cashflowmanagementservice.application.enums.TypeMovement
import com.br.app.cashflowmanagementservice.domain.entities.MovementFakeData
import com.br.app.cashflowmanagementservice.domain.entities.entity.MovementEntity
import com.br.app.cashflowmanagementservice.domain.entities.toDomain
import com.br.app.cashflowmanagementservice.domain.entities.toEntity
import com.br.app.cashflowmanagementservice.domain.repository.MovementRepository
import com.br.app.cashflowmanagementservice.domain.service.MovementService
import com.br.app.cashflowmanagementservice.resource.messaging.stream.MovementStreamProducer
import io.mockk.*
import org.junit.jupiter.api.*
import org.assertj.core.api.Assertions.assertThat
import org.springframework.data.domain.PageImpl
import java.time.LocalDate
import java.util.*

class MovementServiceImplTest {

    private val movementRepository: MovementRepository = mockk()
    private val movementStreamProducer: MovementStreamProducer = mockk(relaxed = true)
    private lateinit var movementService: MovementService

    @BeforeEach
    fun setUp() {
        movementService = MovementServiceImpl(movementRepository, movementStreamProducer)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create a movement`() {
        val slot = slot<List<MovementEntity>>()
        val expectedMovement = MovementFakeData.buildList().map { it.toEntity() }

        every { movementRepository.saveAll(capture(slot)) } returns expectedMovement

        movementService.createMovement(expectedMovement.map { it.toDomain() })

        assertThat(slot.captured.first().id).isEqualTo(expectedMovement.first().id)
        assertThat(slot.captured.first().date).isEqualTo(expectedMovement.first().date)
        assertThat(slot.captured.first().description).isEqualTo(expectedMovement.first().description)
        assertThat(slot.captured.first().typeMovement).isEqualTo(expectedMovement.first().typeMovement)
        assertThat(slot.captured.first().value).isEqualTo(expectedMovement.first().value)
        assertThat(slot.captured.first().personId).isEqualTo(expectedMovement.first().personId)
        verify(exactly = 1) { movementRepository.saveAll(any()) }
        verify(exactly = 2) { movementStreamProducer.produce(any()) }
    }

    @Test
    fun `should get movements`() {
        val movements = MovementFakeData.buildList()
        val expectedMovements = PageImpl(movements)

        every { movementRepository.findByDate(any(), any()) } returns expectedMovements.map { it.toEntity() }

        val actualMovements = movementService.getMovements(LocalDate.of(2023, 5, 25), 0, 2)
        Assertions.assertEquals(expectedMovements.content, actualMovements)
    }

    @Test
    fun `should get movements by ID`() {
        val movement = MovementFakeData.build()
        val optional = Optional.of(movement.toEntity())

        every { movementRepository.findById(movement.id!!) } returns optional

        val result = movementService.findById(movement.id!!)
        Assertions.assertEquals(optional.map { it.toDomain() }, result)
    }

    @Test
    fun `should update a movement`() {
        val slot = slot<MovementEntity>()
        val updatedMovement = MovementFakeData.build()
        val id = updatedMovement.id
        val optional = Optional.of(updatedMovement.toEntity())

        every { movementRepository.findById(id!!) } returns optional
        every { movementRepository.save(capture(slot)) } answers { updatedMovement.toEntity() }

        movementService.updateMovement(id!!, updatedMovement)

        assertThat(slot.captured.id).isEqualTo(updatedMovement.id)
        assertThat(slot.captured.date).isEqualTo(updatedMovement.date)
        assertThat(slot.captured.description).isEqualTo(updatedMovement.description)
        assertThat(TypeMovement.fromType(slot.captured.typeMovement)).isEqualTo(updatedMovement.typeMovement)
        assertThat(slot.captured.value).isEqualTo(updatedMovement.value)
        assertThat(slot.captured.personId).isEqualTo(updatedMovement.personId)

        verify { movementRepository.findById(id) }
        verify(exactly = 1) { movementRepository.findById(any()) }
        verify(exactly = 1) { movementRepository.save(any()) }
    }

    @Test
    fun `should delete a movement`() {
        val id = MovementFakeData.build().id
        every { movementRepository.deleteById(id!!) } just runs
        movementService.deleteMovement(id!!)
        verify { movementRepository.deleteById(id) }
    }
}