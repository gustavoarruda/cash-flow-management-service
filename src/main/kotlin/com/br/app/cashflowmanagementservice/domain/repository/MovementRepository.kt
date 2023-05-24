package com.br.app.cashflowmanagementservice.domain.repository


import com.br.app.cashflowmanagementservice.domain.entities.entity.MovementEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovementRepository : JpaRepository<MovementEntity, String>