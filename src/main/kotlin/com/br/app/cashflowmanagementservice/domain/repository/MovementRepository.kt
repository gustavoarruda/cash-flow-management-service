package com.br.app.cashflowmanagementservice.domain.repository


import com.br.app.cashflowmanagementservice.domain.entities.entity.MovementEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MovementRepository : JpaRepository<MovementEntity, String> {
    fun findByDate(date: LocalDate, pageable: Pageable): Page<MovementEntity>
}